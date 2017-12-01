package org.telosys.tools.generator.engine.fake;

/**
 * Fake 'EntityInContext' class for tests 
 * 
 * @author laguerin
 *
 */
public class EntityInContext {

	private final boolean flag ;
	
	public EntityInContext(boolean flag) {
		this.flag = flag ;
	}

	public boolean hasPrimaryKey() {
		return flag ;
	}
}
