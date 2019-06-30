package com.nikpetrovic.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class StockQuote {
    private String symbol;
    private String companyName;
    private Double latestPrice;
    private Long latestUpdate;
}
