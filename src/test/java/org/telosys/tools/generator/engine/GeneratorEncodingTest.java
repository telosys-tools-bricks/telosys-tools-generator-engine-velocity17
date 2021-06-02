package org.telosys.tools.generator.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GeneratorEncodingTest {
	
	private static final String BUNDLE = "templates/encoding" ;
	
	private GeneratorContext getContext1() {
		GeneratorContext context = new GeneratorContext();
        context.put("name", "éàùç");
        return context ;
	}
	
	private GeneratorContext getContext2() {
		GeneratorContext context = new GeneratorContext();
        context.put("name", "δεη");
        return context ;
	}
	//---------------------------------------------------------------------------------------
	
	public String generateFromTemplateFile(String bundleSubPath, String templateFile, GeneratorContext context) { //throws GeneratorEngineException {
		System.out.println("Generation from template file "  );
		System.out.println(" . bundle sub path : " + bundleSubPath );
		System.out.println(" . template file   : " + templateFile );
		GeneratorTemplate template = TemplatesTestsUtil.buildGeneratorTemplateFromFile(bundleSubPath, templateFile);
		System.out.println("GeneratorTemplate created "  );
		GeneratorEngine generatorEngine = new GeneratorEngine();
		System.out.println("Generation... "  );
		String result = generatorEngine.generate(template, context );
		System.out.println("Generation result : " );
		System.out.println(result );
		return result;
	}
	
	@Test
	public void testEncoding01() { // throws GeneratorEngineException {
		String s = generateFromTemplateFile(BUNDLE, "encoding-utf8-1.vm", getContext1());
		String expected = 
				  "abcdefghijk" + "\n"
				+ "é è ê ë à ç ù" + "\n"
				+ "name = éàùç";
		assertEquals(expected, s);
	}

	@Test
	public void testEncoding02() { //throws GeneratorEngineException {
		String s = generateFromTemplateFile(BUNDLE, "encoding-utf8-2.vm", getContext2());
		String expected = 
				  " α β γ" + "\n"
				+ "name = δεη";
		assertEquals(expected, s);
	}

	@Test
	public void testEncoding03Iso88591() { //throws GeneratorEngineException {
		String s = generateFromTemplateFile(BUNDLE, "encoding-iso-8859-1.vm", getContext1());
		String expected = 
				  " é à è ù" + "\n"
				+ "name = éàùç";
		// different due to file encoding ( iso-8859-1 )
		assertNotEquals(expected, s);
	}

}
