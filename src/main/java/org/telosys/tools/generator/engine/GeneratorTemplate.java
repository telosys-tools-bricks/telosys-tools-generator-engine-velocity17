/**
 *  Copyright (C) 2008-2017  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.generator.engine;

import java.io.File;

/**
 * 
 * @author Laurent Guerin
 *
 */
public class GeneratorTemplate {

	private final String bundleFolderAbsolutePath ;

	private final String templateFileNameInBundle ;
	
	/**
	 * @param bundleFolderAbsolutePath
	 * @param templateFileNameInBundle
	 */
	public GeneratorTemplate(String bundleFolderAbsolutePath, String templateFileNameInBundle) {
		super(); 
		this.bundleFolderAbsolutePath = bundleFolderAbsolutePath ;
		this.templateFileNameInBundle = templateFileNameInBundle ;
	}

	/**
	 * Checks the template definition validity <br>
	 * Verifies if the bundle folder exists, if the template file exists in the bundle folder, etc...
	 */
	protected void checkValidity() { // throws GeneratorEngineException {
		checkBundleValidity();
	}

	/**
	 * Checks the bundle folder validity
	 */
	protected void checkBundleValidity() { // throws GeneratorEngineException {
		File bundleFolder = new File(bundleFolderAbsolutePath);
		if ( ! bundleFolder.exists() ) {
			//throw new GeneratorEngineException("Bundle folder not found '" + bundleFolderAbsolutePath + "' ");
			throw new InvalidBundleException("Bundle folder not found '" + bundleFolderAbsolutePath + "' ");
		}
		if ( ! bundleFolder.isDirectory() ) {
			//throw new GeneratorEngineException("Bundle folder is not a directory '" + bundleFolderAbsolutePath + "' ");
			throw new InvalidBundleException("Bundle folder is not a directory '" + bundleFolderAbsolutePath + "' ");
		}
	}

	/**
	 * @return
	 */
	protected String getBundleFolderAbsolutePath() {
		return this.bundleFolderAbsolutePath ;
	}
	
	/**
	 * @return
	 */
	protected String getTemplateFileNameInBundle() {
		return templateFileNameInBundle ;
	}

}
