// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package tribefire.extension.hydrux.model.deployment;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.annotation.Abstract;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.js.model.deployment.UxModule;

/**
 * Base type for JS UX elements. It is returned by the defined UX module contract (resolvable via {@link UxModule#getPath()}) . Each UX element is
 * modeled in order to support type safety and expressive coding.
 *
 * @see HxApplication
 * @see HxView
 * @see HxController
 */
@Abstract
public interface HxComponent extends GenericEntity {

	EntityType<HxComponent> T = EntityTypes.T(HxComponent.class);

	/**
	 * The module is referenced via its global id
	 */
	UxModule getModule();
	void setModule(UxModule module);

	/**
	 * The scope for this component. This is typically <tt>null</tt>, which simply means the scope is inherited from the scope of the caller.
	 * <p>
	 * For details about scopes see {@link HxScope}.
	 */
	HxScope getScope();
	void setScope(HxScope scope);

}
