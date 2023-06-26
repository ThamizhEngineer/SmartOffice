package com.ss.smartoffice.soreport.revenueforecast;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RevenueForecastImpl {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<RevenueForecast> fetchResult() {
		// had to create a view v_revenue_forecast due to the issue --> illegal mix of collations (utf8mb4_unicode_ci implicit)
		String sql = "select * from v_revenue_forecast";
		return jdbcTemplate.query(sql, (rs, rowNum) -> new RevenueForecast(rs.getString("year"),
				rs.getString("quarter_name"), rs.getString("order_intake"), rs.getString("actual_revenue")));
	}

}
