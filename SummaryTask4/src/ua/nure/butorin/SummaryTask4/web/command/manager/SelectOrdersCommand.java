package ua.nure.butorin.SummaryTask4.web.command.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.OrderDAO;
import ua.nure.butorin.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class SelectOrdersCommand extends Command {

	private static final long serialVersionUID = 8584493849902319459L;

	private static final Logger LOG = Logger.getLogger(SelectOrdersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");

		OrderDAO orderDAO = new OrderDAO();

		if (request.getParameter("statusId") != null && !request.getParameter("statusId").isEmpty()) {
			
			int statusId = Integer.parseInt(request.getParameter("statusId"));
			
			List<UserOrderBean> userOrderBeanList = orderDAO.findUserOrderBeans(statusId);
			LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

			request.setAttribute("userOrderBeanList", userOrderBeanList);
			LOG.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);
		}

		LOG.debug("Command finished");
		return Path.PAGE_LIST_ORDERS;
	}
}
