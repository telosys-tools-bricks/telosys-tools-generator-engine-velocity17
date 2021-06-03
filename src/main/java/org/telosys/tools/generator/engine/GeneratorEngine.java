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

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.telosys.tools.generator.engine.events.GeneratorEvents;

/**
 * Generator engine based on Velocity 1.7
 * 
 * @author Laurent GUERIN
 *
 */
public class GeneratorEngine {
	
	/**
	 * Constructor
	 */
	public GeneratorEngine() {
		super();
	}

	/**
	 * Generates using the given template and context. <br>
	 * The generation result is returned as a string. <br>
	 * 
	 * @param generatorTemplate
	 * @param generatorContext
	 * @return
	 * @throws Exception
	 */
	public String generate(GeneratorTemplate generatorTemplate, GeneratorContext generatorContext) {
		
		//------------------------------------------------------------------
		// Workaround for Velocity error in OSGi environment 
		//------------------------------------------------------------------
		Thread currentThread = Thread.currentThread();
		ClassLoader originalClassLoader = currentThread.getContextClassLoader();
		// Set the context ClassLoader for this Thread
		currentThread.setContextClassLoader(this.getClass().getClassLoader()); 
		//------------------------------------------------------------------
		
		// Launch the Velocity generator and catch all possible exceptions
		// All the Velocity exceptions are "Runtime Exceptions"
		try {
			return launchVelocityGeneration(generatorTemplate, generatorContext);
		} finally {
			//------------------------------------------------------------------
			// End of Workaround for Velocity error in OSGi environment
			//------------------------------------------------------------------
			currentThread.setContextClassLoader(originalClassLoader); // Restore the original classLoader
			//------------------------------------------------------------------
		}
	}
	
	/**
	 * Generates using the given template and context. <br>
	 * The generation result is returned as a string. <br>
	 * Throws only RuntimeExceptions (all VelocityExceptions are RuntimeExceptions) <br>
	 * 
	 * @param generatorTemplate
	 * @param generatorContext
	 * @return
	 */
	private String launchVelocityGeneration(GeneratorTemplate generatorTemplate, GeneratorContext generatorContext) { 
		
		//--- Check the template validity 
		generatorTemplate.checkValidity();
		
		//--- Retrieve the engine properties
		Properties properties = GeneratorProperties.buildProperties(generatorTemplate);
		
		//--- Create a new engine instance with the properties
		VelocityEngine velocityEngine = new VelocityEngine(properties);

		//--- Init the engine 
		velocityEngine.init();
		
		//--- Get the template file located in the current bundle folder
		//Template velocityTemplate = velocityEngine.getTemplate("template-a1.vm"); // OK		     
		//Template velocityTemplate = velocityEngine.getTemplate("dir1/template-a3.vm"); // OK
		//Template velocityTemplate = velocityEngine.getTemplate("dir1/template-a4.vm"); // OK with include without SPECIFIC EVENT	
		Template velocityTemplate = velocityEngine.getTemplate(generatorTemplate.getTemplateFileNameInBundle());
		// getTemplate throws the following exceptions :
		// . ResourceNotFoundException extends VelocityException
		// . ParseErrorException extends VelocityException

		//--- Create a VelocityContext from the given context
		VelocityContext velocityContext = createVelocityContext(generatorContext);
		//velocityContext.
		
		//--- Generate the result using template 'merge'
        StringWriter result = new StringWriter();
        velocityTemplate.merge( velocityContext, result);
		return result.toString() ;
		
		// velocityTemplate.merge(..) throws the following exceptions :
		//  . ResourceNotFoundException > VelocityException > RuntimeException
		//  . ParseErrorException       > VelocityException > RuntimeException
		//  . MethodInvocationException > VelocityException > RuntimeException
	}

	/**
	 * Creates a VelocityContext from the given GeneratorContext <br>
	 * and attach events to the VelocityContext <br>
	 * 
	 * @param generatorContext
	 * @return
	 */
	private VelocityContext createVelocityContext(GeneratorContext generatorContext ) {
		VelocityContext velocityContext = new VelocityContext(generatorContext.getMap());
		
		GeneratorEvents.attachEvents(velocityContext);
		
		return velocityContext ;
	}
	
}
