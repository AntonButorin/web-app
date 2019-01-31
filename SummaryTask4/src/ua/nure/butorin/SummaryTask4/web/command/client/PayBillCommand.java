package ua.nure.butorin.SummaryTask4.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.OrderDAO;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class PayBillCommand extends Command {
	private static final long serialVersionUID = 6472957497372130226L;
	private static final Logger LOG = Logger.getLogger(PayBillCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.debug("Commands starts");

		OrderDAO orderDAO = new OrderDAO();
		long billId = Long.parseLong(request.getParameter("billId"));
		int orderStatus = Integer.parseInt(request.getParameter("statusId"));
		boolean billState = false;
		LOG.trace("billId--> " + billId + " orderStatus " + orderStatus);

		orderDAO.updateOrderStatusByBill(orderStatus, billId);
		LOG.trace("Update Status in DB: status--> " + orderStatus);

		orderDAO.updateBillState(billState, billId);
		LOG.trace("Update in DB: bill--> " + billId + " on state: false");

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_MY_ACCOUNT;
	}
}
