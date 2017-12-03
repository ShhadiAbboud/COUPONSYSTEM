package facades;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import exceptions.CompanyException;
import exceptions.CouponException;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Coupon;
import javaBeans.CouponType;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyFacade.
 */
public class CompanyFacade implements CouponClientFacade
{

	/** The companydbdao. */
	private CompanyDBDAO companydbdao;
	
	/** The coupondbdao. */
	private CouponDBDAO coupondbdao;
	
	/**
	 * Instantiates a new company facade.
	 */
	public CompanyFacade()
	{
		this.companydbdao = new CompanyDBDAO();
		this.coupondbdao = new CouponDBDAO();	

	}
	
	/**
	 * Creates the coupon.
	 *
	 * @param coupon the coupon
	 */
	public void createCoupon(Coupon coupon)
	{
		
			try {
				coupondbdao.createCoupon(coupon);
			} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateEntryException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CouponException(e);
			}
		
	}
	
	/**
	 * Removes the coupon.
	 *
	 * @param coupon the coupon
	 */
	public void removeCoupon(Coupon coupon)
	{
		
			try {
				coupondbdao.removeCoupon(coupon);
			} catch (ClassNotFoundException | SQLException | InterruptedException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
		
	}
	
	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 */
	public void updateCoupon(Coupon coupon)
	{
			try {
				coupondbdao.updateCoupon(coupon);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CouponException(e);
			}
		
	}
	
	/**
	 * Gets the coupon.
	 *
	 * @param id the id
	 * @return the coupon
	 */
	public Coupon getCoupon(long id)
	{
		Coupon cou = new Coupon();
			try {
				cou = coupondbdao.getCoupon(id);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
		
		return cou ;
	}
	
	/**
	 * Gets the all coupon.
	 *
	 * @return the all coupon
	 */
	public Collection<Coupon> getAllCoupon()
	{
		ArrayList<Coupon> arr = new ArrayList<>(); 
		
			try {
				arr = (ArrayList<Coupon>) companydbdao.getCoupons();
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
		
		return arr ;
	}
	
	/**
	 * Gets the coupon by type.
	 *
	 * @param couponType the coupon type
	 * @return the coupon by type
	 */
	public Collection<Coupon> getCouponByType(CouponType couponType)
	{
		ArrayList<Coupon> arr = new ArrayList<>(); 
		
			try {
				arr =  (ArrayList<Coupon>) companydbdao.getCompanyCouponByType(couponType);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CouponException(e);
			}
	
		return arr;
	}
	
	/**
	 * Gets the coupon by price.
	 *
	 * @param price the price
	 * @return the coupon by price
	 */
	public Collection<Coupon> getCouponByPrice(double price)
	{
		ArrayList<Coupon> arr = new ArrayList<>(); 
		
			try {
				arr =  (ArrayList<Coupon>) companydbdao.getCompanyCouponByPrice(price);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
	
		return arr;
	}
	
	/**
	 * Gets the coupon by date.
	 *
	 * @param date the date
	 * @return the coupon by date
	 */
	public Collection<Coupon> getCouponByDate(Date date)
	{
		
			try {
				return companydbdao.getCompanyCouponByDate(date);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
			new CompanyException(e);
			}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see facades.CouponClientFacade#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) {
		try 
		{
			if(companydbdao.login(name, password))
				return this;
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | WrongDataInputException
				| NullConnectionException e)
		{
			// TODO Auto-generated catch block
			new CompanyException(e);
		}
		return null;
	}
	
	
}
