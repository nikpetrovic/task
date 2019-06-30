package com.nikpetrovic.task.services;

import com.nikpetrovic.task.dto.Logo;
import com.nikpetrovic.task.dto.StockQuote;

public interface IStockService {
    StockQuote getStockQuoteBySymbol(String symbol);
    Logo getLogoBySymbol(String symbol);
}
