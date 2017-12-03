package utilities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class DateTranslate.
 */
public class DateTranslate {

	/**
	 * String to date.
	 *
	 * @param date the date
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date  stringToDate(String date) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date newdate = (Date) sdf.parse(date);

		return newdate;

	}
	
	/**
	 * Date to string.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String dateToString(Date date)
	{
		DateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String newDate = sdf.format(date);

		return newDate;
	}
}
