package org.telosys.tools.generator.engine;

import org.apache.velocity.exception.MethodInvocationException;
import org.junit.Test;
import org.telosys.tools.generator.engine.events.InvalidReferenceException;

import tests.util.FakeEntity;
import tests.util.FakeObject;
import tests.util.GenerationUtil;

public class GeneratorEventsTest {
	
	private static final String DIRECTIVES_BUNDLE = "templates/events" ;
	
	private GeneratorContext getContext() {
		GeneratorContext context = new GeneratorContext();
        context.put("name",    "Foo");
        context.put("project", "My project");
        context.put("flagTrue", true);
        context.put("flagFalse", false);
        context.put("entity1", new FakeEntity(true));
        context.put("entity2", new FakeEntity(false));
        context.put("object", new FakeObject());
        return context ;
	}

	//---------------------------------------------------------------------------------------
	
	public void generateFromTemplateFile(String bundleSubPath, String templateFile) {
		GenerationUtil.generateFromTemplateFile(getContext(), bundleSubPath, templateFile);
	}
	
	//---------------------------------------------------------------------------------------
	// Directive #error
	//---------------------------------------------------------------------------------------
	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceGet1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get1.vm") ;
	}

	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceGet2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get2.vm") ;
	}
	
	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceGet3() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get3.vm") ;
	}
	
	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceGet4() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get4.vm") ;
	}

	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceGet5() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get5.vm") ;
	}

	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceMethod1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-method1.vm") ;
	}

	@Test(expected=InvalidReferenceException.class)
	public void testInvalidReferenceMethod2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-method2.vm") ;
	}

	//@Test(expected=NullSetError.class)
	@Test(expected=InvalidReferenceException.class)
	public void testNullSetError1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-set1.vm") ;
	}

	// @Test(expected=MethodException.class)
	@Test(expected=MethodInvocationException.class) // Velocity exception
	public void testMethodException1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "method-exception1.vm") ;
	}

}
