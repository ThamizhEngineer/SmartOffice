package com.ss.smartoffice.soservice.master.officeCalender;

import java.time.LocalDate;

public interface CalenderSummary {

	Integer getId();
	String	getOfficeName();
	String  getFiscalYearCode();
	String 	getFromDt();
	String 	getToDt();
	LocalDate getCalDate();
	String 	getWeekdayCount();
	String	getHolidayCount();
	String 	getWeekendCount();
	String 	getSummaryMonth();
	
	String 	setWeekdayCount(String string);
	String	setHolidayCount(String string);
	String 	setWeekendCount(String string);
	String 	setSummaryMonth(String string);
}
