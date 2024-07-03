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
package tribefire.extension.hydrux.demo.initializer.wire.contract;

import com.braintribe.wire.api.space.WireSpace;

import tribefire.cortex.initializer.support.impl.lookup.GlobalId;
import tribefire.cortex.initializer.support.impl.lookup.InstanceLookup;
import tribefire.extension.js.model.deployment.UxModule;

@InstanceLookup(lookupOnly = true)
public interface ExistingInstancesContract extends WireSpace {

	String GROUP_ID = "tribefire.extension.hydrux";
	String MODEL_PREFIX = "model:" + GROUP_ID + ":";
	String JS_UX_MODULE_PREFIX = "js-ux-module://";

	@GlobalId(JS_UX_MODULE_PREFIX + GROUP_ID + ":hx-demo-hx-module")
	UxModule hxComponentModule();

}
