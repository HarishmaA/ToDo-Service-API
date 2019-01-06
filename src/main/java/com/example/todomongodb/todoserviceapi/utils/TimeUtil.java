package com.example.todomongodb.todoserviceapi.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
	public static String getCurrentTime() {
		return OffsetDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}
}
