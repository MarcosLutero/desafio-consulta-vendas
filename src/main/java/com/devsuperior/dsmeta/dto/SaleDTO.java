package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SaleDTO {
    private Long id;
    private Double amount;
    private LocalDate date;
    private SellerDTO seller;
    private String name;
    
  
    public SaleDTO() {}
    
    public SaleDTO(Long id, Double amount, LocalDate date, String name) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.name = name;
    }


    public SaleDTO(Sale entity) {
        id = entity.getId();
        amount = entity.getAmount();
        date = entity.getDate();
        seller = new SellerDTO(entity.getSeller());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;	
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }	
}
