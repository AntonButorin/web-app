package ua.nure.butorin.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewRegistration extends Command {

	private static final long serialVersionUID = 8125586453450024821L;
	private static final Logger LOG = Logger.getLogger(ViewRegistration.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		LOG.debug("Command starts");

		LOG.debug("Command finished");
		return Path.PAGE_REGISTRATION;
	}
}
