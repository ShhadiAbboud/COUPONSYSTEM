package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.mysql.jdbc.Statement;

import exceptions.DuplicateCouponTypeException;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.UnAvailableCouponException;
import exceptions.WrongDataInputException;
import javaBeans.Coupon;
import javaBeans.CouponType;
import javaBeans.Customer;
import main.ConnectionPool;
import utilities.CompanySqlQuerys;
import utilities.CouponSqlQuerys;
import utilities.CustomerSqlQuerys;
import utilities.DateTranslate;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerDBDAO.
 */
public class CustomerDBDAO implements CustomerDAO{
	
	/** The all customers. */
	ArrayList<Customer> allCustomers = new ArrayList<>();
	
	/** The all coupons. */
	ArrayList<Coupon> allCoupons = new ArrayList<>();
	
	/** The user customer id. */
	private long userCustomerId;
	
	/** The pool. */
	private ConnectionPool pool;
	
	/**
	 * Instantiates a new customer DBDAO.
	 */
	public CustomerDBDAO()
	{
		pool = ConnectionPool.getInstance();
	}

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#createCustomer(javaBeans.Customer)
	 */
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

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#removeCustomer(javaBeans.Customer)
	 */
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

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#updateCustomer(javaBeans.Customer)
	 */
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

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#getCustomer(long)
	 */
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

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#getAllCustomer()
	 */
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

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#getCoupons()
	 */
	@Override
	public Collection<Coupon> getCoupons()
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		
		return getCouponsByCustomerId(getUserCustomerId());
		
	}

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#login(java.lang.String, java.lang.String)
	 */
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
	
	/**
	 * Gets the user customer id.
	 *
	 * @return the user customer id
	 */
	public long getUserCustomerId()
	{
		return userCustomerId;
	}
	
	/**
	 * Sets the user customer id.
	 *
	 * @param userCustomerId the new user customer id
	 */
	public void setUserCustomerId(long userCustomerId)
	{
		this.userCustomerId = userCustomerId;
	}

	/* (non-Javadoc)
	 * @see dao.CustomerDAO#getCouponsByCustomerId(long)
	 */
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
	
	/**
	 * Gets the all purchased coupons by type.
	 *
	 * @param coupontype the coupontype
	 * @return the all purchased coupons by type
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public ArrayList<Coupon> getAllPurchasedCouponsByType(CouponType coupontype) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initializing the method variables
		ArrayList<Long> couponId = new ArrayList<>();
		allCoupons.removeAll(allCoupons);
		//Establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		//a mysql statement that adds all of the purchased coupons id
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_CUST_ID, this.getUserCustomerId() ));
		while (rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));
		}
		//a for loop with a mysql statement that adds all of the purchased coupons with the input couponType to an ArrayList
		for (int i=0;i<couponId.size();i++)
		{
			ResultSet newrs;	
			Statement stmtN = (Statement) con.createStatement();
			newrs = stmtN.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID_AND_TYPE, couponId.get(i), coupontype.toString() ));
			if (newrs.next())
			{
				Coupon coupon = new Coupon();
				coupon.setId(newrs.getLong("id"));
				coupon.setTitle(newrs.getString("title"));
				coupon.setStartDate(DateTranslate.stringToDate(newrs.getString("start_date")));
				coupon.setEndDate(DateTranslate.stringToDate(newrs.getString("end_date")));
				coupon.setAmount(newrs.getInt("amount"));
				coupon.setType(CouponType.valueOf(newrs.getString("type").trim()));
				coupon.setMessage(newrs.getString("message"));
				coupon.setPrice(newrs.getDouble("price"));
				coupon.setImage(newrs.getString("image"));
				allCoupons.add(coupon);

			}
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;
	}
	
	/**
	 * Gets the all purchased coupons by price.
	 *
	 * @param price the price
	 * @return the all purchased coupons by price
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public ArrayList<Coupon> getAllPurchasedCouponsByPrice(double price) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initializing the method variables
		ArrayList<Long> couponId = new ArrayList<>();
		allCoupons.removeAll(allCoupons);	
		//Establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		//a mysql statement that adds all of the purchased coupons id
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_CUST_ID, this.getUserCustomerId() ));
		while (rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));					
		} 
		//a for loop with a mysql statement that adds all of the purchased coupons with a price that is lower or equal to the input price to an ArrayList
		for (int i=0;i<couponId.size();i++)
		{
			ResultSet newrs;					
			newrs = stmt.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID_AND_PRICE, couponId.get(i), price ));
			while (newrs.next())
			{
				Coupon coupon = new Coupon();
				coupon.setId(newrs.getLong("id"));
				coupon.setTitle(newrs.getString("title"));
				coupon.setStartDate(DateTranslate.stringToDate(newrs.getString("start_date")));
				coupon.setEndDate(DateTranslate.stringToDate(newrs.getString("end_date")));
				coupon.setAmount(newrs.getInt("amount"));
				coupon.setType(CouponType.valueOf(newrs.getString("type").trim()));
				coupon.setMessage(newrs.getString("message"));
				coupon.setPrice(newrs.getDouble("price"));
				coupon.setImage(newrs.getString("image"));       
				allCoupons.add(coupon);

			}
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;

	}
	
	
	/**
	 * Purchase coupon.
	 *
	 * @param coupon the coupon
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws DuplicateCouponTypeException the duplicate coupon type exception
	 * @throws UnAvailableCouponException the un available coupon exception
	 * @throws NullConnectionException the null connection exception
	 */
	public void purchaseCoupon(Coupon coupon) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, DuplicateCouponTypeException, UnAvailableCouponException, NullConnectionException
	{
		boolean canPurchase = false;
		canPurchase = this.validCoupon(coupon);
		canPurchase = this.validateCouponType(coupon);
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		if (canPurchase==true)
		{
			//sql statement that updates the available amount of the coupon
			String updateQuery = CouponSqlQuerys.UPDATE_COUPON_AMOUNT;		
			PreparedStatement updateStmt = (PreparedStatement) con.prepareStatement(updateQuery);
			updateStmt.setInt   (1, (coupon.getAmount()-1));
			updateStmt.setLong  (2, coupon.getId());
			updateStmt.executeUpdate();
			// sql statement that adds the purchased coupon's id and the customer's id to the customer-coupon table
			String query = CustomerSqlQuerys.INSERT_CUSTOMER_COUPON;	  
			PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
			preparedStmt.setLong (1, this.getUserCustomerId());
			preparedStmt.setLong (2, coupon.getId());
			// execute the preparedstatement
			preparedStmt.execute();     
			//returning the connection
			ConnectionPool.getInstance().returnConnection(con);
			System.out.println("coupon has been purchased");
		}
		else
		{
			System.out.println("cannot purchase coupon " + coupon.getTitle());
		}
	}
	
	/**
	 * Valid coupon.
	 *
	 * @param coupon the coupon
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws UnAvailableCouponException the un available coupon exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean validCoupon(Coupon coupon) throws SQLException, ParseException, UnAvailableCouponException, NullConnectionException 
	{
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();
		Date today = Calendar.getInstance().getTime();
		Date validDate = null;
		int availableAmount = 0;

		Statement stmt = (Statement) con.createStatement();		
		//sql statement that checks if the coupon been purchased is actually available for purchasing
		ResultSet checkAvailability;
		checkAvailability = stmt.executeQuery(String.format(CustomerSqlQuerys.AMOUNT_AND_END_DATE_BY_ID, coupon.getId() ));
		if (checkAvailability.next())
		{
			availableAmount = checkAvailability.getInt("amount");
			validDate = (Date) DateTranslate.stringToDate(checkAvailability.getString("end_date"));
		}

		if( availableAmount>0 && today.before(validDate) )
		{
			ConnectionPool.getInstance().returnConnection(con);
			return true;
		}
		else
		{
			ConnectionPool.getInstance().returnConnection(con);
			throw new UnAvailableCouponException("customer " + this.getUserCustomerId() + " tried to purchse a coupon that is"
					+ " either out of date or it's available amount is 0");
		}
	}
	
	/**
	 * Validate coupon type.
	 *
	 * @param coupon the coupon
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 * @throws DuplicateCouponTypeException the duplicate coupon type exception
	 * @throws NullConnectionException the null connection exception
	 */
	public boolean validateCouponType(Coupon coupon) throws SQLException, DuplicateCouponTypeException, NullConnectionException
	{
		ArrayList<Long> couponId = new ArrayList<>();
		ArrayList<CouponType> coupontype = new ArrayList<>();
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		//sql statement that gather all of the current customer's coupons's id
		ResultSet rs;
		Statement stmtP = (Statement) con.createStatement();
		rs = stmtP.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_CUST_ID, this.getUserCustomerId() ));
		while (rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));					
		}
		for (int i=0;i<couponId.size();i++)
		{
			ResultSet newrs;
			Statement stmtN = (Statement) con.createStatement();
			newrs = stmtN.executeQuery(String.format(CouponSqlQuerys.TYPE_BY_ID, couponId.get(i) ));
			while (newrs.next())
			{
				coupontype.add(CouponType.valueOf((newrs.getString("type").trim())));
			}
		}
		//a for loop that checks if the current customer already has a coupon of the same type as the one he is trying to buy
		for (int i=0;i<coupontype.size();i++)
		{
			if(coupontype.get(i)==coupon.getCoupontype())
			{
				ConnectionPool.getInstance().returnConnection(con);
				throw new DuplicateCouponTypeException("customer " + this.getUserCustomerId() + " tried to purchse a coupon of a type that he already has");
			}
		}
		ConnectionPool.getInstance().returnConnection(con);
		
		return true;

	}

}
