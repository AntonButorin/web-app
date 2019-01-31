package ua.nure.butorin.SummaryTask4.web.command.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.CarDAO;
import ua.nure.butorin.SummaryTask4.db.OrderDAO;
import ua.nure.butorin.SummaryTask4.db.Status;
import ua.nure.butorin.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.butorin.SummaryTask4.db.entity.CarBrand;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewListOrdersCommand extends Command {

	private static final long serialVersionUID = -2673028809222582754L;

	private static final Logger LOG = Logger.getLogger(ViewListOrdersCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession(false);

		OrderDAO orderDAO = new OrderDAO();
		List<UserOrderBean> userOrderBeanList = orderDAO.findUserOrderBeans();
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);
		
		// put user order beans list to request
		request.setAttribute("userOrderBeanList", userOrderBeanList);		
		LOG.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);
		
		CarDAO carDAO = new CarDAO();
		List<CarBrand> brandList = carDAO.findCarBrands();
		session.setAttribute("brandList", brandList);
		LOG.trace("Set the session attribute: brandList --> " + brandList);
		
		Status[] statusList = Status.values();
		session.setAttribute("statusList", statusList);
		LOG.trace("Set the session attribute: statusList --> " + statusList);

		LOG.debug("Command finished");
		return Path.PAGE_LIST_ORDERS;
	}
}
