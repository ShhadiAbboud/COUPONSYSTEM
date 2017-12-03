package javaBeans;

import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Class Company.
 */
public class Company {
	
	/** The id. */
	private long id;
	
	/** The comp name. */
	private String compName;
	
	/** The password. */
	private String password;
	
	/** The email. */
	private String email;
	
	/** The coupons. */
	private Collection<Coupon> coupons;
	
	/**
	 * Instantiates a new company.
	 */
	public Company()
	{

	}

	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param compName the comp name
	 * @param password the password
	 * @param email the email
	 */
	public Company(long id, String compName, String password, String email)
	{
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		
	}
	
	/**
	 * Instantiates a new company.
	 *
	 * @param id the id
	 * @param compName the comp name
	 * @param password the password
	 * @param email the email
	 * @param coupons the coupons
	 */
	public Company(long id, String compName, String password, String email,Collection<Coupon> coupons)
	{
		this(id,compName,password,email);
		this.coupons = coupons;
		
		
		
	}

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
	 * Gets the comp name.
	 *
	 * @return the comp name
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * Sets the comp name.
	 *
	 * @param compName the new comp name
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the coupons.
	 *
	 * @return the coupons
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * Sets the coupons.
	 *
	 * @param coupons the new coupons
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", mycoupons=" + coupons + "]";
	}
	

}
