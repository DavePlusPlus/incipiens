/**
 * 
 */
package Impl;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Draft2 {

	public Draft2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Draft test = new Draft("First value1", "First value2");
		System.out.println(test.var1());
		System.out.println(test.var2());
		test.var1("New value1");
		test.var2("New value2");
		System.out.println(test.var1());
		System.out.println(test.var2());
	}

}
