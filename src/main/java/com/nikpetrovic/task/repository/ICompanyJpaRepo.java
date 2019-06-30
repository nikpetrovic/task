package com.nikpetrovic.task.repository;

import com.nikpetrovic.task.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyJpaRepo extends JpaRepository<Company, String> {
}
