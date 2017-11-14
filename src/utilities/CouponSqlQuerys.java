package utilities;

public class CouponSqlQuerys {
	public static String COUPON_ID_BY_COMP_ID = "SELECT coupon_id FROM company_coupon WHERE comp_id = '%1s'";
	public static String COUPON_ID_BY_CUST_ID = "SELECT coupon_id FROM customer_coupon WHERE cust_id = '%1s'";
	public static String DELETE_COUPON_CUST_ID = "DELETE FROM customer_coupon WHERE coupon_id = '%1s'";
	public static String DELETE_COUPON_COMP_ID = "DELETE FROM company_coupon WHERE comp_id = '%1s'";
	public static String ALL_COUPONS_BY_ID = "SELECT * FROM coupon WHERE id = '%1s'";
	public static String ALL_COUPONS_BY_TYPE = "SELECT * FROM coupon WHERE type = '%1s'";
	public static String ALL_COUPONS = "SELECT * FROM coupon";
	public static String COUPONS_BY_TITLE = "SELECT * FROM coupon WHERE title = '%1s'";
	public static String INSERT_COUPON = " insert into coupon (title, start_date, end_date, amount, type, message, price, image)"
			+ " values (?, ?, ?, ? ,? ,? ,? ,?)";
	public static String INSERT_COUPON_COMP = " insert into company_coupon (comp_id, coupon_id)"
			+ " values (?, ?)";
	public static String DELETE_COUPON_BY_ID = "delete from coupon where id = ?";
	public static String DELETE_COUPON_COMPANY_BY_ID = "delete from company_coupon where coupon_id = ?";
	public static String DELETE_COUPON_CUSTOMER_BY_ID = "delete from customer_coupon where coupon_id = ?";
	public static String DELETE_COUPON_BY_COUPON_ID = "delete from coupon where id = '%1s'";
	public static String UPDATE_COUPON = "update coupon set end_date = ?, price = ? where id = ?";
	public static String TYPE_BY_ID = "SELECT type FROM coupon WHERE id = '%1s'";
	public static String UPDATE_COUPON_AMOUNT = "update coupon set amount = ? where id = ?";
	public static String ALL_COUPONS_BY_ID_AND_TYPE = "SELECT * FROM coupon WHERE id = '%1s' and type LIKE '%2s'";
	public static String ALL_COUPONS_BY_ID_AND_PRICE = "SELECT * FROM coupon WHERE id = '%1s' and price <= '%2s'";

}
