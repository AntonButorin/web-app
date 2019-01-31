package ua.nure.butorin.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.web.command.admin.ChangeUserStateCommand;
import ua.nure.butorin.SummaryTask4.web.command.admin.UpdateCarSettingsCommand;
import ua.nure.butorin.SummaryTask4.web.command.admin.ViewCarSettingsCommand;
import ua.nure.butorin.SummaryTask4.web.command.admin.ViewListCarsCommand;
import ua.nure.butorin.SummaryTask4.web.command.admin.ViewListClientsCommand;
import ua.nure.butorin.SummaryTask4.web.command.admin.ViewListManagersCommand;
import ua.nure.butorin.SummaryTask4.web.command.client.MakeOrderCommand;
import ua.nure.butorin.SummaryTask4.web.command.client.PayBillCommand;
import ua.nure.butorin.SummaryTask4.web.command.client.ViewMakeOrderCommand;
import ua.nure.butorin.SummaryTask4.web.command.client.ViewMyAccountCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.ChangeLocaleCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.LoginCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.LogoutCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.NoCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.RegistrationCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.SelectCarCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.SortCarCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.UpdateSettingsCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.ViewLoginCommand;
import ua.nure.butorin.SummaryTask4.web.command.common.ViewRegistration;
import ua.nure.butorin.SummaryTask4.web.command.common.ViewSettingsCommand;
import ua.nure.butorin.SummaryTask4.web.command.manager.SelectOrdersCommand;
import ua.nure.butorin.SummaryTask4.web.command.manager.UpdateOrderStatusCommand;
import ua.nure.butorin.SummaryTask4.web.command.manager.ViewListOrdersCommand;

public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// out-of-control commands
		commands.put("login", new LoginCommand());
		commands.put("viewLogin", new ViewLoginCommand());
		commands.put("viewRegistration", new ViewRegistration());
		commands.put("registration", new RegistrationCommand());
		commands.put("sortCar", new SortCarCommand());
		commands.put("selectCar", new SelectCarCommand());

		// common commands
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("changeLocale", new ChangeLocaleCommand());

		// client commands
		commands.put("viewMakeOrder", new ViewMakeOrderCommand());
		commands.put("viewMyAccount", new ViewMyAccountCommand());
		commands.put("makeOrder", new MakeOrderCommand());
		commands.put("payBill", new PayBillCommand());

		// manager commands
		commands.put("viewListOrders", new ViewListOrdersCommand());
		commands.put("selectOrders", new SelectOrdersCommand());
		commands.put("updateOrderStatus", new UpdateOrderStatusCommand());		

		// admin commands
		commands.put("viewListClients", new ViewListClientsCommand());
		commands.put("changeUserState", new ChangeUserStateCommand());
		commands.put("viewListManagers", new ViewListManagersCommand());
		commands.put("viewListCars", new ViewListCarsCommand());
		commands.put("updateCarSettings", new UpdateCarSettingsCommand());
		commands.put("viewCarSettings", new ViewCarSettingsCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

}