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

package org.camunda.bpm.getstarted.pizza.logic;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.getstarted.pizza.dao.ItemDAO;
import org.camunda.bpm.getstarted.pizza.entity.ItemEntity;

@Stateless
@Named
public class ItemBusinessLogic {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	private static Logger LOGGER = Logger.getLogger(ItemBusinessLogic.class.getName());

	public long persistOrder(ItemDAO item) {
		ItemEntity ie = new ItemEntity();
		ie.setName(item.getName());
		ie.setDetails(item.getDetails());
		ie.setDone(false);
		
		entityManager.persist(ie);
		entityManager.flush();
		
		return ie.getId();
	}

	public ItemDAO getItemDAO(long itemId) {
		ItemEntity ie = getItem(itemId);
		ItemDAO dao = new ItemDAO(ie.getName(), ie.getDetails(), ie.isDone());
		return dao;
	}

	public void setDoneState(long itemId, boolean done) {
		ItemEntity ie = getItem(itemId);
		ie.setDone(done);
		entityManager.merge(ie);
	}

	public boolean isItemDone(long itemId) {
		ItemEntity ie = getItem(itemId);
		return ie.isDone();
	}

//	public void rejectOrder(long orderId) {
//		ItemEntity order = getOrder(orderId);
//		LOGGER.log(Level.INFO, "\n\n\nSending Email:\nDear {0}, your order {1} of a {2} pizza has been rejected.\n\n\n", 
//				new String[] {order.getCustomer(), String.valueOf(order.getId()), order.getPizza() });
//	}
	public List<ItemEntity> getAllItem(){
		List<ItemEntity>allItems = entityManager.createQuery("SELECT t FROM ItemEntity t").getResultList();
		return  allItems;
	}
	  
	private ItemEntity getItem(Long itemId) {
		return entityManager.find(ItemEntity.class, itemId);
	}
}
