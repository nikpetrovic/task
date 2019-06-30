package com.nikpetrovic.task.controllers;

import com.nikpetrovic.task.model.Company;
import com.nikpetrovic.task.model.StockPrice;
import com.nikpetrovic.task.repository.ICompanyRepo;
import com.nikpetrovic.task.repository.IStockPriceRepo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class TaskController {
    private final ICompanyRepo companyRepo;
    private final IStockPriceRepo stockPriceRepo;

    public TaskController(ICompanyRepo companyRepo, IStockPriceRepo stockPriceRepo) {
        this.companyRepo = companyRepo;
        this.stockPriceRepo = stockPriceRepo;
    }

    private IStockPriceRepo getStockPriceRepo() {
        return stockPriceRepo;
    }

    private ICompanyRepo getCompanyRepo() {
        return companyRepo;
    }

    @RequestMapping("/{symbol}/company")
    public Optional<Company> findCompanyBySymbol(@PathVariable("symbol") String symbol) {
        return this.getCompanyRepo().findById(symbol);
    }

    @RequestMapping("/companies")
    public Iterable<Company> findCompaniesBySymbols(@RequestParam("symbols") String[] symbols) {
        return this.getCompanyRepo().findAllById(Arrays.asList(symbols));
    }

    @RequestMapping("/{symbol}/prices")
    public Iterable<StockPrice> findPrices(@PathVariable("symbol") String symbol, @RequestParam(value = "after",
            required = false) Long after,
                                           @RequestParam(value = "before", required = false) Long before) {
        if (after == null && before == null) {
            return this.getStockPriceRepo().findByCompanySymbol(symbol);
        } else {
            return this.getStockPriceRepo()
                    .findByCompanySymbolAndRange(symbol, after, before);
        }
    }
}
