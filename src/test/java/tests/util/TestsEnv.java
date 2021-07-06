package tests.util;

import java.io.File;

public class TestsEnv {

	private static final String SRC_TEST_RESOURCES = "src/test/resources/" ;

	public static String getTestFileAbsolutePath(String fileName) {
		File file = getTestFile( fileName ); 
		return file.getAbsolutePath();
	}
	
	public static File getTestFile(String fileName) {
		File file = new File(SRC_TEST_RESOURCES + fileName);
		if ( file.exists() ) {
			if ( file.isFile() ) {
				return file ;
			}
			else {
				throw new RuntimeException("Test resource file '"+ file.getName() + "' is not a file");
			}
		}
		else {
			throw new RuntimeException("Test resource file '"+ file.getName() + "' not found");
		}
	}

	public static File getTestRootFolder() {
		File folder = new File(SRC_TEST_RESOURCES);
		checkFolder(folder);
		return folder ;
	}

	public static File getTestFolder(String folderName) {
		File folder = new File(SRC_TEST_RESOURCES + folderName);
		checkFolder(folder);
		return folder ;
	}

	private static void checkFolder(File folder) {
		if ( folder.exists() ) {
			if ( folder.isDirectory() ) {
				return ;
			}
			else {
				throw new RuntimeException("Folder '"+ folder.getName() + "' is not a folder");
			}
		}
		else {
			throw new RuntimeException("Folder '"+ folder.getName() + "' doesn't exist");
		}
	}
	
}
