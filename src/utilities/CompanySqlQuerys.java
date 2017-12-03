package utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanySqlQuerys.
 */
public class CompanySqlQuerys
{
	
	/** The select all comp name. */
	public static String SELECT_ALL_COMP_NAME = "SELECT * FROM company WHERE comp_name = '%1s'";
	
	/** The insert company. */
	public static String INSERT_COMPANY = " INSERT INTO company (COMP_NAME, PASSWORD, EMAIL) values (?, ?, ?)";
	
	/** The select company comp id. */
	public static String SELECT_COMPANY_COMP_ID = "SELECT * FROM company WHERE id ='%1s'";
	
	/** The delete company id. */
	public static String DELETE_COMPANY_ID = "DELETE FROM company WHERE id = '%1s'";
	
	/** The update company by id. */
	public static String UPDATE_COMPANY_BY_ID = "update company set password = ?, email = ? where id = ?";
	
	/** The select all companys. */
	public static String SELECT_ALL_COMPANYS = "SELECT * FROM company";
	
	/** The company by password. */
	public static String COMPANY_BY_PASSWORD = "SELECT * FROM company WHERE password = '%1s' and comp_name LIKE '%2s'";
	
	/** The delete coupon where id. */
	public static String DELETE_COUPON_WHERE_ID = "DELETE FROM company_coupon WHERE id = '%1s'";

}
