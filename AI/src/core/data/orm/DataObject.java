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
	 * @return true if ok. False if something goes wrong.
	 * @throws SQLException 
	 */
	public DataObject createTable(String tableName, String[][] fields) throws SQLException;
	
	/**
	 * Delete a Database Table.
	 * @param tableName. String, name of the table.
	 * @return true if ok. False if something goes wrong.
	 * @throws SQLException 
	 */
	public DataObject dropTable(String tableName) throws SQLException;
	
	/**
	 * Rename a Database Table.
	 * @param oldName. String, original name of the table.
	 * @param newTable. String, new name of the table.
	 * @return true if ok. False if something goes wrong.
	 * @throws SQLException 
	 */
	public DataObject renameTable(String oldName, String newName) throws SQLException;
	
	/**
	 * Add a new Column to a Database Table.
	 * @param tableName. String, name of the table.
	 * @param columnDefinition. String array, parameters of the new field.
	 * @return true if ok. False if something goes wrong.
	 * @throws SQLException 
	 */
	public DataObject addColumn(String tableName, String[] columnDefinition)throws SQLException;
	
	//DML Statements.
	
	
	
}
