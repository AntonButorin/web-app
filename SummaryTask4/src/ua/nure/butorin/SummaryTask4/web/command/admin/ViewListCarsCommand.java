package ua.nure.butorin.SummaryTask4.web.command.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.CarDAO;
import ua.nure.butorin.SummaryTask4.db.Category;
import ua.nure.butorin.SummaryTask4.db.Fuel;
import ua.nure.butorin.SummaryTask4.db.entity.Car;
import ua.nure.butorin.SummaryTask4.db.entity.CarBrand;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewListCarsCommand extends Command {

	private static final long serialVersionUID = 2747590047978756699L;
	private static final Logger LOG = Logger.getLogger(ViewListCarsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Commands starts");

		CarDAO carDAO = new CarDAO();
		HttpSession session = request.getSession(false);

		List<Car> listCars = carDAO.findCars();
		LOG.trace("Found in DB: listCars --> " + listCars);

		request.setAttribute("listCars", listCars);
		LOG.trace("Set the request attribute: listCars --> " + listCars);

		List<CarBrand> brandList = carDAO.findCarBrands();
		LOG.trace("Found in DB: brandList --> " + brandList);
		session.setAttribute("brandList", brandList);
		LOG.trace("Set the session attribute: brandList --> " + brandList);

		Category[] categoryList = Category.values();
		session.setAttribute("categoryList", categoryList);
		LOG.trace("Set the session attribute: categoryList --> " + categoryList);

		Fuel[] fuelList = Fuel.values();
		session.setAttribute("fuelList", fuelList);
		LOG.trace("Set the session attribute: fuelList --> " + fuelList);

		Collections.sort(listCars, new Comparator<Car>() {
			public int compare(Car car1, Car car2) {
				return (int) (car1.getId() - car2.getId());
			}
		});

		LOG.debug("Commands finished");
		return Path.PAGE_LIST_CARS;
	}
}
