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
package tribefire.extension.hydrux.model.deployment.session;

import com.braintribe.model.generic.annotation.Abstract;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import tribefire.extension.hydrux.model.deployment.HxComponent;
import tribefire.extension.hydrux.model.deployment.domain.HxDomainSupplier;

/**
 * Denotation type for a sessionFactory (in javascript expressed as "() => PersistenceGmSession"), a function which delivers a new session instance on
 * each invocation.
 * 
 * @see HxSession
 * 
 * @author peter.gazdik
 */
@Abstract
public interface HxSessionFactory extends HxComponent {

	EntityType<HxSessionFactory> T = EntityTypes.T(HxSessionFactory.class);

	HxDomainSupplier getDomain();
	void setDomain(HxDomainSupplier domain);

}
