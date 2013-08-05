/**
 * 
 */
package core.data.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	private String arrayToString(String[] array, String separator) {
		String plainColumn = "";
		for(int i = 0; i < array.length; i++) {
			plainColumn += array[i];
			if(i < array.length - 1)
				plainColumn += separator;
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
	
	/**
	 * 
	 */
	private String[] doublePlainArrayData(String[][] data) {
		String[] orderedData = new String[2];
		String plainFields = "";
		String plainValues = "";
		for(int i = 0; i < data.length; i++) {
			plainFields += data[i][0];
			plainValues += "'" + data[i][1] + "'";
			if(i < data.length - 1) {
				plainFields += ", ";
				plainValues += ", ";
			}
		}
		orderedData[0] = plainFields;
		orderedData[1] = plainValues;
		return orderedData;
	}
	
	/**
	 * 
	 */
	private String singlePlainArrayData(String[][] data) {
		String plainData = "";
		for(int i = 0; i < data.length; i++) {
			plainData += data[i][0] + " = '" + data[i][1] + "'";
			if(i < data.length - 1)
				plainData += ", ";
		}
		return plainData;
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
			statement.execute("alter table " + tableName + " add column " + arrayToString(columnDefinition, " "));
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess truncateTable(String tableName) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("delete from " + tableName);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess insert(String tableName, String[][] data) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("insert into " + tableName + " (" + doublePlainArrayData(data)[0] + ") values (" + doublePlainArrayData(data)[1] + ")");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess update(String tableName, String[][] data, String[] where) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("update " + tableName + " set " + singlePlainArrayData(data) + " where " + where[0] + " = '" + where[1] + "'");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public DataAccess delete(String tableName, String[] where) throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("delete from " + tableName + " where " + where[0] + " = '" + where[1] + "'");
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}
	
	public ArrayList<ArrayList<String>> select(String tableName, String[] fields, String[] where) throws SQLException {
		Statement statement = null;
		ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();
		try {
			statement = connection.createStatement();
			ResultSet rawResultSet = statement.executeQuery("select " + arrayToString(fields, ", ") + " from " + tableName + " where " + where[0] + " = '" + where[1] + "'");
			while(rawResultSet.next()) {
				ArrayList<String> subResultSet = new ArrayList<String>();
				for(int i = 0; i < fields.length; i++) {
					subResultSet.add(rawResultSet.getString(fields[i]));
				}
				resultSet.add(subResultSet);
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null) {
				statement.close();
				close();
			}
		}
		return resultSet;
	}

}
