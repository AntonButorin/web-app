package ua.nure.butorin.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewCarSettingsCommand extends Command {

	private static final long serialVersionUID = -4053424168960267750L;
	private static final Logger LOG = Logger.getLogger(ViewCarSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");

		String actionMessage = request.getParameter("actionMessage");
		LOG.trace("Get Action message from request: actionMessage --> " + actionMessage);
		request.setAttribute("actionMessage", actionMessage);

		if (request.getParameter("carId") != null && !request.getParameter("carId").isEmpty()) {
			Long carId = Long.parseLong(request.getParameter("carId"));
			LOG.trace("Get Id from request: carId --> " + carId);

			request.setAttribute("carId", carId);
		}

		LOG.debug("Command finished");
		return Path.PAGE_CAR_SETTINGS;
	}

}
