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
package tribefire.extension.hydrux.demo.processor;

import com.braintribe.model.generic.eval.Evaluator;
import com.braintribe.model.processing.service.api.ServiceRequestContext;
import com.braintribe.model.processing.service.impl.AbstractDispatchingServiceProcessor;
import com.braintribe.model.processing.service.impl.DispatchConfiguration;
import com.braintribe.model.service.api.CallbackPushAddressing;
import com.braintribe.model.service.api.PushRequest;
import com.braintribe.model.service.api.ServiceRequest;

import tribefire.extension.hydrux.demo.model.api.HxDemoComputeTextLength;
import tribefire.extension.hydrux.demo.model.api.HxDemoRequest;
import tribefire.extension.hydrux.demo.model.api.HxDemoThrowException;
import tribefire.extension.hydrux.demo.model.api.push.HxDemoNotifyProgress;
import tribefire.extension.hydrux.demo.model.api.push.HxDemoSendProcessResult;
import tribefire.extension.hydrux.demo.model.api.push.HxDemoStartComplexProcessOnServer;

/**
 * @author peter.gazdik
 */
public class HxDemoRequestProcessor extends AbstractDispatchingServiceProcessor<HxDemoRequest, Object> {

	private Evaluator<ServiceRequest> evaluator;

	public void setEvaluator(Evaluator<ServiceRequest> evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	protected void configureDispatching(DispatchConfiguration<HxDemoRequest, Object> dispatching) {
		dispatching.register(HxDemoComputeTextLength.T, this::computeTextLength);
		dispatching.register(HxDemoThrowException.T, this::throwException);
		dispatching.register(HxDemoStartComplexProcessOnServer.T, this::callServer);
	}

	private Integer computeTextLength(@SuppressWarnings("unused") ServiceRequestContext ctx, HxDemoComputeTextLength request) {
		String text = request.getText();

		return text == null ? 0 : text.trim().length();
	}

	@SuppressWarnings("unused")
	private Boolean throwException(ServiceRequestContext ctx, HxDemoThrowException request) {
		throw new RuntimeException("This is the exception you wanted!");
	}

	@SuppressWarnings("unused")
	private Boolean callServer(ServiceRequestContext ctx, HxDemoStartComplexProcessOnServer request) {
		new Thread(new ComplexProcessRun(request)).start();

		return true;
	}

	private class ComplexProcessRun implements Runnable {

		private final CallbackPushAddressing pushAddress;

		public ComplexProcessRun(HxDemoStartComplexProcessOnServer request) {
			this.pushAddress = request.getPushAddress();
		}

		@Override
		public void run() {
			waitMs(700);
			notifyClient("Complex process on server started!");
			waitMs(1000);
			notifyClient("Sit straight!");
			waitMs(1500);
			notifyClient("Don't bite your fingers!");
			waitMs(1500);
			notifyClient("Done!");
			waitMs(1000);
			notifyProcessFinished();
		}

		private void notifyClient(String msg) {
			if (pushAddress == null)
				return;

			HxDemoNotifyProgress request = HxDemoNotifyProgress.T.create();
			request.setMessage(msg);

			send(request);
		}

		private void notifyProcessFinished() {
			HxDemoSendProcessResult request = HxDemoSendProcessResult.T.create();
			request.setResult("ALL DONE!");

			send(request);
		}

		private void send(ServiceRequest notify) {
			PushRequest pr = pushAddress.pushify(notify);
			pr.eval(evaluator).get(null);
		}

		private void waitMs(int millis) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				throw new RuntimeException("We got interrupted", e);
			}
		}

	}

}
