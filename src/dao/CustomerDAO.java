package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import javaBeans.Coupon;
import javaBeans.Customer;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerDAO.
 */
public interface CustomerDAO {
	
	/**
	 * Creates the customer.
	 *
	 * @param customer the customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void createCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	DuplicateEntryException,
	NullConnectionException;
	
	/**
	 * Removes the customer.
	 *
	 * @param customer the customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void removeCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void updateCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;

	/**
	 * Gets the customer.
	 *
	 * @param id the id
	 * @return the customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Customer getCustomer(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	/**
	 * Gets the all customer.
	 *
	 * @return the all customer
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Customer> getAllCustomer() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;

	/**
	 * Gets the coupons.
	 *
	 * @return the coupons
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCoupons() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	/**
	 * Login.
	 *
	 * @param custName the cust name
	 * @param password the password
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws WrongDataInputException the wrong data input exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean login(String custName, String password) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	WrongDataInputException,
	NullConnectionException;
	
	

	/**
	 * Gets the coupons by customer id.
	 *
	 * @param id the id
	 * @return the coupons by customer id
	 * @throws SQLException the SQL exception
	 */
	public Collection<Coupon> getCouponsByCustomerId(long id) throws SQLException;

}
