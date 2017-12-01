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
package org.telosys.tools.generator.engine.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.Node;
import org.telosys.tools.commons.MethodInvoker;
/*
 * NB : each user directive must be defined in the Velocity properties
 * Example : 
 * userdirective=com.example.MyDirective1, com.example.MyDirective2
 * 
 * In this project it's defined in the Generator class 
 * 
 */
/**
 * Velocity directive <br>
 * 
 * This directive is used to check 'Id' or 'PK' existence in the given entity <br>
 * Designed to be used at the beginning of each template that requires an 'Id' in the entity <br>
 * 
 * @author Laurent Guerin
 *
 */
public class CheckIdDirective extends AbstractLineDirective {

	public CheckIdDirective() {
		super("checkId", 1);
	}

	@Override
	// InternalContextAdapter contains everything Velocity needs to know about the template in order to render it
	// Writer is our template writer where we are going to write the result.
	// Node object contains information about our directive (its parameters and properties) 
	// Return : True if the directive rendered successfully.
	public boolean render(InternalContextAdapter context, Writer writer, Node node)
			throws IOException {
		
		checkArgumentsCount(node);
		
		// Get the 1rst (and unique) argument of the directive
		Object o = getArgument(0, node, context);
		
		// This directive works by introspection in order to avoid cyclic reference with generator project
		// 1) Check class name
		String simpleName = o.getClass().getSimpleName() ;
		if ( ! "EntityInContext".equals(simpleName) ) {
			String message = "Invalid argument (class '" + simpleName +"' Entity expected) " ;
			throw new DirectiveException( message, this.getName(), node.getTemplateName(), node.getLine() );
		}
		// 2) Invoke "hasPrimaryKey" method
		boolean entityHasPrimaryKey;
		try {
			entityHasPrimaryKey = MethodInvoker.invokeBooleanGetter(o, "hasPrimaryKey");
		} catch (Exception e) {
			String message = "Cannot call 'hasPrimaryKey' method (probably not an entity)" ;
			throw new DirectiveException( message, this.getName(), node.getTemplateName(), node.getLine() );
		}
		if ( entityHasPrimaryKey ) {
			return true ; // OK
		}
		else {
			String message = "The entity must have an ID/PK (required by this template)" ;
			throw new DirectiveException( message, this.getName(), node.getTemplateName(), node.getLine() );
		}
	}
	
}
