package org.telosys.tools.generator.engine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.telosys.tools.generator.engine.events.InvalidReferenceException;
import org.telosys.tools.generator.engine.fake.EntityInContext;

public class GeneratorEventsTest {
	
	private static final String DIRECTIVES_BUNDLE = "templates/events" ;
	
	private GeneratorContext getContext() {
		GeneratorContext context = new GeneratorContext();
        context.put("name",    "Foo");
        context.put("project", "My project");
        context.put("flagTrue", true);
        context.put("flagFalse", false);
        context.put("entity1", new EntityInContext(true));
        context.put("entity2", new EntityInContext(false));
        return context ;
	}

	//---------------------------------------------------------------------------------------
	
	public GeneratorEngineException generateFromTemplateFile(String bundleSubPath, String templateFile) throws GeneratorEngineException {
		System.out.println("Generation from template file "  );
		System.out.println(" . bundle sub path : " + bundleSubPath );
		System.out.println(" . template file   : " + templateFile );
		GeneratorTemplate template = TemplatesTestsUtil.buildGeneratorTemplateFromFile(bundleSubPath, templateFile);
		System.out.println("GeneratorTemplate created "  );
		GeneratorEngine generatorEngine = new GeneratorEngine();
		System.out.println("Generation... "  );
		GeneratorEngineException exceptionCatched = null ;
		String result;
		try {
			result = generatorEngine.generate(template, getContext() );
			System.out.println("Generation result : " );
			System.out.println(result );
		} catch (GeneratorEngineException e) {
			e.printStackTrace();
			exceptionCatched = e ;
		}
		return exceptionCatched ;
	}
	
	//---------------------------------------------------------------------------------------
	// Directive #error
	//---------------------------------------------------------------------------------------
	@Test 
	public void testInvalidReferenceGet1() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get1.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}
	@Test 
	public void testInvalidReferenceGet2() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}
	@Test 
	public void testInvalidReferenceGet3() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get3.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}
	@Test 
	public void testInvalidReferenceGet4() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get4.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}
	@Test 
	public void testInvalidReferenceGet5() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-get5.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}

	@Test 
	public void testInvalidReferenceSet1() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-set1.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}

	@Test 
	public void testInvalidReferenceMethod1() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-method1.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}
	@Test 
	public void testInvalidReferenceMethod2() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "invalid-reference-method2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof InvalidReferenceException );
	}

}
