package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javaBeans.Coupon;
import javaBeans.CouponType;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;

public interface CouponDAO {
	
	public void createCoupon(Coupon coupon) throws SQLException,
	ClassNotFoundException,
	InterruptedException,
	DuplicateEntryException,
	NullConnectionException;
	
	public void removeCoupon(Coupon coupon) throws SQLException,
	ClassNotFoundException,
	InterruptedException,
	NullConnectionException;
	
	public void updateCoupon(Coupon coupon) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	public Coupon getCoupon(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	public Collection<Coupon> getAllCoupon() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	public Collection<Coupon> getCouponByType(CouponType couponType) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;

}
