package com.ss.smartoffice.shared.model.currency;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepo extends CrudRepository<ExchangeRate, Integer> {

	@Query( "select exRate.exchangeValue from com.ss.smartoffice.shared.model.currency.ExchangeRate exRate where exRate.fromCurrId=:fromCurrId AND exRate.toCurrId=:toCurrId " )
	float findByFromCurrIdAndToCurrId(String fromCurrId,String toCurrId);
	
}
