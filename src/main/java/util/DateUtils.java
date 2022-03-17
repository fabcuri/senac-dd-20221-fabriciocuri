package util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtils {
	
	//FONTE: https://www.baeldung.com/java-date-to-localdate-and-localdatetime
	public static LocalDateTime converterParaLocalDateTime(Date data) {
		return LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault());
	}
}
