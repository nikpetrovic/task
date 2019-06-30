package com.nikpetrovic.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeAlias("companies")
public class Company {
    @Id
    @Column(length = 8)
    private String symbol;
    private String companyName;
    private String logo;
}
