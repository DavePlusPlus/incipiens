/**
 * 
 */
package Impl;

/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Draft2 {

	public Draft2() {}

	public static void main(String[] args) {
		Inter<String> test = new Draft<>("Hello world");
		System.out.println(test.get());
	}

}
