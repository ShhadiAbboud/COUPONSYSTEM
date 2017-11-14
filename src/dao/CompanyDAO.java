package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

import exceptions.DuplicateEntryException;
import exceptions.NullConnectionException;
import exceptions.WrongDataInputException;
import javaBeans.Company;
import javaBeans.Coupon;

public interface CompanyDAO {
	
	public void createCompany(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	DuplicateEntryException,
	NullConnectionException;
	
	public void removeComapny(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	public void updateCompany(Company company) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException;
	
	public Company getCompany(long id) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException,
	ParseException;
	
	public Collection<Company> getAllCompanys() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	NullConnectionException,
	ParseException;
	

	public Collection<Coupon> getCoupons() throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	ParseException,
	NullConnectionException;
	
	public boolean login(String compName, String password) throws ClassNotFoundException,
	InterruptedException,
	SQLException,
	WrongDataInputException,
	NullConnectionException;
	
	public Collection<Coupon> getCouponsByCompanyId(long id) throws SQLException;


}
