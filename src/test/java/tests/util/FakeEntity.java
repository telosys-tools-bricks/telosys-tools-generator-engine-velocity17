package tests.util;

/**
 * Fake 'EntityInContext' class for tests 
 * 
 * @author laguerin
 *
 */
public class FakeEntity {

	private final boolean flag ;
	
	public FakeEntity(boolean flag) {
		this.flag = flag ;
	}

	public boolean hasPrimaryKey() {
		return flag ;
	}
}
