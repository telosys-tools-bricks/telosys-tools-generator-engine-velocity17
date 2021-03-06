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

import java.util.Properties;

import org.apache.velocity.runtime.RuntimeConstants;
import org.telosys.tools.generator.engine.directive.AssertFalseDirective;
import org.telosys.tools.generator.engine.directive.AssertTrueDirective;
import org.telosys.tools.generator.engine.directive.CancelDirective;
import org.telosys.tools.generator.engine.directive.CheckIdDirective;
import org.telosys.tools.generator.engine.directive.ErrorDirective;
import org.telosys.tools.generator.engine.directive.UsingDirective;

/**
 * 
 */
class GeneratorProperties { // "package" visibility
	
	private static final String USER_DIRECTIVE_NAME  = "userdirective" ;
	private static final String USER_DIRECTIVE_VALUE = getDirectives() ;
	
	/**
	 * Private constructor
	 */
	private GeneratorProperties() {	
	}
	
	/**
	 * Returns a list of all the classes used as "Specific Directives" <br>
	 * @return a string with all class names separated by a comma
	 */
	private static String getDirectives() {
		return 
				UsingDirective.class.getCanonicalName() 
				+ ", " 
				+ AssertTrueDirective.class.getCanonicalName() 
				+ ", " 
				+ AssertFalseDirective.class.getCanonicalName() 
				+ ", " 
				+ CheckIdDirective.class.getCanonicalName() 
				+ ", " 
				+ ErrorDirective.class.getCanonicalName() 
				+ ", " 
				+ CancelDirective.class.getCanonicalName()  // v 3.3.0
				; // one or n directive(s) separated by a comma 
	}	

	public static final Properties buildProperties(GeneratorTemplate generatorTemplate) {

		Properties properties = new Properties();

		//--- #include & #parse management
		// Force the templates path to "bundle folder" instead of "."
		// Path for #parse and #include 
		properties.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, generatorTemplate.getBundleFolderAbsolutePath() );
		
		//--- Specific 'directives' management
		// Set all the "user directives" ( list of classes names ) : OK, it works
		properties.setProperty(USER_DIRECTIVE_NAME, USER_DIRECTIVE_VALUE);
		
		// To force the macro reloading for each execution (usefull for development mode, no perf constraint)
		// Constant for "autoreload" is VM_LIBRARY_AUTORELOAD 
		properties.setProperty(RuntimeConstants.VM_LIBRARY_AUTORELOAD, "true");
		
		// To avoid logging ( no 'velocity.log' file )
		// runtime.log.logsystem.class=org.apache.velocity.runtime.log.NullLogChute
		properties.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");

		// Input and output encoding
		final String UTF8 = "UTF-8" ;
		properties.setProperty(RuntimeConstants.INPUT_ENCODING, UTF8);
		properties.setProperty(RuntimeConstants.OUTPUT_ENCODING, UTF8);

		return properties;
	}
}
