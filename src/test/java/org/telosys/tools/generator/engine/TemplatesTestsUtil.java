package org.telosys.tools.generator.engine;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.telosys.tools.commons.FileUtil;

import tests.env.TestsEnv;

public class TemplatesTestsUtil {
	
	public static GeneratorTemplate buildGeneratorTemplateFromFile(String bundleSubPath, String templateFileNameInBundle) {
		System.out.println("Building GeneratorTemplate from '" + bundleSubPath + "'");
		System.out.println(" . bundle   : '" + bundleSubPath + "'");
		System.out.println(" . template : '" + templateFileNameInBundle + "'");
		
		checkTestRootFolder() ;
		//File templateFile = buildTemplateFile(templateFilePath);
		String bundleFolderAbsolutePath = buildBundleFolderAbsolutePath(bundleSubPath);
		//checkBundleFolder(bundleFolderAbsolutePath);
		
		System.out.println(" . bundle folder (String) : '" + bundleFolderAbsolutePath + "'");
		//System.out.println(" . bundle folder (File)   : '" + new File(bundleFolder).getAbsolutePath() + "'");
		//System.out.println(" . template file (File)   : '" + templateFile.getAbsolutePath() + "'");
		return new GeneratorTemplate(bundleFolderAbsolutePath, templateFileNameInBundle);
	}

	private static void checkTestRootFolder() {
		File root = TestsEnv.getTestRootFolder();
		assertTrue( root.exists() );
		assertTrue( root.isDirectory() );			
	}
	
	private static String buildBundleFolderAbsolutePath(String bundleSubPath) {
		System.out.println("Building bundle folder from '" + bundleSubPath + "'");
		return FileUtil.buildFilePath(TestsEnv.getTestRootFolder().getAbsolutePath(), bundleSubPath);
	}

	
	static void checkBundleFolder(String bundleFolder) {
		System.out.println("Checking bundle folder : " + bundleFolder);
		File bundelFolderFile = new File(bundleFolder);
		assertTrue(bundelFolderFile.exists());
		assertTrue(bundelFolderFile.isDirectory());
		assertTrue(bundelFolderFile.isAbsolute());
	}

	static File buildTemplateFile(String templateFile) {
		System.out.println("Building template file from '" + templateFile + "'");
		File file = TestsEnv.getTestFile(templateFile) ;
		System.out.println("File : " + file);
		assertTrue(file.exists());
		assertTrue(file.isFile());
		return (file);
	}

}
