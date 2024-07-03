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
package tribefire.extension.hydrux.wire.space;

import com.braintribe.model.processing.session.api.managed.ModelAccessoryFactory;
import com.braintribe.utils.lcd.StringTools;
import com.braintribe.web.api.registry.WebRegistries;
import com.braintribe.wire.api.annotation.Import;
import com.braintribe.wire.api.annotation.Managed;

import tribefire.extension.hydrux.model.api.HxRequest;
import tribefire.extension.hydrux.processor.HxRequestProcessor;
import tribefire.extension.hydrux.servlet.HydruxServlet;
import tribefire.module.wire.contract.ModuleResourcesContract;
import tribefire.module.wire.contract.TribefireModuleContract;
import tribefire.module.wire.contract.TribefireWebPlatformContract;

/**
 * This module's javadoc is yet to be written.
 */
@Managed
public class HydruxModuleSpace implements TribefireModuleContract {

	@Import
	private TribefireWebPlatformContract tfPlatform;

	@Import
	private ModuleResourcesContract moduleResources;

	//
	// Hardwired deployables
	//

	@Override
	public void bindHardwired() {
		tfPlatform.hardwiredDeployables().bindOnNewServiceDomain("hydrux", "Hydrux") //
				.serviceProcessor("processor.hydrux", "Hydrux Request Processor", HxRequest.T, hydruxProcessor());

		tfPlatform.hardwiredDeployables().webRegistry().addServlet(WebRegistries.servlet() //
				.name("Hydrux Servlet") //
				.instance(hydruxServlet()) //
				.pattern("/hydrux/*") //
		);
	}

	private HxRequestProcessor hydruxProcessor() {
		HxRequestProcessor bean = new HxRequestProcessor();
		bean.setModelAccessoryFactory(systemModelAccessoryFactory());
		bean.setCortexSessionFactory(tfPlatform.systemUserRelated().cortexSessionSupplier());

		return bean;
	}

	private HydruxServlet hydruxServlet() {
		String servicesUrl = servicesUrlWithNoTrailingSlash();
		String webSocketUrl = webSocketUrlNoTrailingSlash(servicesUrl);

		HydruxServlet bean = new HydruxServlet();
		bean.setServicesUrl(servicesUrl);
		bean.setWebSocketUrl(webSocketUrl);
		bean.setHydruxPlatformHtmlTemplate(moduleResources.resource("hydrux-application.html").asFile());
		bean.setModelAccessoryFactory(systemModelAccessoryFactory());

		return bean;
	}

	private String servicesUrlWithNoTrailingSlash() {
		return ensureNoTrailingSlash(tfPlatform.platformReflection().getProperty("TRIBEFIRE_SERVICES_URL"));
	}

	private String webSocketUrlNoTrailingSlash(String servicesUrl) {
		String url = tfPlatform.platformReflection().getProperty("TRIBEFIRE_WEBSOCKET_URL");
		if (!StringTools.isEmpty(url))
			return ensureNoTrailingSlash(url);
		else
			return servicesUrl.replaceFirst("http:", "ws:").replaceFirst("https:", "wss:") + "/websocket";
	}

	private static String ensureNoTrailingSlash(String url) {
		while (url.endsWith("/"))
			url = StringTools.removeSuffixIfEligible(url, "/");
		return url;
	}

	private ModelAccessoryFactory systemModelAccessoryFactory() {
		return tfPlatform.systemUserRelated().modelAccessoryFactory();
	}

}
