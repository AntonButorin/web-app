package ua.nure.butorin.SummaryTask4.web.command.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.Path;
import ua.nure.butorin.SummaryTask4.db.UserDAO;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.AppException;
import ua.nure.butorin.SummaryTask4.exception.Messages;
import ua.nure.butorin.SummaryTask4.helper.ConvertPassword;
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = -5568983183303162148L;
	private static final Logger LOG = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		UserDAO userDAO = new UserDAO();
		
		String newFirstName = request.getParameter("firstName");
		String newLastName = request.getParameter("lastName");
		String newPassword = request.getParameter("password");
		
		if (newFirstName != null && !newFirstName.isEmpty()) {
			user.setFirstName(newFirstName);
		}
		if (newLastName != null && !newLastName.isEmpty()) {
			user.setLastName(newLastName);
		}
		if (newPassword != null && !newPassword.isEmpty()) {
			try {
				user.setPassword(new ConvertPassword().convertToMD5(newPassword));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
				LOG.error(Messages.ERR_CANNOT_CONVERT_A_PASSWORD, ex);
				throw new AppException(Messages.ERR_USER_CANNOT_CHANGE_PASSWORD);
			}
		}

		userDAO.updateUser(user);
		LOG.trace("Update in DB: user --> " + user);

		session.setAttribute("user", user);
		LOG.trace("Set the new session attribute: user --> " + user);

		LOG.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}
}
