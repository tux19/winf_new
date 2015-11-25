///* Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.camunda.bpm.getstarted.pizza;
//
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.ejb.EJB;
//import javax.enterprise.context.ConversationScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
//import org.camunda.bpm.engine.cdi.BusinessProcess;
//import org.camunda.bpm.engine.cdi.jsf.TaskForm;
//import org.camunda.bpm.getstarted.pizza.dao.ItemDAO;
//import org.camunda.bpm.getstarted.pizza.logic.ItemBusinessLogic;
//
//@Named
//@ConversationScoped
//public class ApproveOrderController implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Inject
//	private BusinessProcess businessProcess;
//
//	@Inject
//	private TaskForm taskForm;
//
//	@EJB
//	private ItemBusinessLogic itemBusinessLogic;
//
//	private ItemDAO orderDAO;
//
//	public void loadOrderDAO() {
//		long orderId = (Long) businessProcess.getVariable("orderId");
//		orderDAO = itemBusinessLogic.getOrderDAO(orderId);
//	}
//
//	public ItemDAO getOrderDAO() {
//		if(orderDAO == null)
//			loadOrderDAO();
//		return orderDAO;
//	}
//
//	public void setOrderDAO(ItemDAO orderDAO) {
//		this.orderDAO = orderDAO;
//	}
//
//	public void submitForm() throws IOException {
//		long orderId = (Long) businessProcess.getVariable("orderId");
//		itemBusinessLogic.setApprovalState(orderId, orderDAO.getApproved());
//		
//		try {
//			// Complete user task from
//			taskForm.completeTask();
//		} catch (IOException e) {
//			// Rollback both transactions on error
//			throw new RuntimeException("Cannot complete task", e);
//		}
//	}
//
//}
