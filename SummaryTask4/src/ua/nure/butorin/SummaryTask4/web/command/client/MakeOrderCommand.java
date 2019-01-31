package ua.nure.butorin.SummaryTask4.web.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.OrderDAO;
import ua.nure.butorin.SummaryTask4.db.Status;
import ua.nure.butorin.SummaryTask4.db.entity.Order;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.validators.MakeOrderValidator;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class MakeOrderCommand extends Command {

	private static final long serialVersionUID = -6575871344324311771L;

	private static final Logger LOG = Logger.getLogger(MakeOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");
		
		List<String> errors = new MakeOrderValidator().validate(request);
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			return Path.COMMAND_VIEW_MAKE_ORDER;
		}
		
		Order order = new Order();
		OrderDAO orderDAO = new OrderDAO();			
		order.setCarId(Long.parseLong(request.getParameter("carId")));
		order.setUserId(Long.parseLong(request.getParameter("userId")));
		order.setPrice(Integer.parseInt(request.getParameter("price")));
		order.setAmount(order.getPeriod()*order.getPrice());		
		order.setStatusId(Status.OPENED.ordinal());
		order.setPassport(request.getParameter("passport"));
		order.setDriver(Boolean.parseBoolean(request.getParameter("driver")));
		order.setPeriod(Integer.parseInt(request.getParameter("period")));

		order = orderDAO.createOrder(order);
		LOG.trace("Create in DB: order --> " + order);

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_MY_ACCOUNT;
	}
}
