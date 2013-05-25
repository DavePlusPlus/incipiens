/**
 * 
 */
package inGCore;

import javafx.application.Application;

/**
 * 
 * @author Dave (http://about.me/david.herrera)
 */
public class InGSys {

	public InGSys() {}

	/**
	 * 
	 * @author Dave (http://about.me/david.herrera)
	 *
	 */
	public static abstract class InGController implements InGEnv {
		
	}
	
	/**
	 * 
	 * @author Dave (http://about.me/david.herrera)
	 *
	 */
	public static abstract class InGApplication extends Application implements InGEnv {
		
	}
	
	/**
	 * 
	 * @author Dave (http://about.me/david.herrera)
	 *
	 */
	public static abstract class InGProfiler implements InGEnv {
		
	}

}
