package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.jdbc.Statement;

import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Coupon;
import javaBeans.CouponType;
import javaBeans.Customer;
import main.ConnectionPool;
import utilities.CompanySqlQuerys;
import utilities.CouponSqlQuerys;
import utilities.CustomerSqlQuerys;
import utilities.DateTranslate;

public class CustomerDBDAO implements CustomerDAO{
	
	ArrayList<Customer> allCustomers = new ArrayList<>();
	ArrayList<Coupon> allCoupons = new ArrayList<>();
	private long userCustomerId;
	private ConnectionPool pool;
	
	public CustomerDBDAO()
	{
		pool = ConnectionPool.getInstance();
	}

	@Override
	public void createCustomer(Customer customer) throws ClassNotFoundException, InterruptedException, SQLException,
			DuplicateEntryException, NullConnectionException {

		//establishing the connection to the data base
		Connection con = (Connection) pool.getConnection();

		Statement testStmt = (Statement) con.createStatement();
		ResultSet testRs;
		//the mysql statement to check if there is a customer by that name with the same password in my database
		testRs = testStmt.executeQuery(String.format(CustomerSqlQuerys.ALL_CUSTOMER_BY_NAME, customer.getCustName()));
		if (testRs.next())
		{
			throw new DuplicateEntryException();
		}
		else
		{
			
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(CustomerSqlQuerys.INSERT_CUSTOMER);
			preparedStmt.setString (1, customer.getCustName());
			preparedStmt.setString (2, customer.getPassword());  
			// execute the preparedstatement
			preparedStmt.execute();
		}
		//returning the connection
		pool.returnConnection(con);
		System.out.println("customer " + customer.getCustName() + " has been added");
		
	}

	@Override
	public void removeCustomer(Customer customer)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {
		//establishing the connection to the data base
		Connection con = (Connection) pool.getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet CH;
		//a mysql statement that checks if the customer exist in the database
		CH = stmt.executeQuery(CustomerSqlQuerys.SELECT_CUSTOMER_BY_NAME + customer.getCustName() +"'");
		// mysql statements that removes the customer and all his purchased coupos from the database
		if(CH.next()){
		stmt.execute(String.format(CustomerSqlQuerys.DELET_BY_CUST_NAME, customer.getCustName()));
		stmt.execute(String.format(CustomerSqlQuerys.DELET_BY_CUST_ID, customer.getId())); 
		System.out.println("customer " + customer.getCustName() + " has been removed");
		}
		else
		{
			System.out.println("customer " + customer.getCustName() + "does not exist in the database");

		}
		//returning the connection
		pool.returnConnection(con);
		
	}

	@Override
	public void updateCustomer(Customer customer)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {
		//establishing the connection to the data base
		Connection con = (Connection) pool.getConnection();
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		//the mysql statement to check if there is a company by that name in my database
		rs = stmt.executeQuery(String.format(CompanySqlQuerys.SELECT_COMPANY_COMP_ID, customer.getId()));
		if (rs.next())
		{
		// create the java mysql update preparedstatement
		String query = CustomerSqlQuerys.UPDATE_CUSTOMER;
		PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		preparedStmt.setString   (1, customer.getPassword());
		preparedStmt.setLong     (2, customer.getId());
		// execute the java preparedstatement
		preparedStmt.executeUpdate();
		//returning the connection
		pool.returnConnection(con);
		System.out.println("customer " + customer.getCustName() + " has been updated");
		}
		else {
			System.out.println("customer " + customer.getCustName() + " the user does not exist");

		}
	}

	@Override
	public Customer getCustomer(long id)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {
		//Initializing the return variables
		Customer customer = new Customer();
		//establishing the connection to the data base
		Connection con = (Connection) pool.getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		// the mysql select statement for the correct customer
		rs = stmt.executeQuery(String.format(CustomerSqlQuerys.ALL_CUSTOMER_BY_ID, id ));
		//adding the data from the sql table to the correct members in the customer instance
		while ( rs.next() )
		{
			customer.setId(rs.getLong("id"));
			customer.setCustName(rs.getString("cust_name"));
			customer.setPassword(rs.getString("password"));
			customer.setCoupons(getCouponsByCustomerId(customer.getId()));
		}
		//returning the connection
		pool.returnConnection(con);

		return customer;
	}

	@Override
	public Collection<Customer> getAllCustomer()
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {
		//initializing the return variables		
		allCustomers.removeAll(allCustomers);
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for all companys
		rs = stmt.executeQuery(CustomerSqlQuerys.SELECT_ALL_CUSTOMER);      
		//adding the data from the sql table to the correct members in the company instance
		while ( rs.next() )
		{
			Customer cust = new Customer();
			cust.setId(rs.getLong("id"));
			cust.setCustName(rs.getString("comp_name"));
			cust.setPassword(rs.getString("password"));               
			//addind all the companys to the ArrayList
			allCustomers.add(cust);
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);
		for (Customer c : allCustomers)
		{
			c.setCoupons(this.getCouponsByCustomerId(c.getId()));
		}

		return allCustomers;
	}

	@Override
	public Collection<Coupon> getCoupons()
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		
		return getCouponsByCustomerId(getUserCustomerId());
		
	}

	@Override
	public boolean login(String custName, String password) throws ClassNotFoundException, InterruptedException,
			SQLException, WrongDataInputException, NullConnectionException {
				//establishing the connection to the data base
		Connection cust = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) cust.createStatement();
		ResultSet rs;
		// the mysql select statement to check if i have a row where the password and the compname match the
		//input data
		rs = stmt.executeQuery(String.format(CustomerSqlQuerys.CUSTOMER_BY_PASSWORD, password, custName ));
		if(rs.next())
		{
			//sets the userCompanyId for later methods use	        	
			setUserCustomerId((rs.getLong("id")));
			//returning the connection
			pool.returnConnection(cust);

			return true;
		}
		else
		{
			pool.returnConnection(cust);
			throw new WrongDataInputException();
		}

	
	}
	public long getUserCustomerId()
	{
		return userCustomerId;
	}
	public void setUserCustomerId(long userCustomerId)
	{
		this.userCustomerId = userCustomerId;
	}

	@Override
	public Collection<Coupon> getCouponsByCustomerId(long id) throws SQLException {
		ArrayList<Coupon> SCouponsArray = new ArrayList<>();

		//getting connection from the pool
		Connection SConn = pool.getConnection();
		Statement  SStatement = (Statement) SConn.createStatement();
		ResultSet  SResultSet;
		
		SResultSet = SStatement.executeQuery(String.format(CustomerSqlQuerys.COUPON_ID_BY_CUSTOMERID, id));
		try
		{
			while (SResultSet.next())
			{
				Statement  SStatement2 = (Statement) SConn.createStatement();
				ResultSet  SResultSet2;
				SResultSet2 = SStatement2.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID,SResultSet.getInt("COUPON_ID")));

				while (SResultSet2.next())
				{
					Coupon SCoupon = new Coupon();
					SCoupon.setId(SResultSet2.getInt("ID"));
					SCoupon.setTitle(SResultSet2.getString("TITLE"));

					// getting dates and converting them using converter class to format yy-MM-dd 
					SCoupon.setStartDate(DateTranslate.stringToDate(SResultSet2.getString("START_DATE")));
					SCoupon.setEndDate(DateTranslate.stringToDate(SResultSet2.getString("END_DATE")));
					SCoupon.setAmount(SResultSet2.getInt("AMOUNT"));
					SCoupon.setType(CouponType.valueOf(SResultSet2.getString("TYPE").trim()));
					SCoupon.setMessage(SResultSet2.getString("MESSAGE"));
					SCoupon.setPrice(SResultSet2.getDouble("PRICE"));
					SCoupon.setImage(SResultSet2.getString("IMAGE"));

					SCouponsArray.add(SCoupon);
				}
			}

			//returning the connection to the pool
			pool.returnConnection(SConn);
		}
		catch (Exception e)
		{
	
		}
		return SCouponsArray;
	}

}
