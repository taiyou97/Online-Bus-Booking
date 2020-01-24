package com.app.pojos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DayFromDate {
	private static String[] day = {"sun", "mon", "tues", "wed", "thurs", "fri", "sat"};
	
	private DayFromDate() {
		System.out.println("DayFromDate()");
	}
	
	public static String getDay(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			d = formatter.parse(date);
			return day[d.getDay()];
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static int getDayInt(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			d = formatter.parse(date);
			System.out.println(d);
			return d.getDay();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date getDate(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static LocalDate getLocalDate(String date) {
		return LocalDate.parse(date);
	}
	
	public static String getStringDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	
}
