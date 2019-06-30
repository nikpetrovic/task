package com.nikpetrovic.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikpetrovic.task.dto.Logo;
import com.nikpetrovic.task.dto.StockQuote;
import com.nikpetrovic.task.services.IStockService;
import com.nikpetrovic.task.utils.HttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskApplicationTests {
    public static final String GET_QUOTE_BY_SYMBOL = "/stable/stock/aapl/quote";
    public static final String GET_LOGO_BY_SYMBOL = "/stable/stock/aapl/logo";

    private StockQuote stockQuoteAapl;
    private Logo logoAapl;

    @MockBean
    private HttpClient httpClient;
    @Autowired
    private IStockService stockService;

    public IStockService getStockService() {
        return stockService;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    @Before
    public void setUp() {
        stockQuoteAapl = new StockQuote();
        stockQuoteAapl.setCompanyName("aapl");
        stockQuoteAapl.setCompanyName("Apple, Inc.");
        stockQuoteAapl.setLatestPrice(198.02d);
        stockQuoteAapl.setLatestUpdate(1561744866210L);

        logoAapl = new Logo();
        logoAapl.setUrl("https://storage.googleapis.com/iex/api/logos/AAPL.png");

        try {
            Mockito.when(getHttpClient().get(GET_QUOTE_BY_SYMBOL))
                    .thenReturn(new ObjectMapper().writeValueAsString(stockQuoteAapl));
            Mockito.when(getHttpClient().get(GET_LOGO_BY_SYMBOL))
                    .thenReturn(new ObjectMapper().writeValueAsString(logoAapl));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getStockQuoteBySymbolTest() {
        StockQuote stockQuoteForAapl = this.getStockService().getStockQuoteBySymbol("aapl");
        assertEquals(stockQuoteAapl, stockQuoteForAapl);
    }

    @Test
    public void getLogoBySymbolTest() {
        Logo logo = this.getStockService().getLogoBySymbol("aapl");
        assertEquals(logo, logoAapl);
    }
}
