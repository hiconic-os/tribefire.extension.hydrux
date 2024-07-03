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
package tribefire.extension.hydrux.model.api;

import java.util.Set;

import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;

import tribefire.extension.hydrux.model.deployment.HxApplication;
import tribefire.extension.hydrux.model.deployment.prototyping.HxMainView;
import tribefire.extension.js.model.deployment.UxModule;

/**
 * Resolves the {@link HxApplication} meta-data for given {@link #getTargetDomainId() domainId}.
 * 
 * @author peter.gazdik
 */
public interface ResolveHxApplication extends HxRequest {

	EntityType<ResolveHxApplication> T = EntityTypes.T(ResolveHxApplication.class);

	@Mandatory
	String getTargetDomainId();
	void setTargetDomainId(String targetDomainId);

	Set<String> getUseCases();
	void setUseCases(Set<String> useCases);

	/**
	 * The name of the {@link UxModule} to set on the {@link HxApplication#getView() result's view}. This is only relevant for the prototyping domain,
	 * in which case the view is {@link HxMainView}.
	 */
	String getPrototypingModule();
	void setPrototypingModule(String prototypingModule);

	@Override
	EvalContext<HxApplication> eval(Evaluator<ServiceRequest> evaluator);
}
