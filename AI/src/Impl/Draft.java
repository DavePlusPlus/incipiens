/**
 * 
 */
package Impl;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Draft {
	
	private String var1;
	private String var2;
	
	public String var1() {
		return this.var1;
	}
	
	public String var2() {
		return this.var2;
	}
	
	public void var1(String lvar1) {
		this.var1 = lvar1;
	}
	
	public void var2(String lvar2) {
		this.var2 = lvar2;
	}
	
	public Draft(String lvar1, String lvar2) {
		this.var1(lvar1);
		this.var2(lvar2);
	}

}