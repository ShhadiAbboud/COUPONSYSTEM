package utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerSqlQuerys.
 */
public class CustomerSqlQuerys {
	
	/** The all customers. */
	public static String ALL_CUSTOMERS = "SELECT * FROM customer";
	
	/** The all customer by name. */
	public static String ALL_CUSTOMER_BY_NAME = "SELECT * FROM customer WHERE cust_name = '%1s' ";
	
	/** The all customer by id. */
	public static String ALL_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = '%1s'";
	
	/** The insert customer. */
	public static String INSERT_CUSTOMER = " insert into customer (cust_name, password)"
			+ " values (?, ?)";
	
	/** The select customer by name. */
	public static String SELECT_CUSTOMER_BY_NAME = "SELECT * FROM customer WHERE cust_name ='";
	
	/** The delet by cust name. */
	public static String DELET_BY_CUST_NAME = "DELETE FROM customer WHERE cust_name = '%1s'";
	
	/** The delet by cust id. */
	public static String DELET_BY_CUST_ID = "DELETE FROM customer_coupon WHERE cust_id = '%1s'";
	
	/** The update customer. */
	public static String UPDATE_CUSTOMER = "update customer set password = ? where id = ?";
	
	/** The customer by password. */
	public static String CUSTOMER_BY_PASSWORD = "SELECT * FROM customer WHERE password = '%1s' and cust_name = '%2s'";
	
	/** The amount and end date by id. */
	public static String AMOUNT_AND_END_DATE_BY_ID = "SELECT amount, end_date FROM coupon WHERE id = '%1s'";
	
	/** The insert customer coupon. */
	public static String INSERT_CUSTOMER_COUPON = " insert into customer_coupon (cust_id,coupon_id)"
			+ " values (?, ?)";
	
	/** The coupon id by customerid. */
	public static String COUPON_ID_BY_CUSTOMERID = "SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = '%1S'";
	
	/** The select all customer. */
	public static String SELECT_ALL_CUSTOMER = "SELECT * FROM customer";
	
	/** The delete coupon cust id. */
	public static String DELETE_COUPON_CUST_ID = "DELETE FROM company_coupon WHERE id = '%1s'";

}
