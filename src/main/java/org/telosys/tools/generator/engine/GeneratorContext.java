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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GeneratorContext {
	
	private final HashMap<String,Object> map = new HashMap<>();
	
	protected final Map<String,Object> getMap() {
		return map;
	}

	public final void put(String key, Object object) {
		map.put(key, object);
	}

	public final Object get(String key) {
		return map.get(key);
	}

	public final void clear() {
		map.clear();
	}

	public final Object remove(String key) {
		return map.remove(key);
	}

	public final Set<String> keySet() {
		return map.keySet();
	}

}
