package com.sample.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAndTime {

	public String getLocalDateTime() {

		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String formatDateTime = dateTime.format(formatter);

		return formatDateTime;
	}

}
