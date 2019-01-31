package ua.nure.butorin.SummaryTask4.web.command.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.CarDAO;
import ua.nure.butorin.SummaryTask4.db.entity.Car;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewMakeOrderCommand extends Command {

	private static final long serialVersionUID = 7755523492487373583L;
	private static final Logger LOG = Logger.getLogger(ViewMakeOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");
		
		CarDAO carDAO = new CarDAO();
		
		if (request.getParameter("carId") != null) {
			Long id = Long.parseLong(request.getParameter("carId"));
			
			Car car = carDAO.findCarById(id);
			LOG.trace("Found in DB: car --> " + car);

			request.setAttribute("car", car);
			LOG.trace("Set the request attribute: car --> " + car);
		}

		LOG.debug("Command finished");
		return Path.PAGE_MAKE_ORDER;
	}
}
