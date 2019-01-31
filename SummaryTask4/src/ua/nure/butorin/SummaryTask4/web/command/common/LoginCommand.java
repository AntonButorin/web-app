package ua.nure.butorin.SummaryTask4.web.command.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.Role;
import ua.nure.butorin.SummaryTask4.db.UserDAO;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.validators.LoginValidator;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		
		LOG.debug("Command starts");

		List<String> errors = new LoginValidator().validate(request);
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			return Path.PAGE_LOGIN;
		}

		HttpSession session = request.getSession();

		// obtain login and password from a request
		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findUserByLogin(login);

		Role userRole = Role.getRole(user);
		LOG.trace("userRole --> " + userRole);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.ADMIN) {
			forward = Path.PAGE_ADMIN_MENU;
		}

		if (userRole == Role.MANAGER) {
			forward = Path.PAGE_MANAGER_MENU;
		}

		if (userRole == Role.CLIENT) {
			forward = Path.PAGE_CLIENT_MENU;
		}

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		LOG.debug("Command finished");

		return forward;
	}
}