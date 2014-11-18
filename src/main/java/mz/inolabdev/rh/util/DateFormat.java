package mz.inolabdev.rh.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
	
	private static Locale locale = new Locale("pt", "Br");

	public static String formated(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/mm/yyyy", locale);

		return sdf.format(date);
	}
}
