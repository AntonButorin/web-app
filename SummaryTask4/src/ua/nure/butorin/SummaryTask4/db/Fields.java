package ua.nure.butorin.SummaryTask4.db;

/**
 * Holder for fields names of DB tables.
 * 
 * @author A.Butorin
 * 
 */
public final class Fields {

	// entities
	public static final String ENTITY_ID = "id";

	// Users
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	public static final String USER_BLOCK_STATE = "block";

	// Roles
	public static final String ROLE_NAME = "role_name";

	// Cars
	public static final String CARS_BRAND_ID = "brand_id";
	public static final String CARS_MODEL = "model";
	public static final String CARS_CATEGORY_ID = "category_id";
	public static final String CARS_SEAT_AMOUNT = "seat_amount";
	public static final String CARS_FUEL_ID = "fuel_id";
	public static final String CARS_AIRCONDITION = "air_condition";
	public static final String CARS_AUTOMATIC_TRNSMISSION = "automatic_transmission";
	public static final String CARS_PRICE = "price";
	public static final String CARS_GUARANTEE_AMOUNT = "guarantee_amount";

	// Categories
	public static final String CATEGORY_NAME = "name";

	// Car brands
	public static final String BRAND_NAME = "name";

	// Fuel
	public static final String FUEL_NAME = "name";

	// Orders
	public static final String ORDER_BILL = "bill";
	public static final String ORDER_USER_ID = "user_id";
	public static final String ORDER_CAR_ID = "car_id";
	public static final String ORDER_PASSPORT = "passport";
	public static final String ORDER_DRIVER = "driver";
	public static final String ORDER_PERIOD = "period";
	public static final String ORDER_PRICE = "price";
	public static final String ORDER_AMOUNT = "amount";
	public static final String ORDER_STATUS_ID = "status_id";
	public static final String ORDER_COMPENSATION_SUM = "compensation_sum";
	public static final String ORDER_COMMENTS = "comments";

	// UserOrderBeans
	public static final String USER_ORDER_BEAN_ORDER = "id";
	public static final String USER_ORDER_BEAN_USER_FIRST_NAME = "first_name";
	public static final String USER_ORDER_BEAN_USER_LAST_NAME = "last_name";
	public static final String USER_ORDER_BEAN_CAR_BRAND_ID = "brand_id";
	public static final String USER_ORDER_BEAN_CAR_MODEL = "model";
	public static final String USER_ORDER_BEAN_DRIVER = "driver";
	public static final String USER_ORDER_BEAN_PRICE = "price";
	public static final String USER_ORDER_BEAN_RENT_PERIOD = "period";
	public static final String USER_ORDER_BEAN_ORDER_BILL_ID = "bill";
	public static final String USER_ORDER_BEAN_ORDER_STATUS_NAME = "name";
	public static final String USER_ORDER_BEAN_ORDER_COMMENTS = "comments";
	
	// Bills
	public static final String BILL_OPERATION_NAME = "operation_name";
	public static final String BILL_USER_ID = "user_id";
	public static final String BILL_AMOUNT = "amount";
	public static final String BILL_STATE = "state";
}