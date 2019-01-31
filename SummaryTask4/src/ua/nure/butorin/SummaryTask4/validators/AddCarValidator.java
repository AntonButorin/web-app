package ua.nure.butorin.SummaryTask4.validators;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butorin.SummaryTask4.exception.Messages;

public class AddCarValidator {

	public List<String> validate(HttpServletRequest request) {
		List<String> errors = new ArrayList<>();

		String model = request.getParameter("model");
		String seatAmount = request.getParameter("seatAmount");
		String price = request.getParameter("price");
		String guaranteeAmount = request.getParameter("guaranteeAmount");

		if (model == null || model.isEmpty()) {
			errors.add(Messages.MSG_MODEL_CANNOT_BE_EMPTY);
		}

		if (seatAmount == null || seatAmount.isEmpty()) {
			errors.add(Messages.MSG_SEAT_AMOUNT_CANNOT_BE_EMPTY);
		}

		if (price == null || price.isEmpty()) {
			errors.add(Messages.MSG_PRICE_CANNOT_BE_EMPTY);
		}

		if (guaranteeAmount == null || guaranteeAmount.isEmpty()) {
			errors.add(Messages.MSG_GUARANTEE_AMOUNT_CANNOT_BE_EMPTY);
		}
		return errors;
	}
}
