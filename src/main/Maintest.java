package main;

import java.sql.SQLException;
import java.text.ParseException;
import facades.*;
import javaBeans.*;
import utilities.DateTranslate;

// TODO: Auto-generated Javadoc
/**
 * The Class Maintest.
 */
public class Maintest {
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ParseException the parse exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException, InterruptedException {

		AdminFacade adminfacade;
		CompanyFacade companyfacade;
		CustomerFacade customerfacade;

		adminfacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);

		//test 1
		System.out.println("before creating company");
		Company comp1 = new Company(0,"bezeq","123","www.");
		Company comp2 = new Company(0,"intel","456","www.");
		Company comp3 = new Company(0,"karnaf","789","www.");
		Company comp4 = new Company(0,"teva","741","www.");
		adminfacade.createCompany(comp1);
		adminfacade.createCompany(comp2);
		adminfacade.createCompany(comp3);
		adminfacade.createCompany(comp4);

		// test 2
		Customer cust1 = new Customer(0,"jhon","963");
		Customer cust2 = new Customer(0,"nick","852");
		Customer cust3 = new Customer(0,"shelly","753");
		Customer cust4 = new Customer(0,"maya","951");
		adminfacade.createCustomer(cust1);
		adminfacade.createCustomer(cust2);
		adminfacade.createCustomer(cust3);
		adminfacade.createCustomer(cust4);

		//test 3
		System.out.println(adminfacade.getAllCompanies());
		adminfacade.updateCompany(comp1);
		System.out.println(adminfacade.getCompany(1));

		//test4
		System.out.println(adminfacade.getAllCustomer());
		Customer cust5 = new Customer(1,"jhon","369");
		adminfacade.updateCustomer(cust5);
		System.out.println(adminfacade.getCustomer(1));

		//test5
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "123", ClientType.COMPANY);
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "321", ClientType.COMPANY);
		

		Coupon coup1 = new Coupon(0, "aaa", DateTranslate.stringToDate("18-11-01"), DateTranslate.stringToDate("19-4-05"), 5,CouponType.camping, "hhh", 25.7,"ttt");
		Coupon coup2 = new Coupon(0, "bbb", DateTranslate.stringToDate("18-12-01"), DateTranslate.stringToDate("19-02-20"), 5,CouponType.food, "hhh", 30.6,"lll");
		Coupon coup3 = new Coupon(0, "mmm", DateTranslate.stringToDate("17-01-12"), DateTranslate.stringToDate("17-03-18"), 5,CouponType.food, "hhh", 30.6,"nnn");
		companyfacade.createCoupon(coup1);
		companyfacade.createCoupon(coup2);
		companyfacade.createCoupon(coup3);
		System.out.println(companyfacade.getCouponByType(CouponType.food));
		System.out.println(companyfacade.getCouponByPrice(40));
		java.util.Date test = DateTranslate.stringToDate("17-02-25");
		System.out.println(companyfacade.getCouponByDate(test));

		//test6
		System.out.println(companyfacade.getAllCoupon());
		System.out.println(companyfacade.getCoupon(3));
		Coupon coup4 = new Coupon(3, "aaa", DateTranslate.stringToDate("17-01-01"), DateTranslate.stringToDate("17-03-30"), 5,CouponType.camping, "hhh", 25.8,"ttt");
		companyfacade.updateCoupon(coup4);
		System.out.println(companyfacade.getCoupon(3));

		//test7
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("intel", "456", ClientType.COMPANY);

		Coupon coup5 = new Coupon(0, "ccc",DateTranslate.stringToDate("11-01-02"), DateTranslate.stringToDate("14-05-12"),5,CouponType.camping, "hhh", 25.7,"ttt");
		Coupon coup6 = new Coupon(0, "ddd", DateTranslate.stringToDate("19-01-01"),DateTranslate.stringToDate("19-02-02") , 5,CouponType.camping, "hhh", 25.7,"ttt");
		companyfacade.createCoupon(coup5);
		companyfacade.createCoupon(coup6);
		System.out.println(companyfacade.getAllCoupon());

		//test8
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","963",ClientType.CUSTOMER);
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","369",ClientType.CUSTOMER);
		System.out.println(customerfacade.getAllPurchasedCoupons());
		Coupon coupon = customerfacade.getCoupon(3);
		customerfacade.purchaseCoupon(coupon);
		System.out.println(customerfacade.getAllPurchasedCoupons());
		System.out.println(customerfacade.getAllPurchasedCouponsByType(CouponType.food));
		System.out.println(customerfacade.getAllPurchasedCouponsByPrice(35));

		//test 9
		companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "321", ClientType.COMPANY);
		Coupon coup = companyfacade.getCoupon(3);
		companyfacade.removeCoupon(coup);

		//test 10
		customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","369",ClientType.CUSTOMER);
		System.out.println(customerfacade.getAllPurchasedCoupons());
		customerfacade.purchaseCoupon(customerfacade.getCoupon(2));
		customerfacade.purchaseCoupon(customerfacade.getCoupon(5));
		adminfacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		System.out.println(adminfacade.getAllCompanies());
		System.out.println(adminfacade.getAllCustomer());
		adminfacade.removeCompany(adminfacade.getCompany(2));
		adminfacade.removeCustomer(adminfacade.getCustomer(2));




}
}
