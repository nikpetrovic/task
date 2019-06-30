package com.nikpetrovic.task.repository;

import com.nikpetrovic.task.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockPriceRepo extends GenericRepo<StockPrice, String> implements IStockPriceRepo {
    @Autowired
    private IStockPriceJpaRepo stockPriceJpaRepo;

    @Override
    public List<StockPrice> findByCompanySymbol(String symbol) {
        return this.getJpaRepository().findByIdCompanySymbol(symbol);
    }

    @Override
    public List<StockPrice> findByCompanySymbolAndRange(String symbol, Long after, Long before) {
        return this.getJpaRepository().findAll((Root<StockPrice> root, CriteriaQuery<?> query,
                                               CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.get("id.companySymbol"), symbol));
            if (after != null) {
                predicate.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("id.timestamp"), after)));
            }
            if (before != null) {
                predicate.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("id.timestamp"), before)));
            }

            return criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));
        });
    }

    @Override
    protected IStockPriceJpaRepo getJpaRepository() {
        return this.stockPriceJpaRepo;
    }
}
