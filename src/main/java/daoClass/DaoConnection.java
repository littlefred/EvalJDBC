/**
 * package daoClass => to group all classes to use for data save
 */
package daoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Frederick
 * this class is used to manage the type of connection
 * if we'll have more type of connection we will make an interface to manage this with others
 * We can modify this with a file properties to manage the attributes DB access
 */
class DaoConnection {
	private final String url = "";// your url access to your database postgresql
	private final String user = "";// your user to your postgresql
	private final String pass = "";// your password to your postgresql
	
	/**
	 * default constructor
	 */
	public DaoConnection() {
		
	}

	Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (SQLException e) {
			System.err.println("We have an error on driver.");
			e.printStackTrace();
		}
		return connection;
	}
	
	void getClose(Connection connection) {
		try {
			connection.close();
		}catch(SQLException e) {
			System.err.println("Impossible to close the connection.");
			e.printStackTrace();
		}
	}
	
}
