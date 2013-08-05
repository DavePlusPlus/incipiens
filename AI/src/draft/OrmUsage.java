/**
 * 
 */
package draft;

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
		
		String[][] fields = new String[][] {
				{"id", "integer", "primary key"},
				{"field1", "varchar(25)"},
				{"field2", "integer"},
				{"field3", "varchar(25)"}
		};
		
		String[] column = new String[] {"newColumn2", "varchar(6)"};
		
		String[][] data = new String[][] {
				//{"id", "9"},
				{"field1", "newdata"},
				{"field2", "258"},
				{"field3", "wowdata"}
		};
		
		String[] where = new String[] {"id", "8"};
		
		DataObject db = new DataAccess();
	}

}
