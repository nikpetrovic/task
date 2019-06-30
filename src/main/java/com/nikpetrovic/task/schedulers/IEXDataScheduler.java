package com.nikpetrovic.task.schedulers;

import com.nikpetrovic.task.beans.StockPullExecutor;
import com.nikpetrovic.task.configs.DataFetchConfig;
import com.nikpetrovic.task.dto.Logo;
import com.nikpetrovic.task.dto.StockQuote;
import com.nikpetrovic.task.model.Company;
import com.nikpetrovic.task.model.StockPrice;
import com.nikpetrovic.task.repository.ICompanyRepo;
import com.nikpetrovic.task.repository.IStockPriceRepo;
import com.nikpetrovic.task.services.IStockService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@EnableScheduling
@Profile("!test")
public class IEXDataScheduler {
    private final IStockService stockService;
    private final ICompanyRepo companyRepo;
    private final IStockPriceRepo stockPriceRepo;
    private final StockPullExecutor taskExecutor;
    private final DataFetchConfig dataFetchConfig;

    public IEXDataScheduler(IStockService stockService, ICompanyRepo companyRepo, IStockPriceRepo stockPriceRepo,
                            StockPullExecutor taskExecutor, DataFetchConfig dataFetchConfig) {
        this.stockService = stockService;
        this.companyRepo = companyRepo;
        this.stockPriceRepo = stockPriceRepo;
        this.taskExecutor = taskExecutor;
        this.dataFetchConfig = dataFetchConfig;
    }

    @PostConstruct
    public void fetchCompanyInfos() {
        for (String symbol : this.getDataFetchConfig().getCompanySymbols()) {
            getTaskExecutor().execute(() -> {
                StockQuote stockQuote = getStockService().getStockQuoteBySymbol(symbol);
                Logo logo = getStockService().getLogoBySymbol(symbol);
                Company company = new Company(symbol, stockQuote.getCompanyName(), logo.getUrl());
                getCompanyRepo().save(company);

                StockPrice stockPrice = new StockPrice(symbol, stockQuote.getLatestUpdate(),
                        stockQuote.getLatestPrice());
                getStockPriceRepo().save(stockPrice);
            });
        }
    }

    private DataFetchConfig getDataFetchConfig() {
        return dataFetchConfig;
    }

    private StockPullExecutor getTaskExecutor() {
        return taskExecutor;
    }

    private IStockPriceRepo getStockPriceRepo() {
        return stockPriceRepo;
    }

    private ICompanyRepo getCompanyRepo() {
        return companyRepo;
    }

    public IStockService getStockService() {
        return stockService;
    }

    @Scheduled(fixedRateString = "${job.fetch-price.rate}")
    public void fetchData() {
        for (String symbol : this.getDataFetchConfig().getCompanySymbols()) {
            getTaskExecutor().execute(() -> {
                StockQuote stockQuote = getStockService().getStockQuoteBySymbol(symbol);
                StockPrice stockPrice = new StockPrice(symbol, stockQuote.getLatestUpdate(),
                        stockQuote.getLatestPrice());
                getStockPriceRepo().save(stockPrice);
            });
        }
    }
}
