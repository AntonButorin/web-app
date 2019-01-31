package ua.nure.butorin.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.Role;
import ua.nure.butorin.SummaryTask4.db.UserDAO;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ChangeUserStateCommand extends Command {

	private static final long serialVersionUID = -543871492351819334L;
	private static final Logger LOG = Logger.getLogger(ChangeUserStateCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Commands starts");

		long userId = Long.parseLong(request.getParameter("id"));
		long roleId = Long.parseLong(request.getParameter("roleId"));
		boolean block = Boolean.parseBoolean(request.getParameter("block"));

		UserDAO userDAO = new UserDAO();

		userDAO.updateUserState(block, userId);
		LOG.trace("Changing state user with id --> " + userId + " to--> " + block);

		String forward = Path.PAGE_ERROR_PAGE;

		if (roleId == Role.MANAGER.ordinal()) {
			forward = Path.COMMAND_VIEW_LIST_MANAGERS;
		}

		if (roleId == Role.CLIENT.ordinal()) {
			forward = Path.COMMAND_VIEW_LIST_CLIENTS;
		}

		LOG.debug("Commands finished");
		return forward;
	}
}
