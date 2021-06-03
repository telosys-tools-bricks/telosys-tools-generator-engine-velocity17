package tests.util;

/**
 * Fake 'EntityInContext' class for tests 
 * 
 * @author laguerin
 *
 */
public class FakeObject {
	
	public FakeObject() {
		super() ;
	}

	public String getName() {
		return "Bob";
	}

	public boolean doWithException() {
		throw new IllegalArgumentException("invalid arguments");
	}
}
