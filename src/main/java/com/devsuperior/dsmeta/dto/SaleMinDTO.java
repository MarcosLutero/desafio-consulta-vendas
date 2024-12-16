package com.devsuperior.dsmeta.dto;


import com.devsuperior.dsmeta.projections.SumaryProjection;

public class SaleMinDTO {

    private String name;
    private Double totalAmount;

		

    public SaleMinDTO(SumaryProjection projection) {
        name = projection.getName();
        totalAmount = Double.parseDouble(projection.getAmount());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
