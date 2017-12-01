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
/*
 * NB : each user directive must be defines in the Velocity properties
 * Example : 
 * userdirective=com.example.MyDirective1, com.example.MyDirective2
 * 
 */
/**
 * Velocity directive "#using"
 * 
 * @author Laurent Guerin
 *
 */
public class UsingDirective extends AbstractLineDirective {

	public UsingDirective() {
		super("using", -1); // 1 to N arguments
	}

	@Override
	// InternalContextAdapter contains everything Velocity needs to know about the template in order to render it
	// Writer is our template writer where we are going to write the result.
	// Node object contains information about our directive (its parameters and properties) 
	// Return : True if the directive rendered successfully.
	public boolean render(InternalContextAdapter context, Writer writer, Node node)	throws IOException {
		
		int errorsCount = 0 ;
		int n = node.jjtGetNumChildren();
		if ( n == 0 ) {
			throw new DirectiveException( "#" + this.getName() + " : invalid usage at least one object name expected", this.getName(), node.getTemplateName(), node.getLine() );
		}

		StringBuilder sbErrors = new StringBuilder();
		for ( int i = 0 ; i < n ; i++ ) {
			Node arg = node.jjtGetChild(i) ;
			if (arg != null) {
				String name = String.valueOf(arg.value(context)); // 'name' or '$name'
				String nameInContext = getObjectNameInContext(node, name) ; // 'name' (without '$')
				boolean found = context.containsKey( nameInContext );
				if ( ! found ) {
					errorsCount++ ;
					if ( errorsCount > 1 ) {
						sbErrors.append(", ");
					}
					sbErrors.append("'$"+ nameInContext + "'");
				}
			}
		}
		if ( errorsCount > 0 ) {
			String errorMessage = "#" + this.getName() + " : " + sbErrors.toString() + " required but not defined" ;
			throw new DirectiveException( errorMessage, this.getName(), node.getTemplateName(), node.getLine() );
		}
		return true;
	}
	
	private String getObjectNameInContext(Node node, String name) {
		if ( name != null && name.length() > 0 ) {
			if ( name.charAt(0) == '$' ) {
				return name.substring(1);
			}
			else {
				return name ;
			}
		}
		throw new DirectiveException( "#" + this.getName() + " : invalid argument", this.getName(), node.getTemplateName(), node.getLine() );
	}
}
