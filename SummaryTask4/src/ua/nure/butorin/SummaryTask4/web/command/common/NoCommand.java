package ua.nure.butorin.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");
		
		request.setAttribute("errorMessage", Messages.ERR_NO_SUCH_COMMAND);
		LOG.error("Set the request attribute: errorMessage --> " + Messages.ERR_NO_SUCH_COMMAND);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}