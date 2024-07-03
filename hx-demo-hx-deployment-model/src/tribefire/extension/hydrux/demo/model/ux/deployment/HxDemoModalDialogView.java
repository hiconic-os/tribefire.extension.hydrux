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
package tribefire.extension.hydrux.demo.model.ux.deployment;

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.hydrux.demo.model.ux.deployment.client.HxDemoChooseTextProcessingMethodRequestDialog;
import tribefire.extension.hydrux.model.deployment.HxRequestDialog;
import tribefire.extension.hydrux.model.deployment.HxView;

/**
 * Demo for a service-request which is evaluated locally, and the evaluation involves asking a user for input via {@link HxRequestDialog}.
 * <p>
 * The request is <tt>HxDemoProcessText</tt> (from <tt>hx-demo-api-model</tt>).<br>
 * the dialog is {@link HxDemoChooseTextProcessingMethodRequestDialog}
 * <p>
 * This view is bound as a normal view of course, and contains an input and a button. When the button is clicked, a <code>HxDemoProcessText</code> is
 * evaluated on the local evaluator. For this requests we use
 * <code>context.serviceProcessorBinder().bindDialogProcessor(HxDemoProcessText, dialog)</code>, where <code>dialog</code> is an instance of
 * {@link HxDemoChooseTextProcessingMethodRequestDialog}.
 * <p>
 * 
 * @author peter.gazdik
 */
public interface HxDemoModalDialogView extends HxView {

	EntityType<HxDemoModalDialogView> T = EntityTypes.T(HxDemoModalDialogView.class);

}
