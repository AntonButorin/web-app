package ua.nure.butorin.SummaryTask4.validators;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.butorin.SummaryTask4.exception.Messages;

public class MakeOrderValidator {
	
	public List<String> validate(HttpServletRequest request) {
		List<String> errors = new ArrayList<>();

		if (request.getParameter("passport") == null || request.getParameter("passport").isEmpty()) {
			errors.add(Messages.MSG_PASSPORT_CANNOT_BE_EMPTY);
		}
		
		if (request.getParameter("period") == null || request.getParameter("period").isEmpty()) {
			errors.add(Messages.MSG_PERIOD_CANNOT_BE_EMPTY);
		}
		return errors;
	}
}
