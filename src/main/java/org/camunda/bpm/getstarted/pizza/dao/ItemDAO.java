/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.getstarted.pizza.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

public class ItemDAO {
	private String name;
	private String details;
	private boolean done;
	
	public ItemDAO() {
		
	}
	
	public ItemDAO(String name, String details,  boolean done) {
		this.name = name;
		this.details = details;
		this.done = done;
	}
	


	  public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getDetails() {
	    return details;
	  }

	  public void setDetails(String details) {
	    this.details = details;
	  }

}
