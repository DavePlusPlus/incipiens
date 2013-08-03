package draft;

import java.sql.Connection;
import java.sql.DriverManager;
import org.sormula.Database;
import org.sormula.Table;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Sample2 {
	
	static Connection connection;
	static Database database;
	static Table<Inventory> inventoryTable;
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:Data/dbFile.db");
		database = new Database(connection);
		inventoryTable = database.getTable(Inventory.class);
		inventoryTable.deleteAll();
		Inventory tableInstance = new Inventory();
		tableInstance.setPartNumber(45);
		tableInstance.setQuantity(25);
		tableInstance.setManufacturerId("Acme");
		inventoryTable.insert(tableInstance);
		Inventory row = inventoryTable.select(45);
		row.setQuantity(35);
		inventoryTable.update(row);
		inventoryTable.delete(row);
		connection.close();
		database.close();
		
	}
	
}
