package org.telosys.tools.generator.engine;

import org.junit.Test;

import tests.util.GenerationUtil;

public class GeneratorEngineTest {
	
	private static final String BUNDLE0_INEX = "templates/bundle0" ; // non-existent
	
	private static final String BUNDLE1 = "templates/bundle1" ;
	
	private GeneratorContext getContext() {
		GeneratorContext context = new GeneratorContext();
        context.put("name",    "Foo");
        context.put("project", "My project");
        context.put("flagTrue", true);
        context.put("flagFalse", false);
        return context ;
	}

	public void generateFromTemplateFile(String bundleSubPath, String templateFile) {
		GenerationUtil.generateFromTemplateFile(getContext(), bundleSubPath, templateFile);
	}
	
	//---------------------------------------------------------------------------------------
	
	@Test
	public void test10TemplateFile()  {
		generateFromTemplateFile(BUNDLE1, "template-a1.vm");
	}
	@Test
	public void test11TemplateFile()  {
		generateFromTemplateFile(BUNDLE1, "template-a1.vm");
	}
	@Test
	public void test23TemplateFile()  {
		generateFromTemplateFile(BUNDLE1, "template-a2.vm");
	}
	@Test
	public void test24TemplateFile()  {
		generateFromTemplateFile(BUNDLE1, "dir1/template-a3.vm");
	}
	@Test
	public void test25TemplateFile()  { 
		generateFromTemplateFile(BUNDLE1, "/dir1/template-a4.vm");
	}

	//--- TESTS WITH INVALID BUNDLE FOLDER
	@Test( expected=InvalidBundleException.class)
	public void test81TemplateFile()  { 
		generateFromTemplateFile(BUNDLE0_INEX, "template-a1.vm");
	}

	@Test( expected=InvalidBundleException.class)
	public void test82TemplateFile()  { 
		// invalid bundle path
		generateFromTemplateFile(BUNDLE0_INEX, "template-a2.vm");
	}
}
