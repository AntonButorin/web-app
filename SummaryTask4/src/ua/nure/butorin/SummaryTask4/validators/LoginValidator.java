package ua.nure.butorin.SummaryTask4.validators;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.db.UserDAO;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.helper.ConvertPassword;

public class LoginValidator {

	private static final Logger LOG = Logger.getLogger(RegistrationValidator.class);

	public List<String> validate(HttpServletRequest request) throws AppException {

		List<String> errors = new ArrayList<>();

		String login = request.getParameter("login");
		LOG.trace("Request parameter: loging --> " + login);

		UserDAO userDAO = new UserDAO();
		User user = userDAO.findUserByLogin(login);
		LOG.trace("Found in DB: user --> " + user);

		if (login == null || login.isEmpty()) {
			errors.add(Messages.MSG_LOGIN_CANNOT_BE_EMPTY);
		}

		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add(Messages.MSG_PASSWORD_CANNOT_BE_EMPTY);
		}

		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			try {
				if (user == null || !(new ConvertPassword().convertToMD5(password)).equals(user.getPassword())) {
					errors.add(Messages.MSG_CANNOT_FIND_USER_WITH_SUCH_LOGIN_AND_PASSWORD);
				}
			} catch (NoSuchAlgorithmException ex) {
				LOG.error(Messages.ERR_CANNOT_CONVERT_A_PASSWORD, ex);
				throw new AppException(Messages.ERR_USER_CANNOT_REGISTRATED);
			} catch (UnsupportedEncodingException ex) {
				LOG.error(Messages.ERR_CANNOT_CONVERT_A_PASSWORD, ex);
				throw new AppException(Messages.ERR_USER_CANNOT_REGISTRATED);
			}
			if (user != null && user.isBlock()) {
				errors.add(Messages.MSG_YOUR_ACCOUNT_IS_BLOCKED);
			}
		}
		return errors;
	}
}
