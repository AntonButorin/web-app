package ua.nure.butorin.SummaryTask4.validators;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.db.UserDAO;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.exception.Messages;

import java.util.ArrayList;
import java.util.List;

public class RegistrationValidator {

	private static final Logger LOG = Logger.getLogger(RegistrationValidator.class);

	public List<String> validate(HttpServletRequest request) throws AppException {
		List<String> errors = new ArrayList<>();

		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);

		if (login == null || login.isEmpty()) {
			errors.add(Messages.MSG_LOGIN_CANNOT_BE_EMPTY);
		}

		UserDAO userDAO = new UserDAO();
		User user = userDAO.findUserByLogin(login);
		if (user != null) {
			LOG.trace(login + " is busy");
			errors.add(Messages.MSG_LOGIN_IS_BUSY);
		}

		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add(Messages.MSG_PASSWORD_CANNOT_BE_EMPTY);
		}

		String repeatPassword = request.getParameter("repeatPassword");
		if (repeatPassword == null || repeatPassword.isEmpty()) {
			errors.add(Messages.MSG_PLEASE_REPEAT_PASSWORD);
		} else {
			if (!repeatPassword.equals(password)) {
				errors.add(Messages.MSG_PASSWORD_AND_REPEAT_PASSWORD_NOT_EQUALS);
			}
		}

		String firstName = request.getParameter("firstName");
		if (firstName == null || firstName.isEmpty()) {
			errors.add(Messages.MSG_FIRST_NAME_CANNOT_BE_EMPTY);
		}

		String lastName = request.getParameter("lastName");
		if (lastName == null || lastName.isEmpty()) {
			errors.add(Messages.MSG_LAST_NAME_CANNOT_BE_EMPTY);
		}

		return errors;
	}
}
