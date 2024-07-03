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

import com.braintribe.devrock.templates.model.artifact.CreateTsLibrary;
import com.braintribe.model.generic.annotation.meta.Description;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.hydrux._HydruxSetupApiModel_;

@Description("Creates a TypeScript-based js-library artifact.")
public interface CreateHydruxLibrary extends CreateTsLibrary {

	EntityType<CreateHydruxLibrary> T = EntityTypes.T(CreateHydruxLibrary.class);

	@Override
	default String template() {
		return "tribefire.extension.hydrux:hydrux-library-template#" + TmpHelper.removeRevision(_HydruxSetupApiModel_.reflection.version());
	}

}

/* Just a temporary solution to get the major.minor as ArtifactReflection doesn't provide that. Remove once ArtifactReflection is improved. */
class TmpHelper {

	public static String removeRevision(String version) {
		int f = version.indexOf('.');
		int l = version.lastIndexOf('.');
		if (f < 0 || l < 0 || f == l)
			throw new IllegalArgumentException("Unexpected version format. Version: " + version);
		return version.substring(0, l);
	}

}
