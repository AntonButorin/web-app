package ua.nure.butorin.SummaryTask4.web.command.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.OrderDAO;
import ua.nure.butorin.SummaryTask4.db.Status;
import ua.nure.butorin.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.butorin.SummaryTask4.db.entity.Bill;
import ua.nure.butorin.SummaryTask4.db.entity.Order;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.validators.UpdateOrderStatusValidator;
import ua.nure.butorin.SummaryTask4.web.command.Command;
import ua.nure.butorin.SummaryTask4.web.command.admin.UpdateCarSettingsCommand;

public class UpdateOrderStatusCommand extends Command {

	private static final long serialVersionUID = 4198929117115562026L;

	private static final Logger LOG = Logger.getLogger(UpdateCarSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug("Commands starts");

		Bill bill = new Bill();
		OrderDAO orderDAO = new OrderDAO();

		int orderStatus = Integer.parseInt(request.getParameter("statusId"));
		String orderStatusName = Status.values()[orderStatus].getName();
		int orderId = Integer.parseInt(request.getParameter("id"));

		Order order = orderDAO.findOrder(orderId);
		LOG.trace("Find in DB: order--> " + order);

		switch (orderStatusName) {
		case "confirmed":
			bill.setOperationName("order");
			bill.setUserId(order.getUserId());
			bill.setAmount(order.getAmount());
			bill.setState(true);
			orderDAO.createBill(bill);
			LOG.trace("Create in DB: bill--> " + bill + " for orderId--> " + orderId);

			orderDAO.updateOrderStatus(orderStatus, orderId, bill.getId());
			LOG.trace("Update in DB: orderId--> " + orderId + " on status--> " + Status.values()[orderStatus]
					+ " and bill= " + bill);
			break;

		case "canceled":
			String comments = request.getParameter("comments");
			
			if (comments == null || comments.isEmpty()) {
				request.setAttribute("errors", Messages.MSG_COMMENTS_CANNOT_BE_EMPTY);
				return Path.COMMAND_VIEW_LIST_ORDERS;
			}
			
			orderDAO.updateOrder(orderStatus, orderId, comments);
			LOG.trace("Update in DB: orderId--> " + orderId + " on status--> " + Status.values()[orderStatus]);
			break;

		case "complained":
			List<String> errors = new UpdateOrderStatusValidator().validate(request);

			if (errors.size() != 0) {
				request.setAttribute("errors", errors);
				return Path.COMMAND_VIEW_LIST_ORDERS;
			}

			int compensationSum = Integer.parseInt(request.getParameter("compensationSum"));
			bill.setOperationName("complaint");
			bill.setUserId(order.getUserId());
			bill.setAmount(compensationSum);
			bill.setState(true);

			orderDAO.createBill(bill);
			LOG.trace("Create in DB: bill--> " + bill + " for orderId--> " + orderId);

			orderDAO.updateOrderStatus(orderStatus, orderId, bill.getId(), compensationSum);
			LOG.trace("Update in DB: orderId--> " + orderId + " on status--> " + Status.values()[orderStatus]
					+ " and bill= " + bill);
			break;

		case "closed":
			orderDAO.updateOrderStatus(orderStatus, orderId);
			LOG.trace("Update in DB: orderId--> " + orderId + " on status--> " + Status.values()[orderStatus]);
			break;
		}

		List<UserOrderBean> userOrderBeanList = orderDAO.findUserOrderBeans();
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

		request.setAttribute("userOrderBeanList", userOrderBeanList);
		LOG.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_LIST_ORDERS;
	}
}
