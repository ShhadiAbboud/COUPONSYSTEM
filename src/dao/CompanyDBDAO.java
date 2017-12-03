package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Company;
import javaBeans.Coupon;
import javaBeans.CouponType;
import main.ConnectionPool;
import utilities.CompanySqlQuerys;
import utilities.CouponSqlQuerys;
import utilities.DateTranslate;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanyDBDAO.
 */
public class CompanyDBDAO implements CompanyDAO 
{
	
	/** The pool. */
	private ConnectionPool pool;
	
	/** The id. */
	private long id ;
	
	/** The all companys. */
	ArrayList<Company> allCompanys = new ArrayList<>();
	
	/** The all coupons. */
	ArrayList<Coupon> allCoupons = new ArrayList<>();
	
	

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Instantiates a new company DBDAO.
	 */
	public CompanyDBDAO()
	{
		pool = ConnectionPool.getInstance();
		
	}

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#createCompany(javaBeans.Company)
	 */
	@Override
	public void createCompany(Company company) throws ClassNotFoundException, InterruptedException, SQLException,
			DuplicateEntryException, NullConnectionException 
	{
		//establishing the connection to the data base
		Connection con = pool.getConnection();

		Statement testStmt = con.createStatement();
		ResultSet testRs;
		//the mysql statement to check if there is a company by that name in my database
		testRs = testStmt.executeQuery(String.format(CompanySqlQuerys.SELECT_ALL_COMP_NAME, company.getCompName()));
		
		if (testRs.next())
		{
			throw new DuplicateEntryException();
		}
		else
		{
			// the mysql insert statement
			String query = CompanySqlQuerys.INSERT_COMPANY;
			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt =  con.prepareStatement(query);
			preparedStmt.setString (1, company.getCompName());
			preparedStmt.setString (2, company.getPassword());
			preparedStmt.setString (3, company.getEmail());	 
			// execute the preparedstatement
			preparedStmt.execute();
			System.out.println("company " + company.getCompName() + " has been added to the database");
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);
	}
		
	

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#removeComapny(javaBeans.Company)
	 */
	@Override
	public void removeComapny(Company company)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {

		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		//the mysql statement to check if there is a company by that name in my database
		rs = stmt.executeQuery(String.format(CompanySqlQuerys.SELECT_COMPANY_COMP_ID, company.getId()));
		if (rs.next())
		{

			long compId = company.getId();
			//the mysql statement that removes the company from the company table in the database
			Statement deleteCompany = (Statement) con.createStatement();
			deleteCompany.execute(String.format(CompanySqlQuerys.DELETE_COMPANY_ID, compId ));	 
			Statement getCouponsStmt = (Statement) con.createStatement();
			ResultSet newRs;
			newRs = getCouponsStmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_COMP_ID, company.getId()));
			while (newRs.next())
			{
				long id = newRs.getLong("coupon_id");
				//the mysql statement that removes the company's coupons from the coupon table in the database
				Statement delete = (Statement) con.createStatement();
				delete.execute(String.format(CouponSqlQuerys.DELETE_COUPON_BY_COUPON_ID, id ));
				delete.execute(String.format(CouponSqlQuerys.DELETE_COUPON_CUST_ID, id ));
				delete.execute(String.format(CouponSqlQuerys.DELETE_COUPON_COMP_ID, company.getId()));
			}
			//returning the connection
			ConnectionPool.getInstance().returnConnection(con);
			System.out.println("company " + company.getCompName() + " has been removerd");		    		
		}
		else
		{
			System.out.println("compnay " + company.getCompName() + " does not exist in the database");
		}
	}

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#updateCompany(javaBeans.Company)
	 */
	@Override
	public void updateCompany(Company company)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException {

		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		//the mysql statement to check if there is a company by that name in my database
		rs = stmt.executeQuery(String.format(CompanySqlQuerys.SELECT_COMPANY_COMP_ID, company.getId()));
		if (rs.next())
		{
		// create the mysql update preparedstatement
		
		String query = CompanySqlQuerys.UPDATE_COMPANY_BY_ID;
		PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
		preparedStmt.setString   (1, company.getPassword());
		preparedStmt.setString   (2, company.getEmail());
		preparedStmt.setLong     (3, company.getId());
		// execute the java preparedstatement
		preparedStmt.executeUpdate();
		//returning the connections
		ConnectionPool.getInstance().returnConnection(con);
		System.out.println("company " + company.getCompName() + " has been updated");}
		else{
			System.out.println("company " + company.getCompName() + "the company does not exist");
			}

		}
	

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#getCompany(long)
	 */
	@Override
	public Company getCompany(long id)
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException {
		//getting a connection
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		// the mysql select statement for the correct company
		rs = stmt.executeQuery(String.format(CompanySqlQuerys.SELECT_COMPANY_COMP_ID, id ));
		Company comp = new Company();
		//adding the data from the sql table to the correct members in the company instance
		while ( rs.next() )
		{
			comp.setId(rs.getLong("id"));
			comp.setCompName(rs.getString("comp_name"));
			comp.setPassword(rs.getString("password"));
			comp.setEmail(rs.getString("email"));
		}
		comp.setCoupons(getCouponsByCompanyId(comp.getId()));
		
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);


		return comp;
		
	}

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#getAllCompanys()
	 */
	@Override
	public Collection<Company> getAllCompanys()
			throws ClassNotFoundException, InterruptedException, SQLException, NullConnectionException, ParseException {
		//initializing the return variables		
		allCompanys.removeAll(allCompanys);
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for all companys
		rs = stmt.executeQuery(CompanySqlQuerys.SELECT_ALL_COMPANYS);      
		//adding the data from the sql table to the correct members in the company instance
		while ( rs.next() )
		{
			Company comp = new Company();
			comp.setId(rs.getLong("id"));
			comp.setCompName(rs.getString("comp_name"));
			comp.setPassword(rs.getString("password"));
			comp.setEmail(rs.getString("email"));                
			//addind all the companys to the ArrayList
			allCompanys.add(comp);
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);
		for (Company c : allCompanys)
		{
			this.setId(c.getId());
			c.setCoupons(this.getCouponsByCompanyId(c.getId()));
		}

		return allCompanys;
	}

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#getCoupons()
	 */
	@Override
	public Collection<Coupon> getCoupons()
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {

		//initializing the return variables
		allCoupons.removeAll(allCoupons);
		ArrayList<Long> couponId = new ArrayList<>(); 
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		// the mysql select statement for all of the company's coupons id 
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_COMP_ID, this.getId()));
		while(rs.next())
		{
			couponId.add(rs.getLong("COUPON_ID"));
		}
		//a for loop that adds all of the coupons that the company has created
		for (int i=0;i<couponId.size();i++)
		{
			Statement addStmt = (Statement) con.createStatement();
			ResultSet addRs;
			addRs = addStmt.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID, couponId.get(i)));
			while ( addRs.next() )
			{
				Coupon coupon = new Coupon();
				coupon.setId(addRs.getLong("id"));
				coupon.setTitle(addRs.getString("title"));
				coupon.setStartDate(DateTranslate.stringToDate(addRs.getString("start_date")));
				coupon.setEndDate(DateTranslate.stringToDate(addRs.getString("end_date")));
				coupon.setAmount(addRs.getInt("amount"));
				coupon.setType(CouponType.valueOf((addRs.getString("type").trim())));
				coupon.setMessage(addRs.getString("message"));
				coupon.setPrice(addRs.getDouble("price"));
				coupon.setImage(addRs.getString("image"));

				allCoupons.add(coupon);
			}

		}

		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;  
	}

	/* (non-Javadoc)
	 * @see dao.CompanyDAO#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String compName, String password) throws ClassNotFoundException, InterruptedException,
			SQLException, WrongDataInputException, NullConnectionException {

		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		// the mysql select statement to check if i have a row where the password and the compname match the
		//input data
		rs = stmt.executeQuery(String.format(CompanySqlQuerys.COMPANY_BY_PASSWORD, password, compName ));
		if(rs.next())
		{
			//sets the userCompanyId for later methods use	        	
			this.setId(rs.getLong("id"));
			//returning the connection
			pool.returnConnection(con);

			return true;
		}
		else
		{
			pool.returnConnection(con);
			throw new WrongDataInputException();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see dao.CompanyDAO#getCouponsByCompanyId(long)
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyId(long id) throws SQLException
	{
		ArrayList<Coupon> SCouponsArray = new ArrayList<>();

		//getting connection from the pool
		Connection SConn = pool.getConnection();
		Statement  SStatement = (Statement) SConn.createStatement();
		ResultSet  SResultSet;
		
		SResultSet = SStatement.executeQuery("SELECT COUPON_ID FROM company_coupon WHERE COMP_ID = '"+id+"'");
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
	 * Gets the company coupon by type.
	 *
	 * @param couponType the coupon type
	 * @return the company coupon by type
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCompanyCouponByType(CouponType couponType) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initializing the return variables
		allCoupons.removeAll(allCoupons);
		ArrayList<Long> couponId = new ArrayList<>(); 
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for all of the company's coupons id 
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_COMP_ID, this.id));    
		while(rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));
		}
		//a for loop that adds all of the coupons that the company has created
		for (int i=0;i<couponId.size();i++)
		{
			Statement stmt1 = (Statement) con.createStatement();
			ResultSet addRs;      
			// the mysql select statement for the correct coupons
			addRs = stmt1.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID_AND_TYPE, couponId.get(i), couponType.toString()));     		        
			//adding the data from the sql table to the correct members in the coupon instance
			while ( addRs.next() )
			{
				Coupon coupon = new Coupon();
				coupon.setId(addRs.getLong("id"));
				coupon.setTitle(addRs.getString("title"));
				coupon.setStartDate(DateTranslate.stringToDate(addRs.getString("start_date")));
				coupon.setEndDate(DateTranslate.stringToDate(addRs.getString("end_date")));
				coupon.setAmount(addRs.getInt("amount"));
				coupon.setType(CouponType.valueOf((addRs.getString("type").trim())));
				coupon.setMessage(addRs.getString("message"));
				coupon.setPrice(addRs.getDouble("price"));
				coupon.setImage(addRs.getString("image"));            
				//adding all the coupons to the ArrayList
				allCoupons.add(coupon);
			}
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;

	}
	
	/**
	 * Gets the company coupon by price.
	 *
	 * @param price the price
	 * @return the company coupon by price
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCompanyCouponByPrice(double price) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initializing the return variables
		allCoupons.removeAll(allCoupons);
		ArrayList<Long> couponId = new ArrayList<>();
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for all of the company's coupons id 
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_COMP_ID, this.id));       
		while(rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));

		}
		//a for loop that adds all of the coupons that the company has created
		for (int i=0;i<couponId.size();i++)
		{
			Statement stmt1 = (Statement) con.createStatement();
			ResultSet addRs; 
			// the mysql select statement for the correct coupons
			addRs = stmt1.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID_AND_PRICE, couponId.get(i), price ));        
			//adding the data from the sql table to the correct members in the coupon instance
			while ( addRs.next() )
			{
				Coupon coupon = new Coupon();
				coupon.setId(addRs.getLong("id"));
				coupon.setTitle(addRs.getString("title"));
				coupon.setStartDate(DateTranslate.stringToDate(addRs.getString("start_date")));
				coupon.setEndDate(DateTranslate.stringToDate(addRs.getString("end_date")));
				coupon.setAmount(addRs.getInt("amount"));
				coupon.setType(CouponType.valueOf((addRs.getString("type").trim())));
				coupon.setMessage(addRs.getString("message"));
				coupon.setPrice(addRs.getDouble("price"));
				coupon.setImage(addRs.getString("image"));            
				//addind all the coupons to the ArrayList
				allCoupons.add(coupon);
			}
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;
	}
	
	/**
	 * Gets the company coupon by date.
	 *
	 * @param date the date
	 * @return the company coupon by date
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 * @throws NullConnectionException the null connection exception
	 */
	public Collection<Coupon> getCompanyCouponByDate(Date date) throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException
	{
		//initializing the return variables
		allCoupons.removeAll(allCoupons);
		ArrayList<Long> couponId = new ArrayList<>();
		//establishing the connection to the data base
		Connection con = (Connection) ConnectionPool.getInstance().getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for all of the company's coupons id 
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.COUPON_ID_BY_COMP_ID, this.getId()));      
		while(rs.next())
		{
			couponId.add(rs.getLong("coupon_id"));
		}
		//a for loop that adds all of the coupons that the company has created
		for (int i=0;i<couponId.size();i++)
		{
			Statement stmt1 = (Statement) con.createStatement();
			ResultSet addRs;      
			// the mysql select statement for the correct coupon
			addRs = stmt1.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID, couponId.get(i)));
			//adding the data from the sql table to the correct members in the coupon instance
			while ( addRs.next() )
			{
				if (DateTranslate.stringToDate(addRs.getString("end_date")).before(date))
				{
					Coupon coupon = new Coupon();
					coupon.setId(addRs.getLong("id"));
					coupon.setTitle(addRs.getString("title"));
					coupon.setStartDate(DateTranslate.stringToDate(addRs.getString("start_date")));
					coupon.setEndDate(DateTranslate.stringToDate(addRs.getString("end_date")));
					coupon.setAmount(addRs.getInt("amount"));
					coupon.setType(CouponType.valueOf((addRs.getString("type").trim())));
					coupon.setMessage(addRs.getString("message"));
					coupon.setPrice(addRs.getDouble("price"));
					coupon.setImage(addRs.getString("image"));        
					//addind all the coupons to the ArrayList
					allCoupons.add(coupon);
				}
			}
		}
		//returning the connection
		ConnectionPool.getInstance().returnConnection(con);

		return allCoupons;
	}
}
