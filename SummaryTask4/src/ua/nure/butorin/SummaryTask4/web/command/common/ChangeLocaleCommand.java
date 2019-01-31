package ua.nure.butorin.SummaryTask4.web.command.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ChangeLocaleCommand extends Command {

	private static final long serialVersionUID = -2902301978901706471L;
	private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Command starts");

		LOG.debug("Command finished");		
		return Path.PAGE_CHANGE_LOCALE;
	}
}
