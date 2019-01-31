package ua.nure.butorin.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author A.Butorin
 * 
 */
public final class Path {

	// admin page
	public static final String PAGE_ADMIN_MENU = "/WEB-INF/jsp/admin/admin_menu.jsp";
	public static final String PAGE_LIST_CLIENTS = "/WEB-INF/jsp/admin/list_clients.jsp";
	public static final String PAGE_LIST_MANAGERS = "/WEB-INF/jsp/admin/list_managers.jsp";
	public static final String PAGE_CAR_SETTINGS = "/WEB-INF/jsp/admin/car_settings.jsp";

	// common page
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/common/settings.jsp";
	public static final String PAGE_CHANGE_LOCALE = "/WEB-INF/jsp/common/changeLocale.jsp";
	public static final String PAGE_LIST_CARS = "/WEB-INF/jsp/admin/list_cars.jsp";

	// client page
	public static final String PAGE_CLIENT_MENU = "/WEB-INF/jsp/client/client_menu.jsp";
	public static final String PAGE_MAKE_ORDER = "/WEB-INF/jsp/client/make_order.jsp";
	public static final String PAGE_CLIENT_ACCOUNT = "/WEB-INF/jsp/client/client_account.jsp";

	// manager page
	public static final String PAGE_MANAGER_MENU = "/WEB-INF/jsp/manager/manager_menu.jsp";
	public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/manager/list_orders.jsp";
	
	// out-of-control page
	public static final String PAGE_HOME = "/WEB-INF/jsp/home.jsp";
	public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
	public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/registration.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	// admin commands
	public static final String COMMAND_VIEW_LIST_CLIENTS = "controller?command=viewListClients";
	public static final String COMMAND_VIEW_LIST_MANAGERS = "controller?command=viewListManagers";
	public static final String COMMAND_VIEW_LIST_CARS = "controller?command=viewListCars";
	public static final String COMMAND_VIEW_CAR_SETTINGS = "controller?command=viewCarSettings";
	public static final String COMMAND_UPDATE_CAR_SETTINGS = "controller?command=updateCarSettings";

	// common commands
	public static final String COMMAND_LOGOUT = "controller?command=logout";

	// client commands
	public static final String COMMAND_LIST_MENU = "controller?command=listMenu";
	public static final String COMMAND_VIEW_MAKE_ORDER = "controller?command=viewMakeOrder";
	public static final String COMMAND_VIEW_MY_ACCOUNT = "controller?command=viewMyAccount";
	public static final String COMMAND_MAKE_ORDER = "controller?command=makeOrder";
	public static final String COMMAND_PAY_BILL = "controller?command=payBill";

	// manager commands
	public static final String COMMAND_VIEW_LIST_ORDERS = "controller?command=viewListOrders";
	public static final String COMMAND_SELECT_ORDERS = "controller?command=selectOrders";
	public static final String COMMAND_UPDATE_ORDER_STATUS = "controller?command=updateOrderStatus";

	// out-of-control commands
	public static final String COMMAND_LOGIN = "controller?command=login";
	public static final String COMMAND_REGISTRATION = "controller?command=registration";
	public static final String COMMAND_SELECT_CAR = "controller?command=selectCar";
	public static final String COMMAND_SORT_CAR = "controller?command=sortCar";
	public static final String COMMAND_VIEW_LOGIN_PAGE = "controller?command=viewLoginPage";
}