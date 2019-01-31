package ua.nure.butorin.SummaryTask4.validators;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butorin.SummaryTask4.exception.Messages;

public class UpdateOrderStatusValidator {

	public List<String> validate(HttpServletRequest request) {
		List<String> errors = new ArrayList<>();

		if (request.getParameter("compensationSum") == null || request.getParameter("compensationSum").isEmpty()) {
			errors.add(Messages.MSG_COMPENSATION_SUM_IN_COMPLAIN_STATUS_CANNOT_BE_EMPTY);
		} else {
			int compensationSum = Integer.parseInt(request.getParameter("compensationSum"));
			if (compensationSum == 0) {
				errors.add(Messages.MSG_COMPENSATION_SUM_IN_COMPLAIN_STATUS_CANNOT_BE_EQUALS_ZERO);
			}
		}
		return errors;
	}
}
