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
package tribefire.extension.hydrux.model.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.braintribe.model.generic.GenericEntity;
import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * @author peter.gazdik
 */
public interface HxJoat extends GenericEntity {

	EntityType<HxJoat> T = EntityTypes.T(HxJoat.class);

	Object getObject();
	void setObject(Object object);

	// ################################################
	// ## . . . . . . . . Simple . . . . . . . . . ##
	// ################################################

	Integer getInteger();
	void setInteger(Integer integer);

	Long getLong();
	void setLong(Long _long);

	Float getFloat();
	void setFloat(Float _float);

	Double getDouble();
	void setDouble(Double _double);

	Boolean getBoolean();
	void setBoolean(Boolean _boolean);

	String getString();
	void setString(String string);

	Date getDate();
	void setDate(Date date);

	BigDecimal getDecimal();
	void setDecimal(BigDecimal decimal);

	// ################################################
	// ## . . . . . . . . . Custom . . . . . . . . . ##
	// ################################################

	HxColorEnum getColor();
	void setColor(HxColorEnum color);

	HxJoat getEntity();
	void setEntity(HxJoat entity);

	// ################################################
	// ## . . . . . . Simple Collections . . . . . . ##
	// ################################################

	List<String> getListOfStrings();
	void setListOfStrings(List<String> listOfStrings);

	Set<String> getSetOfStrings();
	void setSetOfStrings(Set<String> setOfStrings);

	Map<String, String> getMapOfStrings();
	void setMapOfStrings(Map<String, String> mapOfStrings);

	// ################################################
	// ## . . . . . . Entity Collections . . . . . . ##
	// ################################################

	List<HxJoat> getListOfEntities();
	void setListOfEntities(List<HxJoat> listOfEntities);

	Set<HxJoat> getSetOfEntities();
	void setSetOfEntities(Set<HxJoat> setOfEntities);

	Map<String, HxJoat> getMapOfEntities();
	void setMapOfEntities(Map<String, HxJoat> mapOfEntities);

}
