package ua.nure.butorin.SummaryTask4.web.command.client;

import java.util.Collections;
import java.util.Comparator;
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
import ua.nure.butorin.SummaryTask4.db.entity.Bill;
import ua.nure.butorin.SummaryTask4.db.entity.CarBrand;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewMyAccountCommand extends Command {

	private static final long serialVersionUID = 6277306270527260540L;

	private static final Logger LOG = Logger.getLogger(ViewMyAccountCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession(false);

		OrderDAO orderDAO = new OrderDAO();
		User user = (User) session.getAttribute("user");
	
		List<UserOrderBean> userOrderBeanList = orderDAO.findUserOrderBeansByUserId(user.getId());
		LOG.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

		Collections.sort(userOrderBeanList, new Comparator<UserOrderBean>() {
			public int compare(UserOrderBean order1, UserOrderBean order2) {
				return (int) (order1.getId() - order2.getId());
			}
		});
		
		List<Bill> billList = orderDAO.findBills(user);
		request.setAttribute("billList", billList);
		LOG.trace("Set the session attribute: billList --> " + billList);

		session.setAttribute("userOrderBeanList", userOrderBeanList);
		LOG.trace("Set the session attribute: userOrderBeanList --> " + userOrderBeanList);

		Status[] statusList = Status.values();
		session.setAttribute("statusList", statusList);
		LOG.trace("Set the session attribute: statusList --> " + statusList);
	
		CarDAO carDAO = new CarDAO();
		List<CarBrand> brandList = carDAO .findCarBrands();
		LOG.trace("Found in DB: brandList --> " + brandList);
		
		session.setAttribute("brandList", brandList);
		LOG.trace("Set the session attribute: brandList --> " + brandList);

		LOG.debug("Command finished");
		return Path.PAGE_CLIENT_ACCOUNT;
	}
}
