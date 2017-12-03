package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Company;
import javaBeans.Coupon;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO {
	
	/**
	 * Creates the company.
	 *
	 * @param company the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws DuplicateEntryException the duplicate entry exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void createCompany(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	DuplicateEntryException,
	NullConnectionException;
	
	/**
	 * Removes the comapny.
	 *
	 * @param company the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void removeComapny(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	/**
	 * Update company.
	 *
	 * @param company the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void updateCompany(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws ParseException the parse exception
	 */
	public Company getCompany(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException,
	ParseException;
	
	/**
	 * Gets the all companys.
	 *
	 * @return the all companys
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws NullConnectionException the null connection exception
	 * @throws ParseException the parse exception
	 */
	public Collection<Company> getAllCompanys() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException,
	ParseException;
	

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
	 * @param compName the comp name
	 * @param password the password
	 * @return true, if successful
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws WrongDataInputException the wrong data input exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean login(String compName, String password) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	WrongDataInputException,
	NullConnectionException;
	
	/**
	 * Gets the coupons by company id.
	 *
	 * @param id the id
	 * @return the coupons by company id
	 * @throws SQLException the SQL exception
	 */
	public Collection<Coupon> getCouponsByCompanyId(long id) throws SQLException;


}
