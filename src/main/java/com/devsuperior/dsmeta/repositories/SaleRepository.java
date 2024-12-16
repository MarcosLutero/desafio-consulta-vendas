package com.devsuperior.dsmeta.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SumaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(obj.id, obj.amount, obj.date, obj.seller.name) "
			+ "FROM Sale obj " + "WHERE obj.date BETWEEN :dataInicial AND :dataFinal "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<SaleDTO> report(Pageable pageable, @Param("dataInicial") LocalDate dataInicial,
			@Param("dataFinal") LocalDate dataFinal, @Param("name") String name);

	@Query(nativeQuery = true, value = "SELECT seller.name AS name, SUM(sale.amount) AS amount " 
	        + "FROM tb_sales sale "
	        + "INNER JOIN tb_seller seller ON sale.seller_id = seller.id "
	        + "WHERE sale.date BETWEEN :minDate AND :maxDate "
	        + "GROUP BY seller.name")
	Page<SumaryProjection> sumary(Pageable pageable, @Param("minDate") LocalDate minDate,
	                              @Param("maxDate") LocalDate maxDate);



}
