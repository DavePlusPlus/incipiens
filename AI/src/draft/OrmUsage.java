/**
 * 
 */
package draft;

import java.util.ArrayList;
import java.util.Arrays;
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
				{"field1", "varchar(25)"},
				{"field2", "integer"},
				{"field3", "varchar(25)"}
		};
		
		String[] fields = new String[] {"field2"};
		
		String[][] dml = new String[][] {
				//{"id", "1"},
				{"field1", "oooo"},
				{"field2", "500"},
				{"field3", "nnnn"}
		};
		
		String[] where = new String[] {"id", "1"};
		
		String[][] data = new String[][] {
				{"field1", "data1"},
				{"field2", "654"},
				{"field3", "data3"}
		};
		
		DataObject db = new DataAccess("Table1");
		//db.renameTable("Table1");
		//db.createTable("table1", ddl).insert(dml);
		//db.insert("table1", data);
		//Thread.sleep(600000);
		ArrayList<ArrayList<String>> resultSet = db.select(fields, where);
		db.close();
		String strResultSet = resultSet.get(0).get(0);
		System.out.println(strResultSet);
		
		
		
		ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> subTest1 = new ArrayList<String>();
		subTest1.add("1subData1");
		subTest1.add("1subData2");
		subTest1.add("1subData3");
		
		ArrayList<String> subTest2 = new ArrayList<String>();
		subTest2.add("2subData1");
		subTest2.add("2subData2");
		subTest2.add("2subData3");
		
		ArrayList<String> subTest3 = new ArrayList<String>();
		subTest3.add("3subData1");
		subTest3.add("3subData2");
		subTest3.add("3subData3");
		
		test.add(subTest1);
		test.add(subTest2);
		test.add(subTest3);
		
		test.get(1).add("whatever");
		String subElement = test.get(1).get(3);
		
		//System.out.println(subElement);
		
		
		
	}
	
	

}
