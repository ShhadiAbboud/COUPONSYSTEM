package utilities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTranslate {

	public static Date  stringToDate(String date) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date newdate = (Date) sdf.parse(date);

		return newdate;

	}
	public static String dateToString(Date date)
	{
		DateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String newDate = sdf.format(date);

		return newDate;
	}
}
