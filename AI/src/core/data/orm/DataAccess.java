/**
 * 
 */
package core.data.orm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.sqlite.SQLiteDataSource;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class DataAccess implements DataObject {

	private Connection connection;
	private String table = "";
	
	public DataAccess() throws Exception {
		this.setParams("jdbc:sqlite:Data/dbFile.db", "");
	}
	
	public DataAccess(String table) throws Exception {
		this.setParams("jdbc:sqlite:Data/dbFile.db", table);
	}
	
	public DataAccess(String conDB, String table) throws Exception {
		this.setParams(conDB, table);
	}
	
	/**
	 * 
	 * @param conDB
	 * @param table
	 * @throws Exception
	 */
	private void setParams(String conDB, String table) throws Exception {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl(conDB);
		this.connection = ds.getConnection();
		this.setTable(table);
	}
	
	public String getTable() {
		return this.table;
	}
	
	public DataAccess setTable(String table) {
		this.table = table;
		return this;
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
		try {
			this.connection.close();
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public DataAccess createTable(String tableName, String[][] fields) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("drop table if exists " + tableName).set("create table " + tableName + " (" + bidimensionalArrayToString(fields) + ")");
	}
	
	public DataAccess dropTable(String tableName) throws SQLException {
		if(tableName.equals(this.getTable()))
			this.setTable("");
		return this.set("drop table if exists " + tableName);
	}
	
	public DataAccess dropTable() throws SQLException {
		return this.dropTable(this.getTable());
	}
	
	public DataAccess renameTable(String oldName, String newName) throws SQLException {
		if(oldName.equals(this.getTable()))
			this.setTable(newName);
		return this.set("alter table " + oldName + " rename to " + newName);
	}
	
	public DataAccess renameTable(String newName) throws SQLException {
		return this.renameTable(this.getTable(), newName);
	}
	
	public DataAccess addColumn(String tableName, String[] columnDefinition) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("alter table " + tableName + " add column " + arrayToString(columnDefinition, " "));
	}
	
	public DataAccess addColumn(String[] columnDefinition) throws SQLException {
		return this.addColumn(this.getTable(), columnDefinition);
	}
	
	public DataAccess truncateTable(String tableName) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("delete from " + tableName);
	}
	
	public DataAccess truncateTable() throws SQLException {
		return this.truncateTable(this.getTable());
	}
	
	public DataAccess insert(String tableName, String[][] data) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("insert into " + tableName + " (" + doublePlainArrayData(data)[0] + ") values (" + doublePlainArrayData(data)[1] + ")");
	}
	
	public DataAccess insert(String[][] data) throws SQLException {
		return this.insert(this.getTable(), data);
	}
	
	public DataAccess update(String tableName, String[][] data, String[] where) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("update " + tableName + " set " + singlePlainArrayData(data) + " where " + where[0] + " = '" + where[1] + "'");
	}
	
	public DataAccess update(String[][] data, String[] where) throws SQLException {
		return this.update(this.getTable(), data, where);
	}
	
	public DataAccess delete(String tableName, String[] where) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.set("delete from " + tableName + " where " + where[0] + " = '" + where[1] + "'");
	}
	
	public DataAccess delete(String[] where) throws SQLException {
		return this.delete(this.getTable(), where);
	}
	
	public ArrayList<ArrayList<String>> select(String tableName, String[] fields, String[] where) throws SQLException {
		if(this.getTable().isEmpty())
			this.setTable(tableName);
		return this.get("select " + arrayToString(fields, ", ") + " from " + tableName + " where " + where[0] + " = '" + where[1] + "'");
	}
	
	public ArrayList<ArrayList<String>> select(String[] fields, String[] where) throws SQLException {
		return this.select(this.getTable(), fields, where);
	}
	
	public ArrayList<ArrayList<String>> get(String sql) throws SQLException {
		Statement statement = null;
		ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();
		try {
			statement = this.connection.createStatement();
			ResultSet rawResultSet = statement.executeQuery(sql);
			ResultSetMetaData rsMetaData = rawResultSet.getMetaData();
			int columnsNumber = rsMetaData.getColumnCount();
			while(rawResultSet.next()) {
				ArrayList<String> subResultSet = new ArrayList<String>();
				for(int i = 0; i < columnsNumber; i++) {
					String currentColumnName = rsMetaData.getColumnName(i + 1);
					subResultSet.add(rawResultSet.getString(currentColumnName));
				}
				resultSet.add(subResultSet);
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return resultSet;
	}
	
	public DataAccess set(String sql) throws SQLException {
		Statement statement = null;
		try {
			statement = this.connection.createStatement();
			statement.execute(sql);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null)
				statement.close();
		}
		return this;
	}

}
