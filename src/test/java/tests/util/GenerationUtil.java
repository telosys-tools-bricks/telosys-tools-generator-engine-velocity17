package tests.util;

import java.io.File;

import org.telosys.tools.commons.FileUtil;
import org.telosys.tools.generator.engine.GeneratorContext;
import org.telosys.tools.generator.engine.GeneratorEngine;
import org.telosys.tools.generator.engine.GeneratorTemplate;

import static org.junit.Assert.assertTrue;

public class GenerationUtil {

	private GenerationUtil() {
	}

	private static final void println(String msg) {
		System.out.println(msg);
	}
	private static final void log(String msg) {
		//println(msg);
	}
	private static final void logInfo(String msg) {
		println(msg);
	}
	private static final void logInfo(Exception e) {
		println(" --> EXCEPTION : " + e.getClass().getSimpleName() + " : " + e.getMessage());
	}
	
	/**
	 * Launch generation 
	 * @param context
	 * @param bundleSubPath
	 * @param templateFile
	 */
	public static String generateFromTemplateFile(GeneratorContext context, 
			String bundleSubPath, String templateFile) {
		logInfo("Generation : " + bundleSubPath + "/" + templateFile);

		log("Generation from template file "  );
		log(" . bundle sub path : " + bundleSubPath );
		log(" . template file   : " + templateFile );
		GeneratorTemplate template = buildGeneratorTemplateFromFile(bundleSubPath, templateFile);
		log("GeneratorTemplate created "  );
		GeneratorEngine generatorEngine = new GeneratorEngine();
		log("Generation... "  );
		String result;
		try {
			result = generatorEngine.generate(template, context);
			logInfo(" --> OK (no exception)");
		} catch (Exception e) {
			logInfo(e);
			throw e;
		}
		log("Generation result : " );
		log(result );
		return result;
	}
	
	private static GeneratorTemplate buildGeneratorTemplateFromFile(String bundleSubPath, String templateFileNameInBundle) {
		log("Building GeneratorTemplate from '" + bundleSubPath + "'");
		log(" . bundle   : '" + bundleSubPath + "'");
		log(" . template : '" + templateFileNameInBundle + "'");
		
		checkTestRootFolder() ;
		String bundleFolderAbsolutePath = buildBundleFolderAbsolutePath(bundleSubPath);
		
		log(" . bundle folder : '" + bundleFolderAbsolutePath + "'");
		return new GeneratorTemplate(bundleFolderAbsolutePath, templateFileNameInBundle);
	}

	private static void checkTestRootFolder() {
		File root = TestsEnv.getTestRootFolder();
		assertTrue( root.exists() );
		assertTrue( root.isDirectory() );			
	}
	
	private static String buildBundleFolderAbsolutePath(String bundleSubPath) {
		log("Building bundle folder from '" + bundleSubPath + "'");
		return FileUtil.buildFilePath(TestsEnv.getTestRootFolder().getAbsolutePath(), bundleSubPath);
	}

}
