package utilities;

public class CustomerSqlQuerys {
	
	public static String ALL_CUSTOMERS = "SELECT * FROM customer";
	public static String ALL_CUSTOMER_BY_NAME = "SELECT * FROM customer WHERE cust_name = '%1s' ";
	public static String ALL_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = '%1s'";
	public static String INSERT_CUSTOMER = " insert into customer (cust_name, password)"
			+ " values (?, ?)";
	public static String SELECT_CUSTOMER_BY_NAME = "SELECT * FROM customer WHERE cust_name ='";
	public static String DELET_BY_CUST_NAME = "DELETE FROM customer WHERE cust_name = '%1s'";
	public static String DELET_BY_CUST_ID = "DELETE FROM customer_coupon WHERE cust_id = '%1s'";
	public static String UPDATE_CUSTOMER = "update customer set password = ? where id = ?";
	public static String CUSTOMER_BY_PASSWORD = "SELECT * FROM customer WHERE password = '%1s' and cust_name = '%2s'";
	public static String AMOUNT_AND_END_DATE_BY_ID = "SELECT amount, end_date FROM coupon WHERE id = '%1s'";
	public static String INSERT_CUSTOMER_COUPON = " insert into customer_coupon (cust_id,coupon_id)"
			+ " values (?, ?)";
	public static String COUPON_ID_BY_CUSTOMERID = "SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = '%1S'";
	public static String SELECT_ALL_CUSTOMER = "SELECT * FROM customer";
}
