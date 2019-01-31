package ua.nure.butorin.SummaryTask4.web.command.admin;

import java.util.Collections;
import java.util.Comparator;
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
import ua.nure.butorin.SummaryTask4.web.command.Command;

public class ViewListClientsCommand extends Command {

	private static final long serialVersionUID = -5308330792644991136L;

	private static final Logger LOG = Logger.getLogger(ViewListClientsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Commands starts");

		UserDAO userDAO = new UserDAO();
		List<User> listClients = userDAO.findUsersByRoleId(Role.CLIENT.ordinal());
		LOG.trace("Found in DB: listClients --> " + listClients);		

		Collections.sort(listClients, new Comparator<User>() {
			public int compare(User id1, User id2) {
				return (int) (id1.getId() - id2.getId());
			}
		});

		// put list clients to session
		HttpSession session = request.getSession(false);
		session.setAttribute("listClients", listClients);
		LOG.trace("Set the session attribute: listClients --> " + listClients);

		LOG.debug("Commands finished");
		return Path.PAGE_LIST_CLIENTS;
	}
}
