package com.nikpetrovic.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeAlias("prices")
@EqualsAndHashCode
public class StockPrice {
    @EmbeddedId
    private StockPriceId id;
    private Double price;

    public StockPrice(String companySymbol, Long timestamp, Double price) {
        this.id = new StockPriceId(timestamp, companySymbol);
        this.price = price;
    }
}
