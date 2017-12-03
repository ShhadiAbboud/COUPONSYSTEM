package utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class CouponSqlQuerys.
 */
public class CouponSqlQuerys {
	
	/** The coupon id by comp id. */
	public static String COUPON_ID_BY_COMP_ID = "SELECT coupon_id FROM company_coupon WHERE comp_id = '%1s'";
	
	/** The coupon id by cust id. */
	public static String COUPON_ID_BY_CUST_ID = "SELECT coupon_id FROM customer_coupon WHERE cust_id = '%1s'";
	
	/** The delete coupon cust id. */
	public static String DELETE_COUPON_CUST_ID = "DELETE FROM customer_coupon WHERE coupon_id = '%1s'";
	
	/** The delete coupon comp id. */
	public static String DELETE_COUPON_COMP_ID = "DELETE FROM company_coupon WHERE comp_id = '%1s'";
	
	/** The all coupons by id. */
	public static String ALL_COUPONS_BY_ID = "SELECT * FROM coupon WHERE id = '%1s'";
	
	/** The all coupons by type. */
	public static String ALL_COUPONS_BY_TYPE = "SELECT * FROM coupon WHERE type = '%1s'";
	
	/** The all coupons. */
	public static String ALL_COUPONS = "SELECT * FROM coupon";
	
	/** The coupons by title. */
	public static String COUPONS_BY_TITLE = "SELECT * FROM coupon WHERE title = '%1s'";
	
	/** The insert coupon. */
	public static String INSERT_COUPON = " insert into coupon (title, start_date, end_date, amount, type, message, price, image)"
			+ " values (?, ?, ?, ? ,? ,? ,? ,?)";
	
	/** The insert coupon comp. */
	public static String INSERT_COUPON_COMP = " insert into company_coupon (comp_id, coupon_id)"
			+ " values (?, ?)";
	
	/** The delete coupon by id. */
	public static String DELETE_COUPON_BY_ID = "delete from coupon where id = ?";
	
	/** The delete coupon company by id. */
	public static String DELETE_COUPON_COMPANY_BY_ID = "delete from company_coupon where coupon_id = ?";
	
	/** The delete coupon customer by id. */
	public static String DELETE_COUPON_CUSTOMER_BY_ID = "delete from customer_coupon where coupon_id = ?";
	
	/** The delete coupon by coupon id. */
	public static String DELETE_COUPON_BY_COUPON_ID = "delete from coupon where id = '%1s'";
	
	/** The update coupon. */
	public static String UPDATE_COUPON = "update coupon set end_date = ?, price = ? where id = ?";
	
	/** The type by id. */
	public static String TYPE_BY_ID = "SELECT type FROM coupon WHERE id = '%1s'";
	
	/** The update coupon amount. */
	public static String UPDATE_COUPON_AMOUNT = "update coupon set amount = ? where id = ?";
	
	/** The all coupons by id and type. */
	public static String ALL_COUPONS_BY_ID_AND_TYPE = "SELECT * FROM coupon WHERE id = '%1s' and type LIKE '%2s'";
	
	/** The all coupons by id and price. */
	public static String ALL_COUPONS_BY_ID_AND_PRICE = "SELECT * FROM coupon WHERE id = '%1s' and price <= '%2s'";

}
