package javaBeans;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Coupon.
 */
public class Coupon {
	
	/** The id. */
	private long id;
	
	/** The title. */
	private String title;
    
    /** The start date. */
    private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The amount. */
	private int amount;
	
	/** The type. */
	private CouponType type;
	
	/** The message. */
	private String message;
	
	/** The price. */
	private double price;
	
	/** The image. */
	private String image;
	
	
	/**
	 * Instantiates a new coupon.
	 */
	public Coupon(){
		
	}


	/**
	 * Instantiates a new coupon.
	 *
	 * @param id the id
	 * @param title the title
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param amount the amount
	 * @param coupontype the coupontype
	 * @param message the message
	 * @param price the price
	 * @param image the image
	 */
	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType coupontype,
			String message, double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = coupontype;
		this.message = message;
		this.price = price;
		this.image = image;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + "]";
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}


	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}


	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}


	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


	/**
	 * Gets the coupontype.
	 *
	 * @return the coupontype
	 */
	public CouponType getCoupontype() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param coupontype the new type
	 */
	public void setType(CouponType coupontype) {
		this.type = coupontype;
	}


	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}


	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	

}
