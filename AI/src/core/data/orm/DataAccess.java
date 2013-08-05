/**
 * 
 */
package core.data.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class DataAccess implements DataObject {

	Connection connection;
	
	public DataAccess() throws Exception {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:Data/dbFile.db");
	}
	
	public DataAccess(String driver, String conDB) throws Exception {
		Class.forName(driver);
		connection = DriverManager.getConnection(conDB);
	}
	
	/**
	 * 
	 */
	private String arrayToString(String[] columnDefinition) {
		String plainColumn = "";
		for(int i = 0; i < columnDefinition.length; i++) {
			plainColumn += columnDefinition[i];
			if(i < columnDefinition.length - 1)
				plainColumn += " ";
		}
		return plainColumn;
	}
	
	/**
	 * 
	 */
	private String bidimensionalArrayToString(String[][] fields) {
		String plainFields = "";
		for(int i = 0; i < fields.length; i++) {
			for(int ii = 0; ii < fields[i].length; ii++) {
				plainFields += fields[i][ii];
				if(ii < fields[i].length - 1)
					plainFields += " ";
			}
			if(i < fields.length - 1)
				plainFields += ", ";
		}
		return plainFields;
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public DataAccess createTable(String tableName, String[][] fields) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("drop table if exists " + tableName);
			statement.execute("create table " + tableName + " (" + bidimensionalArrayToString(fields) + ")");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess dropTable(String tableName) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("drop table if exists " + tableName);
			statement.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess renameTable(String oldName, String newName) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("alter table " + oldName + " rename to " + newName);
			statement.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess addColumn(String tableName, String[] columnDefinition)throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("alter table " + tableName + " add column " + arrayToString(columnDefinition));
			statement.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	

}
