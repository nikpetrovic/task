package com.nikpetrovic.task.services;

import com.nikpetrovic.task.dto.Logo;
import com.nikpetrovic.task.dto.StockQuote;
import com.nikpetrovic.task.utils.HttpClient;
import com.nikpetrovic.task.utils.JsonMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nikpetrovic.task.services.ResourceConstants.*;

@Service
public class StockService implements IStockService {
    @Autowired
    public HttpClient client;

    public HttpClient getClient() {
        return client;
    }

    @Override
    public StockQuote getStockQuoteBySymbol(String symbol) {
        String url = String.format(GET_QUOTE_BY_SYMBOL, symbol);
        return JsonMapper.getMapper().fromJsonToStockQuote(getClient().get(url));
    }

    @Override
    public Logo getLogoBySymbol(String symbol) {
        String url = String.format(GET_LOGO_BY_SYMBOL, symbol);
        return JsonMapper.getMapper().fromJsonToLogo(getClient().get(url));
    }
}
