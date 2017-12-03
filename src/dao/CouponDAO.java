package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import javaBeans.Coupon;
import javaBeans.CouponType;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CouponDAO.
 */
public interface CouponDAO {
	
	/**
	 * Creates the coupon.
	 *
	 * @param coupon the coupon
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void createCoupon(Coupon coupon) throws SQLException,
	ClassNotFoundException,
	InterruptedException,
	DuplicateEntryException,
	NullConnectionException;
	
	/**
	 * Removes the coupon.
	 *
	 * @param coupon the coupon
	 * @throws SQLException the SQL exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void removeCoupon(Coupon coupon) throws SQLException,
	ClassNotFoundException,
	InterruptedException,
	NullConnectionException;
	
	/**
	 * Update coupon.
	 *
	 * @param coupon the coupon
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	/**
	 * Gets the coupon.
	 *
	 * @param id the id
	 * @return the coupon
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Coupon getCoupon(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	/**
	 * Gets the all coupon.
	 *
	 * @return the all coupon
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getAllCoupon() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	/**
	 * Gets the coupon by type.
	 *
	 * @param couponType the coupon type
	 * @return the coupon by type
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCouponByType(CouponType couponType) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;

}
