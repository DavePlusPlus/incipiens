/**
 * 
 */
package Impl;
/**
 * @author Dave (http://about.me/david.herrera)
 *
 */
public class Draft<T> implements Inter<T> {
	
	private T t;
	
	public Draft(T t) {
		this.t = t;
	}
	
	public T get() {
		return t;
	}
}