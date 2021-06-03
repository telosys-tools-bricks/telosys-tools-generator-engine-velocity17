package org.telosys.tools.generator.engine;

import org.apache.velocity.exception.ParseErrorException;
import org.junit.Test;
import org.telosys.tools.generator.engine.directive.DirectiveException;

import tests.util.FakeEntity;
import tests.util.GenerationUtil;

public class GeneratorDirectivesTest {
	
	private static final String DIRECTIVES_BUNDLE = "templates/directives" ;
	
	private GeneratorContext getContext() {
		GeneratorContext context = new GeneratorContext();
        context.put("name",    "Foo");
        context.put("project", "My project");
        context.put("flagTrue", true);
        context.put("flagFalse", false);
        context.put("entity1", new FakeEntity(true) ); // has PK
        context.put("entity2", new FakeEntity(false)); // no PK
        return context ;
	}

	//---------------------------------------------------------------------------------------
	
	private void generateFromTemplateFile(String bundleSubPath, String templateFile) {
		GenerationUtil.generateFromTemplateFile(getContext(), bundleSubPath, templateFile);
	}
	
	//---------------------------------------------------------------------------------------
	// Directive #error
	//---------------------------------------------------------------------------------------
	@Test( expected=DirectiveException.class)
	public void testDirectiveError1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-error1.vm") ;
	}

	@Test( expected=ParseErrorException.class)
	public void testDirectiveError2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-error2.vm") ;
	}
	
	//---------------------------------------------------------------------------------------
	// Directive #assertTrue
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveAssertTrue1() { 
		// Test directive '#assertTrue' : OK expected "
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-true1.vm");
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveAssertTrue2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-true2.vm") ;
	}

	//---------------------------------------------------------------------------------------
	// Directive #assertFalse
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveAssertFalse1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false1.vm");
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveAssertFalse2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false2.vm") ;
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveAssertFalse3() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-assert-false3.vm") ;
	}

	//---------------------------------------------------------------------------------------
	// Directive #using
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveUsing1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using1.vm");
	}
	
	@Test( expected=DirectiveException.class)
	public void testDirectiveUsing2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using2.vm") ;
	}
	
	@Test( expected=DirectiveException.class)
	public void testDirectiveUsing3() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using3.vm") ;
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveUsing4() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using4.vm") ;
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveUsing5() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using5.vm") ;
	}
	
	@Test( expected=DirectiveException.class)
	public void testDirectiveUsing6() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-using6.vm") ;
	}

	//---------------------------------------------------------------------------------------
	// Directive #checkId($entity)
	//---------------------------------------------------------------------------------------
	@Test
	public void testDirectiveCheckId1() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id1.vm");
	}
	
	@Test( expected=DirectiveException.class)
	public void testDirectiveCheckId2() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id2.vm") ;
	}

	@Test( expected=DirectiveException.class)
	public void testDirectiveCheckId3() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id3.vm") ;
	}
	
	@Test( expected=DirectiveException.class)
	public void testDirectiveCheckId4() { 
		generateFromTemplateFile(DIRECTIVES_BUNDLE, "directive-check-id4.vm") ;
	}
}
