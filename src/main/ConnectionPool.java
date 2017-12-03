package main;
import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {
	
	/** The instance. */
	private static ConnectionPool instance = null;
	
	/** The driver. */
	private String driver = "com.mysql.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/johnbryceproject?useSSL=false";
	
	/** The username. */
	private String username = "root";
	
	/** The password. */
	private String password = "3sh7adi1";

	/** The key. */
	public  Object key = new Object();
	
	/** The max con. */
	private int MAX_CON=5;
	
	/** The connections. */
	private Set<Connection> connections = new HashSet<Connection>();
	
	/** The is shutting down. */
	private boolean isShuttingDown = false;


	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	//-------------------------------------------------getinstance method-----------------------------------------------------
	public  static ConnectionPool getInstance() 
	{
		if (instance == null)
		{
			instance = new ConnectionPool();
		}
		return instance;
	}

	//ConnectionPool Constructor , initializing the HashSet with 5 Connections
	/**
	 * Instantiates a new connection pool.
	 */
	//-------------------------------------------------ConnectionPool method-----------------------------------------------------
	private ConnectionPool() 
	{
		initConnectionPool();
	}

	/**
	 * Inits the connection pool.
	 */
	private synchronized void initConnectionPool() 
	{
		try 
		{
			Class.forName(driver);
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO: handle exception
			System.out.println("ClassNotFoundException");
		}
	
		while(connections.size()<MAX_CON)
		{
			try 
			{
				Connection conn = DriverManager.getConnection(url, username, password);
				connections.add(conn);
			}
			catch (SQLException e) 
			{
				// TODO: handle exception
				System.out.println("SQLException");

			}
		}	
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	//-------------------------------------------------getConnection method-----------------------------------------------------
	public synchronized Connection getConnection()  
	{
		Connection conn = null;
		synchronized (key) 
		{
			while(connections.isEmpty())
			{	
				try 
				{
					key.wait();
				}
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(!isShuttingDown)
			{
				conn = connections.iterator().next();
				connections.remove(conn);
			}	
		}

		if (conn == null)
		{
			throw new NullPointerException();
		}
		else
		{
			return conn;
		}
	}

	/**
	 * Return connection.
	 *
	 * @param conn the conn
	 */
	//-------------------------------------------------returnConnection method-------------------------------------------------
	public void returnConnection(Connection conn)
	{
		synchronized (key) 
		{
			connections.add(conn);
			key.notifyAll();
		}
	}

	/**
	 * Close all connections.
	 *
	 * @throws SQLException the SQL exception
	 */
	//-------------------------------------------------closeAllConnections method---------------------------------------------
	public void closeAllConnections() throws SQLException 
	{
		isShuttingDown=true;
	synchronized (key) {
		
		if(connections.size()<MAX_CON)
		{
			try 
			{
		
				key.wait(5000);
			}
			catch (InterruptedException e)
			{
				// TODO: handle exception
			}
		}
	}

		Iterator<Connection> iteratorr = connections.iterator();
		
		while(iteratorr.hasNext())
		{
			Connection conn = iteratorr.next();
			conn.close();
		}
	}

	/**
	 * Shutting down.
	 */
	public void shuttingDown() 
	{
		isShuttingDown = true;
	}
}
