package ua.nure.butorin.SummaryTask4.web.command.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.helper.ConvertPassword;
import ua.nure.butorin.SummaryTask4.validators.RegistrationValidator;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class RegistrationCommand extends Command {

	private static final long serialVersionUID = -6518353400073732217L;

	private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

		LOG.debug("Command starts");
		List<String> errors = new RegistrationValidator().validate(request);
		if (errors.size() != 0) {
			setValidateParameters(request);
			request.setAttribute("errors", errors);
			return Path.PAGE_REGISTRATION;
		}

		User user = new User();
		UserDAO userDAO = new UserDAO();
		user.setLogin(request.getParameter("login"));

		try {
			user.setPassword(new ConvertPassword().convertToMD5(request.getParameter("password")));
		} catch (NoSuchAlgorithmException ex) {
			LOG.error(Messages.ERR_CANNOT_CONVERT_A_PASSWORD, ex);
			throw new AppException(Messages.ERR_USER_CANNOT_REGISTRATED);
		} catch (UnsupportedEncodingException ex) {
			LOG.error(Messages.ERR_CANNOT_CONVERT_A_PASSWORD, ex);
			throw new AppException(Messages.ERR_USER_CANNOT_REGISTRATED);
		}

		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setBlock(false);
		int roleId = Role.CLIENT.ordinal();
		String forward = Path.PAGE_CLIENT_MENU;

		// insert new user by admin
		if (Role.ADMIN == request.getSession(false).getAttribute("userRole")) {
			LOG.trace("session attribute: useRole --> " + request.getSession(false).getAttribute("userRole"));

			if (Role.MANAGER.getName().equalsIgnoreCase(request.getParameter("role"))) {
				roleId = Role.MANAGER.ordinal();
				forward = Path.COMMAND_VIEW_LIST_MANAGERS;

			} else {
				forward = Path.COMMAND_VIEW_LIST_CLIENTS;
			}
			user.setRoleId(roleId);
			userDAO.createUser(user);

			// default create new user
		} else {
			user.setRoleId(roleId);
			userDAO.createUser(user);

			HttpSession session = request.getSession();

			session.setAttribute("user", user);
			LOG.trace("Set the session attribute: user --> " + user);

			session.setAttribute("userRole", Role.CLIENT);
			LOG.trace("Set the session attribute: userRole --> " + Role.CLIENT.getName());
		}

		LOG.info(user + " logged as " + Role.CLIENT.getName());

		LOG.debug("Command finished");
		return forward;
	}

	private HttpServletRequest setValidateParameters(HttpServletRequest request) {
		request.setAttribute("login", request.getParameter("login"));
		LOG.trace("Set the request attribute: login --> " + request.getParameter("login"));
		
		request.setAttribute("firstName", request.getParameter("firstName"));
		LOG.trace("Set the request attribute: firstName --> " + request.getParameter("firstName"));
		
		request.setAttribute("lastName", request.getParameter("lastName"));
		LOG.trace("Set the request attribute: lastName --> " + request.getParameter("lastName"));
		return request;
	}
}
