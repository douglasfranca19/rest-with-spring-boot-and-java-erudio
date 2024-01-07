package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationExceptions;

@RestController
public class MathController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return convertDouble(numberOne) + convertDouble(numberTwO);
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double sub(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return convertDouble(numberOne) - convertDouble(numberTwO);
	}
	
	@RequestMapping(value = "/mul/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double mul(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return convertDouble(numberOne) * convertDouble(numberTwO);
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return convertDouble(numberOne) / convertDouble(numberTwO);
	}

	private Double convertDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number))
			return Double.parseDouble(number);
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
