package com.nikpetrovic.task.repository;

import com.nikpetrovic.task.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyRepo extends GenericRepo<Company, String> implements ICompanyRepo {
    @Autowired
    private ICompanyJpaRepo companyJpaRepo;

    @Override
    public Optional<Company> findById(String symbol) {
        return getJpaRepository().findById(symbol);
    }

    @Override
    protected ICompanyJpaRepo getJpaRepository() {
        return this.companyJpaRepo;
    }
}
