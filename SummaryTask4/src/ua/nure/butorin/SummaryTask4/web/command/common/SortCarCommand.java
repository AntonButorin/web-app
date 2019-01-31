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

public class SortCarCommand extends Command {

	private static final long serialVersionUID = -7860090801448160571L;
	private static final Logger LOG = Logger.getLogger(SelectCarCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws AppException {

		LOG.debug("Commands starts");

		CarDAO carDAO = new CarDAO();
		List<Car> listCars = carDAO.findCars();

		// sort list cars by price
		if (request.getParameter("sortBy") != null && !request.getParameter("sortBy").isEmpty()) {
			if (request.getParameter("sortBy").equals("price")) {
				listCars.sort((car1, car2) -> {
					return car1.getPrice().compareTo(car2.getPrice());
				});

				request.setAttribute("listCars", listCars);
				LOG.trace("Set the request attribute: listCars --> " + listCars);
			}

			// sort list cars by model
			if (request.getParameter("sortBy").equals("model")) {
				listCars.sort((car1, car2) -> {
					return car1.getModel().compareTo(car2.getModel());
				});
				request.setAttribute("listCars", listCars);
				LOG.trace("Set the request attribute: listCars --> " + listCars);
			}
		}
		LOG.debug("Commands finished");
		return Path.PAGE_LIST_CARS;
	}
}
