/**
 * 
 */
package core.data.orm;

import java.sql.SQLException;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public interface DataObject {
	
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
	 * Rename a Database Table.
	 * @param oldName. String, original name of the table.
	 * @param newTable. String, new name of the table.
	 * @throws SQLException 
	 */
	public DataObject renameTable(String oldName, String newName) throws SQLException;
	
	/**
	 * Add a new Column to a Database Table.
	 * @param tableName. String, name of the table.
	 * @param columnDefinition. String array, parameters of the new field.
	 * @throws SQLException 
	 */
	public DataObject addColumn(String tableName, String[] columnDefinition)throws SQLException;
	
	/**
	 * Truncate a Database Table.
	 * @param tableName. String, name of the table.
	 * @throws SQLException 
	 */
	public DataObject truncateTable(String tableName) throws SQLException;
	
	//DML Statements.
	/**
	 * Insert data into a Database Table.
	 * @param tableName. String, name of the table.
	 * @param data. String array, data to be saved.
	 * @throws SQLException 
	 */
	public DataObject insert(String tableName, String[][] data) throws SQLException;
	/*
	public Object select() throws SQLException; //Figure out the type of the object.
	
	public DataObject update(String tableName, String[][] data, String[] where) throws SQLException;
	
	public DataObject delete(String tableName, String[] where) throws SQLException;
	*/
}
