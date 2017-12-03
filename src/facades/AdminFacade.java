package facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import dao.CompanyDBDAO;
import dao.CustomerDBDAO;
import exceptions.CompanyException;
import exceptions.CustomerException;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import javaBeans.Company;
import javaBeans.Customer;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminFacade.
 */
public class AdminFacade implements CouponClientFacade {

	/** The companydbdao. */
	private CompanyDBDAO companydbdao;
	
	/** The customerdbdao. */
	private CustomerDBDAO customerdbdao;


	/**
	 * Instantiates a new admin facade.
	 */
	public AdminFacade()
	{
		this.companydbdao = new CompanyDBDAO();
		this.customerdbdao = new CustomerDBDAO();
	}

	/**
	 * Creates the company.
	 *
	 * @param company the company
	 */
	public void createCompany(Company company)
	{
		
			try {
				companydbdao.createCompany(company);
			} 
			catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateEntryException
					| NullConnectionException e) 
			{
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
		
	}
	
	/**
	 * Removes the company.
	 *
	 * @param company the company
	 */
	public void removeCompany(Company company)
	{
			try {
				companydbdao.removeComapny(company);
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
			
		
	}

	/**
	 * Update company.
	 *
	 * @param company the company
	 */
	public void updateCompany(Company company)
	{
		
			try {
				companydbdao.updateCompany(company);
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
		
	}
	
	/**
	 * Gets the all companies.
	 *
	 * @return the all companies
	 */
	public Collection<Company> getAllCompanies()
	{
		ArrayList<Company> arr = new ArrayList<>();
		
		
			try
			{
				arr = (ArrayList<Company>) companydbdao.getAllCompanys();
			}
			catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
					| ParseException e) 
			{
				new CompanyException(e);
			}
		
		
		return arr ;
		
	}

	/**
	 * Gets the company.
	 *
	 * @param id the id
	 * @return the company
	 */
	public Company getCompany(long id)
	{
		Company cHCompany = new Company();
		
			try 
			{
				cHCompany =companydbdao.getCompany(id);
			}
			catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException
					| ParseException e) 
			{
					new CompanyException(e);
			}
		
		return cHCompany;
		
	}

	/**
	 * Creates the customer.
	 *
	 * @param customer the customer
	 */
	public void createCustomer(Customer customer)
	{
		
			try {
				customerdbdao.createCustomer(customer);
			} catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateEntryException
					| NullConnectionException e) {
				// TODO Auto-generated catch block
				new CompanyException(e);
			}
	
	}

	/**
	 * Removes the customer.
	 *
	 * @param customer the customer
	 */
	public void removeCustomer(Customer customer)
	{
		
			try {
				customerdbdao.removeCustomer(customer);
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		
	}
	

	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 */
	public void updateCustomer(Customer customer)
	{
		
			try {
				customerdbdao.updateCustomer(customer);
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		
	}

	/**
	 * Gets the all customer.
	 *
	 * @return the all customer
	 */
	public Collection<Customer> getAllCustomer()
	{
		ArrayList<Customer> arra = new ArrayList<>();
		
			try {
				arra = (ArrayList<Customer>) customerdbdao.getAllCustomer();
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		
		return arra;
		
	}
	
	/**
	 * Gets the customer.
	 *
	 * @param id the id
	 * @return the customer
	 */
	public Customer getCustomer(long id)
	{
		Customer cus = new Customer();
			try {
				cus = customerdbdao.getCustomer(id);
			} catch (ClassNotFoundException | InterruptedException | SQLException | NullConnectionException e) {
				// TODO Auto-generated catch block
				new CustomerException(e);
			}
		
		return cus ;
	}
	
	/* (non-Javadoc)
	 * @see facades.CouponClientFacade#login(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CouponClientFacade login(String name, String password,  String clientType)
	{

		if (name.equals("admin") && password.equals("1234"))
		{
			return this;
		}

		return null;	
	}

}