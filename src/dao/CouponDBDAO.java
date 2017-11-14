package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import javaBeans.Coupon;
import javaBeans.CouponType;

public class CouponDBDAO implements CouponDAO{

	@Override
	public void createCoupon(Coupon coupon) throws SQLException, ClassNotFoundException, InterruptedException,
			DuplicateEntryException, NullConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCoupon(Coupon coupon)
			throws SQLException, ClassNotFoundException, InterruptedException, NullConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCoupon(Coupon coupon)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coupon getCoupon(long id)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getAllCoupon()
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCouponByType(CouponType couponType)
			throws ClassNotFoundException, InterruptedException, SQLException, ParseException, NullConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

}
