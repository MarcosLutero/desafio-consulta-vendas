package com.devsuperior.dsmeta.services;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	

	
	
	public Page<SaleDTO> report(Pageable pageable, String minDate, String maxDate, String name) {
	    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	    LocalDate dataFinal = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
	    LocalDate dataInicial = (minDate == null || minDate.isEmpty()) ? dataFinal.minusYears(1L) : LocalDate.parse(minDate);
	    String searchName = (name == null || name.isEmpty()) ? "" : name.trim().toUpperCase();
	    return repository.report(pageable, dataInicial, dataFinal, searchName);
	}

	
	public Page<SaleMinDTO> sumary(Pageable pageable, String minDate, String maxDate) {
	    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	    LocalDate dataFinal = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
	    LocalDate dataInicial = (minDate == null || minDate.isEmpty()) ? dataFinal.minusYears(1L) : LocalDate.parse(minDate);
	    Page<SumaryProjection> result = repository.sumary(pageable, dataInicial, dataFinal);
	    return result.map(SaleMinDTO::new);
	}


}
