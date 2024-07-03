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
package tribefire.extension.hydrux.model.deployment.domain;

import com.braintribe.model.generic.annotation.Abstract;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.hydrux.model.deployment.HxComponent;
import tribefire.extension.hydrux.model.deployment.HxScope;

/**
 * Denotes a tribefire service-domain, represented by a supplier, which can be used to resolve a service domain's externalId.
 * <p>
 * For example you can specify the value directly use {@link HxStaticDomainSupplier}, or you can specify the domain id should be read from the URL
 * with {@link HxUrlDomainSupplier}.
 *
 * @see HxScope#getDefaultDomain()
 * 
 * @author peter.gazdik
 */
@Abstract
public interface HxDomainSupplier extends HxComponent {

	EntityType<HxDomainSupplier> T = EntityTypes.T(HxDomainSupplier.class);

}
