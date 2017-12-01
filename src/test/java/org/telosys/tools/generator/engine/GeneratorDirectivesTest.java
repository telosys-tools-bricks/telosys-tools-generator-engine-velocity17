package org.telosys.tools.generator.engine;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.velocity.exception.ParseErrorException;
import org.junit.Test;
import org.telosys.tools.generator.engine.directive.DirectiveException;
import org.telosys.tools.generator.engine.fake.EntityInContext;

public class GeneratorDirectivesTest {
	
	private static final String DIRECTIVES_BUNDLE = "templates/directives" ;
	
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
	public void testDirectiveError1() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-error1.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}

	@Test 
	public void testDirectiveError2() throws GeneratorEngineException {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-error2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof ParseErrorException );
	}
	
	//---------------------------------------------------------------------------------------
	// Directive #assertTrue
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveAssertTrue1() throws Exception {
		System.out.println("Test directive '#assertTrue' : OK expected "  );
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-true1.vm");
	}

	@Test
	public void testDirectiveAssertTrue2() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-true2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}

	//---------------------------------------------------------------------------------------
	// Directive #assertFalse
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveAssertFalse1() throws Exception {
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false1.vm");
	}

	@Test
	public void testDirectiveAssertFalse2() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}

	@Test
	public void testDirectiveAssertFalse3() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false3.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}

	//---------------------------------------------------------------------------------------
	// Directive #using
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveUsing1() throws Exception {
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using1.vm");
	}
	@Test
	public void testDirectiveUsing2() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
	@Test
	public void testDirectiveUsing3() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using3.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
	@Test
	public void testDirectiveUsing4() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using4.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
	@Test
	public void testDirectiveUsing5() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using5.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
	@Test
	public void testDirectiveUsing6() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using6.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}

	//---------------------------------------------------------------------------------------
	// Directive #checkId($entity)
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveCheckId1() throws Exception {
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id1.vm");
	}
	@Test
	public void testDirectiveCheckId2() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id2.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
	@Test
	public void testDirectiveCheckId3() throws Exception {
		GeneratorEngineException exception = generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id3.vm") ;
		assertNotNull(exception);
		assertTrue( exception.getCause() instanceof DirectiveException );
	}
}
