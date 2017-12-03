package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import javaBeans.Coupon;
import javaBeans.CouponType;
import main.ConnectionPool;
import utilities.CompanySqlQuerys;
import utilities.CouponSqlQuerys;
import utilities.CustomerSqlQuerys;
import utilities.DateTranslate;

// TODO: Auto-generated Javadoc
/**
 * The Class CouponDBDAO.
 */
public class CouponDBDAO implements CouponDAO{
	
	/** The pool. */
	private ConnectionPool pool;

	/**
	 * Instantiates a new coupon DBDAO.
	 */
	public CouponDBDAO()
	{
		pool = ConnectionPool.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see dao.CouponDAO#createCoupon(javaBeans.Coupon)
	 */
	@Override
	public void createCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException,
	DuplicateEntryException, NullConnectionException {
		Connection tempConn = pool.getConnection();
		Statement  tempStatement = tempConn.createStatement();
		ResultSet  tempRs;
		tempRs = tempStatement.executeQuery(String.format(CouponSqlQuerys.COUPONS_BY_TITLE,coupon.getTitle()));
		if(tempRs.next())
		{
			//exception
			System.out.println("Coupon exist");
		}
		else
		{
			//creating the preparedStatement
			PreparedStatement tempPreparedStatement = tempConn.prepareStatement(CouponSqlQuerys.INSERT_COUPON);

			tempPreparedStatement.setString(1, coupon.getTitle());
			tempPreparedStatement.setString(2, DateTranslate.dateToString(coupon.getStartDate()));
			tempPreparedStatement.setString(3, DateTranslate.dateToString(coupon.getEndDate()));
			tempPreparedStatement.setInt(4, coupon.getAmount());
			tempPreparedStatement.setString(5, coupon.getMessage());
			tempPreparedStatement.setDouble(6, coupon.getPrice());
			tempPreparedStatement.setString(7, coupon.getImage());

			// execute the preparedStatement
			tempPreparedStatement.execute();		
		}

	}

	/* (non-Javadoc)
	 * @see dao.CouponDAO#removeCoupon(javaBeans.Coupon)
	 */
	@Override
	public void removeCoupon(Coupon coupon)
			throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException {
		// TODO Auto-generated method stub
		
		Connection tempConn = pool.getConnection();
		Statement  tempStatement = tempConn.createStatement();
		ResultSet  tempRs;
		tempRs = tempStatement.executeQuery(String.format(CouponSqlQuerys.COUPONS_BY_TITLE,coupon.getTitle()));
		if(tempRs.next())
		{
			//deleting the coupon from coupon table
			Statement  tempDeleteStatement = tempConn.createStatement();
			tempDeleteStatement.execute(String.format(CouponSqlQuerys.DELETE_COUPON_BY_ID,coupon.getId()));

			//deleting the coupon from company_coupon table
			tempDeleteStatement.execute(String.format(CompanySqlQuerys.DELETE_COUPON_WHERE_ID,coupon.getId()));

			//deleting the coupon from customer_coupon table
			tempDeleteStatement.execute(String.format(CustomerSqlQuerys.DELETE_COUPON_CUST_ID,coupon.getId()));

			pool.returnConnection(tempConn);
			System.out.println("coupon removed successfully");
		}
		else
		{
			System.out.println("Coupon not exist");

		}

	}

	/* (non-Javadoc)
	 * @see dao.CouponDAO#updateCoupon(javaBeans.Coupon)
	 */
	@Override
	public void updateCoupon(Coupon coupon)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		// TODO Auto-generated method stub

		Connection tempConn = pool.getConnection();
		Statement  tempStatement = tempConn.createStatement();
		ResultSet  tempRs;
		tempRs = tempStatement.executeQuery(String.format(CouponSqlQuerys.COUPONS_BY_TITLE,coupon.getTitle()));
		if(tempRs.next())
		{
			PreparedStatement preparedStmt = tempConn.prepareStatement(CouponSqlQuerys.UPDATE_COUPON);
			preparedStmt.setString (1, DateTranslate.dateToString(coupon.getEndDate()));
			preparedStmt.setDouble (2, coupon.getPrice());
			preparedStmt.setLong   (3, coupon.getId());    
			// execute the java prepared statement
			preparedStmt.executeUpdate();
			//returning the connection
			pool.returnConnection(tempConn);
			System.out.println("coupon has been updated!");
		}
		else
		{
			System.out.println("coupon not exist");
		}
	}

	/* (non-Javadoc)
	 * @see dao.CouponDAO#getCoupon(long)
	 */
	@Override
	public Coupon getCoupon(long id)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		
		//initializing the return variables
		Coupon coupon = new Coupon();
		//establishing the connection to the data base
		Connection con = pool.getConnection();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;
		// the mysql select statement for the correct coupon
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_ID, id));
		//adding the data from the mysql table to the correct members in the coupon instance
		while ( rs.next() )
		{
			coupon.setId(rs.getLong("id"));
			coupon.setTitle(rs.getString("title"));
			coupon.setStartDate(DateTranslate.stringToDate(rs.getString("start_date")));
			coupon.setEndDate(DateTranslate.stringToDate(rs.getString("end_date")));
			coupon.setAmount(rs.getInt("amount"));
			coupon.setType(CouponType.valueOf(rs.getString("type").trim()));
			coupon.setMessage(rs.getString("message"));
			coupon.setPrice(rs.getDouble("price"));
			coupon.setImage(rs.getString("image"));
		}
		//returning the connection
		pool.returnConnection(con);

		return coupon;
	}

	/* (non-Javadoc)
	 * @see dao.CouponDAO#getAllCoupon()
	 */
	@Override
	public Collection<Coupon> getAllCoupon()
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		
		ArrayList<Coupon> tempCouponArray = new ArrayList<>();

		Connection tempConn = pool.getConnection();
		Statement  tempStatement = tempConn.createStatement();
		ResultSet  tempRs;

		tempRs = tempStatement.executeQuery(CouponSqlQuerys.ALL_COUPONS);
		while(tempRs.next())
		{
			Coupon tempCoupon = getCoupon(tempRs.getInt("ID"));
			tempCouponArray.add(tempCoupon);
		}
		pool.returnConnection(tempConn);
		return tempCouponArray;
	}

	/* (non-Javadoc)
	 * @see dao.CouponDAO#getCouponByType(javaBeans.CouponType)
	 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType couponType)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {


		//establishing the connection to the data base
		Connection con = pool.getConnection();
		ArrayList<Coupon> CouponArr = new ArrayList<>();

		Statement stmt = (Statement) con.createStatement();
		ResultSet rs;      
		// the mysql select statement for the correct coupons
		rs = stmt.executeQuery(String.format(CouponSqlQuerys.ALL_COUPONS_BY_TYPE, couponType.toString()));      
		//adding the data from the sql table to the correct members in the company instance
		while ( rs.next() )
		{
			Coupon coupon = new Coupon();
			coupon.setId(rs.getLong("id"));
			coupon.setTitle(rs.getString("title"));
			coupon.setStartDate(DateTranslate.stringToDate(rs.getString("start_date")));
			coupon.setEndDate(DateTranslate.stringToDate(rs.getString("end_date")));
			coupon.setAmount(rs.getInt("amount"));
			coupon.setType(CouponType.valueOf((rs.getString("type").trim())));
			coupon.setMessage(rs.getString("message"));
			coupon.setPrice(rs.getDouble("price"));
			coupon.setImage(rs.getString("image"));            
			//addind all the coupons to the ArrayList
			CouponArr.add(coupon);
		}
		//returning the connection
		pool.returnConnection(con);

		return CouponArr;
	}

}
