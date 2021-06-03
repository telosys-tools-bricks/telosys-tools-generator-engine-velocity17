package org.telosys.tools.generator.engine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import tests.util.GenerationUtil;

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
	
	private String generateFromTemplateFile(String bundleSubPath, String templateFile, GeneratorContext context) {
		return GenerationUtil.generateFromTemplateFile(context, bundleSubPath, templateFile);
	}

	//---------------------------------------------------------------------------------------

	@Test
	public void testEncoding01() {
		String s = generateFromTemplateFile(BUNDLE, "encoding-utf8-1.vm", getContext1());
		String expected = 
				  "abcdefghijk" + "\n"
				+ "é è ê ë à ç ù" + "\n"
				+ "name = éàùç";
		assertEquals(expected, s);
	}

	@Test
	public void testEncoding02() {
		String s = generateFromTemplateFile(BUNDLE, "encoding-utf8-2.vm", getContext2());
		String expected = 
				  " α β γ" + "\n"
				+ "name = δεη";
		assertEquals(expected, s);
	}

	@Test
	public void testEncoding03Iso88591() { 
		String s = generateFromTemplateFile(BUNDLE, "encoding-iso-8859-1.vm", getContext1());
		String expected = 
				  " é à è ù" + "\n"
				+ "name = éàùç";
		// different due to file encoding ( iso-8859-1 )
		assertNotEquals(expected, s);
	}

}
