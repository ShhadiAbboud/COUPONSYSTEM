package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import javaBeans.Coupon;
import javaBeans.Customer;
import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;

public interface CustomerDAO {
	public void createCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	DuplicateEntryException,
	NullConnectionException;
	
	public void removeCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	public void updateCustomer(Customer customer) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;

	public Customer getCustomer(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	public Collection<Customer> getAllCustomer() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;

	public Collection<Coupon> getCoupons() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	public boolean login(String custName, String password) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	WrongDataInputException,
	NullConnectionException;

	public Collection<Coupon> getCouponsByCustomerId(long id) throws SQLException;

}
