/**
 * 
 */
package core.data.orm;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public interface DataObject {
	
	/**
	 * Returns the name of the current table.
	 * @return String table.
	 */
	public String getTable();
	
	/**
	 * Set the name of the table to use.
	 * @param table. String, name of the table to use.
	 */
	public DataObject setTable(String table);
	
	/**
	 * Close Database connection.
	 * @throws SQLException 
	 */
	public void close() throws SQLException;
	
	//DDL Statements.
	/**
	 * Create a new Database Table.
	 * @param tableName. String, name of the new table.
	 * @param fields. String array, fields of new table.
	 * @throws SQLException 
	 */
	public DataObject createTable(String tableName, String[][] fields) throws SQLException;
	
	/**
	 * Delete a Database Table.
	 * @param tableName. String, name of the table.
	 * @throws SQLException 
	 */
	public DataObject dropTable(String tableName) throws SQLException;
	
	/**
	 * Delete the current Database Table.
	 * @throws SQLException 
	 */
	public DataObject dropTable() throws SQLException;
	
	/**
	 * Rename a Database Table.
	 * @param oldName. String, original name of the table.
	 * @param newName. String, new name of the table.
	 * @throws SQLException 
	 */
	public DataObject renameTable(String oldName, String newName) throws SQLException;
	
	/**
	 * Rename the current Database Table.
	 * @param newName. String, new name of the table.
	 * @throws SQLException 
	 */
	public DataObject renameTable(String newName) throws SQLException;
	
	/**
	 * Add a new Column to a Database Table.
	 * @param tableName. String, name of the table.
	 * @param columnDefinition. String array, parameters of the new field.
	 * @throws SQLException 
	 */
	public DataObject addColumn(String tableName, String[] columnDefinition)throws SQLException;
	
	/**
	 * Add a new Column to the current Database Table.
	 * @param columnDefinition. String array, parameters of the new field.
	 * @throws SQLException 
	 */
	public DataObject addColumn(String[] columnDefinition)throws SQLException;
	
	/**
	 * Truncate a Database Table.
	 * @param tableName. String, name of the table.
	 * @throws SQLException 
	 */
	public DataObject truncateTable(String tableName) throws SQLException;
	
	/**
	 * Truncate the current Database Table.
	 * @throws SQLException 
	 */
	public DataObject truncateTable() throws SQLException;
	
	//DML Statements.
	/**
	 * Insert data into a Database Table.
	 * @param tableName. String, name of the table.
	 * @param data. String array, data to be saved.
	 * @throws SQLException 
	 */
	public DataObject insert(String tableName, String[][] data) throws SQLException;
	
	/**
	 * Insert data into the current Database Table.
	 * @param data. String array, data to be saved.
	 * @throws SQLException 
	 */
	public DataObject insert(String[][] data) throws SQLException;
	
	/**
	 * Update data into a Database Table.
	 * @param tableName. String, name of the table.
	 * @param data. String array, data to be updated.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public DataObject update(String tableName, String[][] data, String[] where) throws SQLException;
	
	/**
	 * Update data into the current Database Table.
	 * @param data. String array, data to be updated.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public DataObject update(String[][] data, String[] where) throws SQLException;
	
	/**
	 * Delete data from a Database Table.
	 * @param tableName. String, name of the table.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public DataObject delete(String tableName, String[] where) throws SQLException;
	
	/**
	 * Delete data from the current Database Table.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public DataObject delete(String[] where) throws SQLException;
	
	/**
	 * Select data from a Database Table.
	 * @param tableName. String, name of the table.
	 * @param fields. String array, selection of columns.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public ArrayList<ArrayList<String>> select(String tableName, String[] fields, String[] where) throws SQLException;
	
	/**
	 * Select data from the current Database Table.
	 * @param fields. String array, selection of columns.
	 * @param where. String array, where definition.
	 * @throws SQLException 
	 */
	public ArrayList<ArrayList<String>> select(String[] fields, String[] where) throws SQLException;
	
	//Free Statements.
	/**
	 * Create a free SQL query that will return a resultset in form of a ArrayList of ArrayList's.
	 * @param sql. String, SQL query.
	 * @throws SQLException
	 */
	public ArrayList<ArrayList<String>> get(String sql) throws SQLException;
	
	/**
	 * Create a free SQL query that won't return a resultset.
	 * @param sql
	 * @throws SQLException
	 */
	public DataObject set(String sql) throws SQLException;
	
}
