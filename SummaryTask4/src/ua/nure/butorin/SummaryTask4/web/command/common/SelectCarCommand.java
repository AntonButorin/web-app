package ua.nure.butorin.SummaryTask4.web.command.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.CarDAO;
import ua.nure.butorin.SummaryTask4.db.entity.Car;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class SelectCarCommand extends Command {

	private static final long serialVersionUID = -7860090801448160571L;
	private static final Logger LOG = Logger.getLogger(SelectCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		LOG.debug("Command starts");

		CarDAO carDAO = new CarDAO();

		if (request.getParameter("carBrand") != null && !request.getParameter("carBrand").isEmpty()) {
			int brandId = Integer.parseInt(request.getParameter("carBrand"));
			
			List<Car> listCars = carDAO.findCarsByBrandId(brandId);
			LOG.trace("Found in DB: listCars --> " + listCars);

			request.setAttribute("listCars", listCars);
			LOG.trace("Set the request attribute: listCars --> " + listCars);
		}

		if (request.getParameter("categoryId") != null && !request.getParameter("categoryId").isEmpty()) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			
			List<Car> listCars = carDAO.findCarsByCategoryId(categoryId);
			LOG.trace("Found in DB: listCars --> " + listCars);

			request.setAttribute("listCars", listCars);
			LOG.trace("Set the request attribute: listCars --> " + listCars);
		}

		LOG.debug("Command finished");

		return Path.PAGE_LIST_CARS;
	}
}
