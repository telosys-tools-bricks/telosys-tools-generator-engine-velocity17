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
package org.telosys.tools.generator.engine.events;

import org.apache.velocity.app.event.MethodExceptionEventHandler;

/**
 * Velocity MethodExceptionEventHandler implementation
 * 
 * @author Laurent Guerin
 * @since 3.3.0
 *
 */
public class MethodExceptionEventHandlerImpl implements MethodExceptionEventHandler {

	/*
     * Called when a method throws an exception.
     * Only the first registered MethodExceptionEventHandler is called.  If
     * none are registered a MethodInvocationException is thrown.
	 */
	@Override
	public Object methodException(Class clazz, String method, Exception e) {
		throw new MethodException( 
				"Exception " + e.getClass().getSimpleName() 
				+ " in method '" + method + "'"
				+ " in class " + clazz.getCanonicalName(),
				e);
	}

}
