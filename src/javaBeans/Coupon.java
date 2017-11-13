package javaBeans;

import java.sql.Date;

public class Coupon {
	private long id;
	private String title;
    private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType coupontype;
	private String message;
	private double price;
	private String image;
	
	
	public Coupon(){
		
	}


	public Coupon(long id, String title, Date startDate, Date endDate, int amount, CouponType coupontype,
			String message, double price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.coupontype = coupontype;
		this.message = message;
		this.price = price;
		this.image = image;
	}


	public long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + "]";
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public java.util.Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public java.util.Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public CouponType getCoupontype() {
		return coupontype;
	}


	public void setCoupontype(CouponType coupontype) {
		this.coupontype = coupontype;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	

}