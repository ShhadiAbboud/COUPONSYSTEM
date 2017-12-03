package javaBeans;

import java.sql.SQLException;

import exceptions.GeneralException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CouponClientFacade;
import facades.CustomerFacade;
import main.ConnectionPool;
import threads.DailyCouponExpirationTask;

// TODO: Auto-generated Javadoc
/**
 * The Class CouponSystem.
 */
public class CouponSystem {
	
	/** The instance. */
	private static CouponSystem instance = null;
	
	/** The my daily coupon expiration task. */
	DailyCouponExpirationTask myDailyCouponExpirationTask = new DailyCouponExpirationTask();
	
	/** The clean system. */
	Thread cleanSystem = new Thread(myDailyCouponExpirationTask,"clean");
	
	

		/**
		 * Instantiates a new coupon system.
		 */
		private CouponSystem()
		{
			ConnectionPool.getInstance();
			cleanSystem.setDaemon(true);
			cleanSystem.start();	
		}
		
	
		/**
		 * Gets the single instance of CouponSystem.
		 *
		 * @return single instance of CouponSystem
		 */
		public static synchronized CouponSystem getInstance()
		{
			if (instance==null)
			{
				instance = new CouponSystem();
			}
			
			return instance;
		}
		
	
		/**
		 * Login.
		 *
		 * @param name the name
		 * @param password the password
		 * @param clientType the client type
		 * @return the coupon client facade
		 */
		public CouponClientFacade login(String name,String password,ClientType clientType)
		{
			CouponClientFacade login = null;
			switch(clientType)
			{
			case CUSTOMER :
				CustomerFacade customerfacade = new CustomerFacade();
			    login = customerfacade.login(name, password, clientType.toString());
			    if (login!=null)
			    {
				System.out.println("welcome customer " + name);
			    return customerfacade;
			    }
			break;
				
			case COMPANY : 
				CompanyFacade companyfacade = new CompanyFacade();
			    login = companyfacade.login(name, password, clientType.toString());
			    if (login!=null)
			    {
				System.out.println("welcome " + name + " company");
				return companyfacade;
			    }
			break;
			
			case ADMIN : 
			
				AdminFacade adminfacade = new AdminFacade();
				login = adminfacade.login(name, password, clientType.toString());
			    if (login!=null)
			    {
			    System.out.println("welcome admin");
				return adminfacade;
			    }
		    }
						
			return null;
         }
		
		
		/**
		 * Shut down.
		 */
		public void shutDown()
		{
			myDailyCouponExpirationTask.stopTask();
			ConnectionPool.getInstance().shuttingDown();
			try
			{
				ConnectionPool.getInstance().closeAllConnections();
			}
			catch (SQLException e)
			{
				new GeneralException(e);
			}
			ConnectionPool.getInstance().shuttingDown();
		}
		

}
