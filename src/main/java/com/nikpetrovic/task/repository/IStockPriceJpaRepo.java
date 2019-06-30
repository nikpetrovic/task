package com.nikpetrovic.task.repository;

import com.nikpetrovic.task.model.StockPrice;
import com.nikpetrovic.task.model.StockPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IStockPriceJpaRepo extends JpaRepository<StockPrice, StockPriceId>, JpaSpecificationExecutor<StockPrice> {
    List<StockPrice> findByIdCompanySymbol(String symbol);
}
