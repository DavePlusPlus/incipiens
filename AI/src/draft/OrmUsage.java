/**
 * ORM usage example class.
 */
package draft;

import java.util.ArrayList;
import core.data.orm.DataAccess;
import core.data.orm.DataObject;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class OrmUsage {

	public OrmUsage() {}

	public static void main(String[] args) throws Exception {
		
		String[][] ddl = new String[][] {
				{"id", "integer", "primary key", "autoincrement"},
				{"name", "varchar(25)"},
				{"title", "varchar(25)"},
				{"age", "integer"},
				{"gender", "varchar(25)"}
		};
		
		String[][] dml1 = new String[][] {
				{"name", "Dave"},
				{"title", "Developer"},
				{"age", "21"},
				{"gender", "male"}
		};
		
		String[][] dml2 = new String[][] {
				{"name", "Elise"},
				{"title", "chef"},
				{"age", "35"},
				{"gender", "female"}
		};
		
		String[] fields = new String[] {"name", "title", "age"};
		
		String[] where = new String[] {"id", "1"};
		
		//Instance of new database object.
		DataObject db = new DataAccess();
		
		//Create a table and store some data.
		db.createTable("table1", ddl).insert(dml1).insert(dml2);
		
		//Let's make a query.
		ArrayList<ArrayList<String>> resultSet = db.select(fields, where);
		
		String name = resultSet.get(0).get(0);
		String title = resultSet.get(0).get(1);
		String age = resultSet.get(0).get(2);
		
		System.out.println("Name: " + name);
		System.out.println("Title: " + title);
		System.out.println("Age: " + age);
		
		//We don't need this connection anymore.
		db.close();
	}
	
	

}
