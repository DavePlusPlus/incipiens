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
				{"newField1", "integer", "primary key"},
				{"newField2", "varchar(26)"},
				{"newField3", "integer"},
				{"newField4", "varchar(55)"}
		};
		
		String[] column = new String[] {"newColumn2", "varchar(6)"};
		
		DataObject db = new DataAccess();
	}

}
