package threads;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import dao.CouponDBDAO;
import exceptions.NullConnectionException;
import javaBeans.Coupon;

// TODO: Auto-generated Javadoc
/**
 * The Class DailyCouponExpirationTask.
 */
public class DailyCouponExpirationTask implements Runnable {
	
	/** The quit. */
	public boolean quit=false;
	
	/** The all coupons. */
	public ArrayList<Coupon> allCoupons = new ArrayList<>();
	
	/** The coupondbdao. */
	CouponDBDAO coupondbdao = new CouponDBDAO();
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		Date today = (Date) Calendar.getInstance().getTime();

		while (!quit)
		{
			try {
				allCoupons = (ArrayList<Coupon>) coupondbdao.getAllCoupon();
			}
			catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e1)
			{

			}

			for (Coupon c : allCoupons)
			{
				if (c.getEndDate().before(today))
				{
					try {
						coupondbdao.removeCoupon(c);
						System.out.println("all out of date coupons have been removed");
					}
					catch (ClassNotFoundException | SQLException | InterruptedException | NullConnectionException e)
					{
						
					}
				}
			}

			try
			{
				Thread.sleep(24*60*60*1000);
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}

	/**
	 * Stop task.
	 */
	public void stopTask()
	{
		quit=true;
	}

}
