package main;
import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConnectionPool {
	
	private static ConnectionPool instance = null;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/johnbryceproject?useSSL=false";
	private String username = "root";
	private String password = "3sh7adi1";

	public  Object key = new Object();
	private int MAX_CON=5;
	private Set<Connection> connections = new HashSet<Connection>();
	private boolean isShuttingDown = false;


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
	//-------------------------------------------------ConnectionPool method-----------------------------------------------------
	private ConnectionPool() 
	{
		initConnectionPool();
	}

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

	//-------------------------------------------------returnConnection method-------------------------------------------------
	public void returnConnection(Connection conn)
	{
		connections.add(conn);
		synchronized (key) 
		{
			key.notifyAll();
		}
	}

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
}
