package ua.nure.butorin.SummaryTask4.exception;

public class Messages {

	private Messages() {
		// no op
	}

	// Application errors
	public static final String ERR_NO_SUCH_COMMAND = "No such command";

	// Manipulate Users errors
	public static final String ERR_USER_CANNOT_REGISTRATED = "Cannot registration";
	public static final String ERR_CANNOT_CREATE_USER = "Cannot create a user";
	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";
	public static final String ERR_CANNOT_UPDATE_STATE_USER = "Cannot update a user state";
	public static final String ERR_USER_CANNOT_CHANGE_PASSWORD = "Cannot change the password";
	public static final String ERR_CANNOT_OBTAIN_USERS_BY_ROLE_ID = "Cannot obtain users by its role id";
	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
	public static final String ERR_CANNOT_CHANGE_LOCALE = "Cannot change locale";
	public static final String ERR_NOT_ACCESS_TO_RESOURCE = "You do not have permission to access the requested resource";
	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";
	public static final String ERR_CANNOT_CONVERT_A_PASSWORD = "Cannot convert a password";

	// User message
	public static final String MSG_LOGIN_PASSWORD_CANNOT_BE_EMPTY = "Login/password cannot be empty";
	public static final String MSG_CANNOT_FIND_USER_WITH_SUCH_LOGIN_AND_PASSWORD = "Cannot find user with such login/password";
	public static final String MSG_YOUR_ACCOUNT_IS_BLOCKED = "Your account is blocked";

	// Validate message
	public static final String MSG_LOGIN_CANNOT_BE_EMPTY = "Login cannot be empty";
	public static final String MSG_LOGIN_IS_BUSY = "User with such login exist";
	public static final String MSG_PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty";
	public static final String MSG_FIRST_NAME_CANNOT_BE_EMPTY = "First name cannot be empty";
	public static final String MSG_LAST_NAME_CANNOT_BE_EMPTY = "Last name cannot be empty";
	public static final String MSG_MODEL_CANNOT_BE_EMPTY = "Model cannot be empty";
	public static final String MSG_SEAT_AMOUNT_CANNOT_BE_EMPTY = "Seat amount name cannot be empty";
	public static final String MSG_PRICE_CANNOT_BE_EMPTY = "Price cannot be empty";
	public static final String MSG_GUARANTEE_AMOUNT_CANNOT_BE_EMPTY = "Guarantee amount name cannot be empty";
	public static final String MSG_PLEASE_REPEAT_PASSWORD = "Please, repeat password";
	public static final String MSG_PASSWORD_AND_REPEAT_PASSWORD_NOT_EQUALS = "Password and repeat password not equals";
	public static final String MSG_COMPENSATION_SUM_IN_COMPLAIN_STATUS_CANNOT_BE_EMPTY = "Compensation sum in complain order cannot be empty";
	public static final String MSG_COMPENSATION_SUM_IN_COMPLAIN_STATUS_CANNOT_BE_EQUALS_ZERO = "Compensation sum in complain order cannot be equls zero";
	public static final String MSG_PASSPORT_CANNOT_BE_EMPTY = "Passport cannot be empty";
	public static final String MSG_PERIOD_CANNOT_BE_EMPTY = "Rent period cannot be empty";
	public static final String MSG_COMMENTS_CANNOT_BE_EMPTY = "Comments to canceled order can not be empty";
	
	
	// Manipulate Data base errors
	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
	public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";

	// Manipulate Cars errors
	public static final String ERR_CANNOT_CREATE_CAR = "Cannot create a car";
	public static final String ERR_CANNOT_UPDATE_CAR = "Cannot update a car";
	public static final String ERR_CANNOT_DELETE_CAR = "Cannot delete a car";
	public static final String ERR_CANNOT_OBTAIN_CAR_BY_ID = "Cannot obtain a car by its id";
	public static final String ERR_CANNOT_UPDATE_STATE_CAR = "Cannot update a car state";
	public static final String ERR_CANNOT_OBTAIN_CAR_BY_CATEGORY_ID = "Cannot obtain a car by its category id";
	public static final String ERR_CANNOT_OBTAIN_CAR_BY_BRAND_ID = "Cannot obtain a car by its brand id";
	public static final String ERR_CANNOT_OBTAIN_CARS = "Cannot obtain all cars";
	public static final String ERR_CANNOT_OBTAIN_FUEL = "Cannot obtain all fuel";
	public static final String ERR_CANNOT_DELETE_CAR_BY_ID = "Cannot delete a car by its id";
	public static final String ERR_CANNOT_OBTAIN_CARS_BY_STATE = "Cannot obtain cars by state";
	public static final String ERR_CANNOT_UPDATE_SETTINGS = "Cannot update settings";
	public static final String ERR_CANNOT_OBTAIN_CATEGORIES = "Cannot obtain categories";
	public static final String ERR_CANNOT_OBTAIN_CAR_BRANDS = "Cannot obtain car brands";

	// Manipulate Orders errors
	public static final String ERR_CANNOT_OBTAIN_ORDERS = "Cannot obtain orders";
	public static final String ERR_CANNOT_CREATE_ORDER = "Cannot create orders";
	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID = "Cannot obtain orders by status id";
	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_STATUS_ID_AND_USER = "Cannot obtain orders by status id and user";
	public static final String ERR_CANNOT_OBTAIN_ORDER_ID = "Cannot obtain order by id";
	public static final String ERR_CANNOT_OBTAIN_ORDERS_BY_USER_ID = "Cannot obtain orders by user id";
	public static final String ERR_CANNOT_UPDATE_ORDER_STATUS = "Cannot update order status";
	public static final String ERR_CANNOT_CREATE_BILL = "Cannot create a bill";
	public static final String ERR_CANNOT_OBTAIN_BILL_BY_USER = "Cannot obtain bill by user";
	public static final String ERR_CANNOT_UPDATE_BILL_STATE = "Cannot update bill state";
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS = "Cannot obtain user order beans";
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS_BY_STATUS_ID = "Cannot obtain user order beans by status id";
	public static final String ERR_CANNOT_OBTAIN_USER_ORDER_BEANS_BY_USER_ID = "Cannot obtain user order beans by user id";
}
