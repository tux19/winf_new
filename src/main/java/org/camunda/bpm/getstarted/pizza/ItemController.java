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
package org.camunda.bpm.getstarted.pizza;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.getstarted.pizza.dao.ItemDAO;
import org.camunda.bpm.getstarted.pizza.entity.ItemEntity;
import org.camunda.bpm.getstarted.pizza.logic.ItemBusinessLogic;

@Named
@ConversationScoped
public class ItemController implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	ItemBusinessLogic itemBusinessLogic;

	// Inject task form available through the camunda cdi artifact
	@Inject
	private TaskForm taskForm;

	// the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	private ItemDAO itemDAO = new ItemDAO();
//	
//	public void setItemId(long itemId){
//		this.itemId = itemId;
//	}
//	public long getItemId(){
//		return itemId;
//	}
	public void submitForm()  {
		long newItemID = itemBusinessLogic.persistOrder(itemDAO);
		businessProcess.setVariable("itemId", newItemID);
		

	}
	
	public void endForm() {
		try {
			// Complete user task from
			taskForm.completeProcessInstanceForm();
			
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	  public List<ItemEntity> getAllItems(){
		  List<ItemEntity> allItems = itemBusinessLogic.getAllItem();
		  return allItems;
	  }
	public void finishTask(long itemId){
		//String itemId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemId");
		itemBusinessLogic.setDone(itemId, true);
	}
}
