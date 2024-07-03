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
package tribefire.extension.hydrux.model.deployment.prototyping;

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.hydrux.model.deployment.HxApplication;
import tribefire.extension.hydrux.model.deployment.HxView;

/**
 * The main {@link HxView view} of a prototype application.
 * <p>
 * When a prototype application resolves its {@link HxApplication}, the Hydrux servlet always returns an application whose
 * {@link HxApplication#getView() view} is an {@link HxMainView}.
 * <p>
 * This view is not actually configured on the HxApplication, but is created dynamically, because it's {@link HxView#getModule()} needs to be created
 * dynamically, based on URL parameters, because that's the whole point of hydrux-prototyping - no need to configure anything on the server side.
 * <p>
 * The client prototype application then simply binds this view:
 * 
 * <pre>
 * export const contract: hx.IHxModuleContract = {
 * 	    bind(context: hx.IHxModuleBindingContext): void {
 * 			context.componentBinder().bindView(HxMainView, mainView); 
 * 		}
 * }

 * async function mainView(denotation: HxMainView, context: hx.IHxComponentCreationContext): Promise<hx.IHxView> {
 *    ...
 * }
 * </pre>
 * 
 * @see HxView
 */
public interface HxMainView extends HxView {

	EntityType<HxMainView> T = EntityTypes.T(HxMainView.class);

	String PROTOTYPING_DOMAIN_ID = "hydrux-prototyping";
	
}
