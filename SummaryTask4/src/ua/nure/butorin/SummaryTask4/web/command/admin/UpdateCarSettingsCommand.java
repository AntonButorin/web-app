package ua.nure.butorin.SummaryTask4.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.CarDAO;
import ua.nure.butorin.SummaryTask4.db.entity.Car;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.validators.AddCarValidator;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class UpdateCarSettingsCommand extends Command {

	private static final long serialVersionUID = -5769967739579274742L;

	private static final Logger LOG = Logger.getLogger(UpdateCarSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug("Commands starts");

		CarDAO carDAO = new CarDAO();
		Car car = new Car();

		Long id = null;
		if (request.getParameter("message") == null || request.getParameter("message").isEmpty()) {
			throw new AppException(Messages.ERR_CANNOT_UPDATE_SETTINGS);
		}

		String message = request.getParameter("message");
		LOG.trace("Message: --> " + message);

		if (request.getParameter("carId") != null) {
			id = Long.parseLong(request.getParameter("carId"));
		}

		switch (message) {

		case "create":
			List<String> errors = new AddCarValidator().validate(request);
			if (errors.size() != 0) {
				request.setAttribute("errors", errors);
				request.setAttribute("actionMessage", "create");
				return Path.PAGE_CAR_SETTINGS;
			}
			
			car.setBrandId(Integer.parseInt(request.getParameter("carBrand")));
			car.setModel(request.getParameter("model"));
			car.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			car.setSeatAmount(Integer.parseInt(request.getParameter("seatAmount")));
			car.setFuelId(Integer.parseInt(request.getParameter("fuelId")));
			car.setAirCondition(Boolean.parseBoolean(request.getParameter("airCondition")));
			car.setAutomaticTransmission(Boolean.parseBoolean(request.getParameter("automaticTransmission")));
			car.setPrice(Integer.parseInt(request.getParameter("price")));
			car.setGuaranteeAmount(Integer.parseInt(request.getParameter("guaranteeAmount")));

			carDAO.createCar(car);
			LOG.trace("Create in DB: car --> " + car);
			break;

		case "update":
			car = carDAO.findCarById(id);
			LOG.trace("Get car from DB: car --> " + car + "  by id--> " + id);

			String newPrice = request.getParameter("price");
			String newGuaranteeAmount = request.getParameter("guaranteeAmount");

			if (newPrice != null && !newPrice.isEmpty()) {
				car.setPrice(Integer.parseInt(newPrice));
			}
			if (newGuaranteeAmount != null && !newGuaranteeAmount.isEmpty()) {
				car.setGuaranteeAmount(Integer.parseInt(newGuaranteeAmount));
			}

			carDAO.updateCar(car);
			LOG.trace("Update in DB: car --> " + car);
			break;

		case "delete":
			carDAO.deleteCarById(id);
			LOG.trace("Delete car from DB: car --> " + car + "  by id--> " + id);
			break;
		}

		LOG.debug("Command finished");
		return Path.COMMAND_VIEW_LIST_CARS;
	}
}
