package org.telosys.tools.generator.engine;

import org.junit.Test;

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


	//---------------------------------------------------------------------------------------
	
	public void generateFromTemplateFile(String bundleSubPath, String templateFile) { // throws GeneratorEngineException {
		System.out.println("Generation from template file "  );
		System.out.println(" . bundle sub path : " + bundleSubPath );
		System.out.println(" . template file   : " + templateFile );
		GeneratorTemplate template = TemplatesTestsUtil.buildGeneratorTemplateFromFile(bundleSubPath, templateFile);
		System.out.println("GeneratorTemplate created "  );
		GeneratorEngine generatorEngine = new GeneratorEngine();
		System.out.println("Generation... "  );
		String result = generatorEngine.generate(template, getContext() );
		System.out.println("Generation result : " );
		System.out.println(result );
	}
	
	@Test
	public void test10TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE1, "template-a1.vm");
	}
	@Test
	public void test11TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE1, "template-a1.vm");
	}
	@Test
	public void test23TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE1, "template-a2.vm");
	}
	@Test
	public void test24TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE1, "dir1/template-a3.vm");
	}
	@Test
	public void test25TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE1, "/dir1/template-a4.vm");
	}

	//--- TESTS WITH INVALID BUNDLE FOLDER
	@Test( expected=InvalidBundleException.class)
	public void test81TemplateFile()  { // throws GeneratorEngineException {
		generateFromTemplateFile(BUNDLE0_INEX, "template-a1.vm");
	}

	@Test( expected=InvalidBundleException.class)
	public void test82TemplateFile()  { // throws GeneratorEngineException {
		// invalid bundle path
		generateFromTemplateFile(BUNDLE0_INEX, "template-a2.vm");
	}
}
