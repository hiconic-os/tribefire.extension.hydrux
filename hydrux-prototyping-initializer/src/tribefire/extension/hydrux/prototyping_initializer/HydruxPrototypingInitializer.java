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
package tribefire.extension.hydrux.prototyping_initializer;

import com.braintribe.model.processing.session.api.collaboration.PersistenceInitializationContext;
import com.braintribe.wire.api.module.WireTerminalModule;

import tribefire.cortex.initializer.support.api.WiredInitializerContext;
import tribefire.cortex.initializer.support.impl.AbstractInitializer;
import tribefire.cortex.initializer.support.impl.CortexInitializerTools;
import tribefire.extension.hydrux.model.deployment.prototyping.HxMainView;
import tribefire.extension.hydrux.prototyping_initializer.wire.HydruxPrototypingInitializerWireModule;
import tribefire.extension.hydrux.prototyping_initializer.wire.contract.HydruxPrototypingInitializerContract;

/**
 * We have to:
 * <ul>
 * <li>Add hydrux-prototyping-deployment-model to the cortex-model.
 * <li>Configure the HX prototyping access HxDemoAcceess via {@link HydruxPrototypingInitializerContract#access()}.
 * </ul>
 */
public class HydruxPrototypingInitializer extends AbstractInitializer<HydruxPrototypingInitializerContract> {

	@Override
	public WireTerminalModule<HydruxPrototypingInitializerContract> getInitializerWireModule() {
		return HydruxPrototypingInitializerWireModule.INSTANCE;
	}

	@Override
	public void initialize(PersistenceInitializationContext context, WiredInitializerContext<HydruxPrototypingInitializerContract> initializerContext,
			HydruxPrototypingInitializerContract hydruxPrototypingInitializer) {

		CortexInitializerTools.addToCortexModel(context.getSession(), HxMainView.T.getModel());

		hydruxPrototypingInitializer.access();
	}
}
