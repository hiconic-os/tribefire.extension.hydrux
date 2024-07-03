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
package tribefire.extension.hydrux.demo.model.api.push;

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * This is sent from server to client to display a progress message to the user.
 * 
 * @author peter.gazdik
 */
public interface HxDemoSendProcessResult extends HxDemoMessageToClient {

	EntityType<HxDemoSendProcessResult> T = EntityTypes.T(HxDemoSendProcessResult.class);

	String getResult();
	void setResult(String result);

}
