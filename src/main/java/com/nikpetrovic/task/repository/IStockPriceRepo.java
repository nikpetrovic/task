package com.nikpetrovic.task.repository;

import com.nikpetrovic.task.model.StockPrice;

import java.util.List;

public interface IStockPriceRepo extends IGenericRepo<StockPrice, String> {
    List<StockPrice> findByCompanySymbol(String symbol);
    List<StockPrice> findByCompanySymbolAndRange(String symbol, Long after, Long before);
}
