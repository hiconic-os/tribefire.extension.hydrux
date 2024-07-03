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
package tribefire.extension.hydrux.setup.model;

import java.util.Set;

import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.annotation.meta.Mandatory;
import com.braintribe.model.generic.annotation.meta.PositionalArguments;
import com.braintribe.model.generic.eval.EvalContext;
import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;
import com.braintribe.model.service.api.ServiceRequest;
import com.braintribe.model.service.api.result.Neutral;

/**
 * @author peter.gazdik
 */
@PositionalArguments({ "name" })
public interface CreateHydruxProject extends HydruxSetupRequest {

	EntityType<CreateHydruxProject> T = EntityTypes.T(CreateHydruxProject.class);

	@Description("The base name for the artifacts that will be created. Example: "
			+ "If name is 'my-project', expect creation of artifacts like 'my-project-ux-module', 'my-project-ux-deployment-model' and optionally others.")
	@Mandatory
	String getName();
	void setName(String name);

	Set<HydruxProjectPart> getParts();
	void setParts(Set<HydruxProjectPart> parts);

	boolean getOverwrite();
	void setOverwrite(boolean Overwrite);

	@Override
	EvalContext<? extends Neutral> eval(Evaluator<ServiceRequest> evaluator);

}
