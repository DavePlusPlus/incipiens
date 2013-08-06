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

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String[][] ddl = new String[][] {
				{"id", "integer", "primary key"},
				{"field1", "varchar(25)"},
				{"field2", "integer"},
				{"field3", "varchar(25)"}
		};
		
		String[] fields = new String[] {"id", "field1", "field2"};
		
		String[][] dml = new String[][] {
				{"id", "5"},
				{"field1", "oooo"},
				{"field2", "500"},
				{"field3", "nnnn"}
		};
		
		String[] where = new String[] {"field3", "gggg"};
		
		DataObject db = new DataAccess();
		ArrayList<ArrayList<String>> dbtest = db.select("logTable", fields, where);
		ArrayList<ArrayList<String>> dbtest2 = db.select("logTable", fields, where);
		
		
		
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
		
		System.out.println(dbtest);
		System.out.println(Arrays.deepToString(test.toArray()));
		
		
		String[][] blabla = new String[][] {
				{"field1", "data1"},
				{"field2", "data2"},
				{"field3", "data3"},
				{"field4", "data4"}
		};
		
	}
	
	

}
