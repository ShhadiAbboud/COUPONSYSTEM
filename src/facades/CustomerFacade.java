package facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exceptions.CouponException;
import exceptions.CustomerException;
import exceptions.DuplicateCouponTypeException;
import exceptions.NullConnectionException;
import exceptions.UnAvailableCouponException;
import exceptions.WrongDataInputException;
import javaBeans.Coupon;
import javaBeans.CouponType;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerFacade.
 */
public class CustomerFacade implements CouponClientFacade {
	
	/** The customerdbdao. */
	private CustomerDBDAO customerdbdao;
	private CouponDBDAO coupondbdao;
	
	/**
	 * Instantiates a new customer facade.
	 */
	public CustomerFacade()
	{
		this.customerdbdao = new CustomerDBDAO();
	}
	
	/**
	 * Purchase coupon.
	 *
	 * @param coupon the coupon
	 */
	public void purchaseCoupon(Coupon coupon)
	{
		try
		{
			customerdbdao.purchaseCoupon(coupon);
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
				| DuplicateCouponTypeException | UnAvailableCouponException | NullConnectionException e) 
		{
			new CouponException(e);
		}
	}
	
	/**
	 * Gets the all purchased coupons.
	 *
	 * @return the all purchased coupons
	 */
	public Collection<Coupon> getAllPurchasedCoupons()
	{
		ArrayList<Coupon> allco = new ArrayList<>();
		
			try {
				allco = (ArrayList<Coupon>) customerdbdao.getCoupons();
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		return  allco ;
	}
	
	/**
	 * Gets the all purchased coupons by type.
	 *
	 * @param coupontype the coupontype
	 * @return the all purchased coupons by type
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType coupontype)
	{
		ArrayList<Coupon> allco = new ArrayList<>();
		
			try {
				allco = (ArrayList<Coupon>) customerdbdao.getAllPurchasedCouponsByType(coupontype);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		return  allco ;
	}
	
	/**
	 * Gets the all purchased coupons by price.
	 *
	 * @param price the price
	 * @return the all purchased coupons by price
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price)
	{
		ArrayList<Coupon> allco = new ArrayList<>();
		
			try {
				allco = (ArrayList<Coupon>) customerdbdao.getAllPurchasedCouponsByPrice(price);
			} catch (ClassNotFoundException | InterruptedException | SQLException | ParseException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		return  allco ;
	}
	
	/* (non-Javadoc)
	 * @see facades.CouponClientFacade#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CouponClientFacade login(String name, String password, String clientType) 
	{
		try 
		{
			if(customerdbdao.login(name, password))
				return this;
		}
		catch (ClassNotFoundException | InterruptedException | SQLException | WrongDataInputException
				| NullConnectionException e)
		{
			// TODO Auto-generated catch block
			new CustomerException(e);
		}
		return null;
	}

	public Coupon getCoupon(int i) 
	{
		try 
		{
		return coupondbdao.getCoupon(i);
		}
		catch (SQLException | ClassNotFoundException | InterruptedException | ParseException | NullConnectionException e) {
			// TODO Auto-generated catch block
			new CustomerException(e);
		}
		return null;
		
	}
}


