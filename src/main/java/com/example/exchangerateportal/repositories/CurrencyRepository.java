package com.example.exchangerateportal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.exchangerateportal.entities.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Currency findByDateAndCurrency(String date, String currency);

	@Query("SELECT c FROM Currency c WHERE c.currency = :currency")
	List<Currency> findHistoryByCurrency(String currency);
}
