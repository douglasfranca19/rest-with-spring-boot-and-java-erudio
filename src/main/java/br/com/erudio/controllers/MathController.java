package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converter.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationExceptions;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {

	private final AtomicLong counter = new AtomicLong();

	private SimpleMath math = new SimpleMath();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return math.sum(NumberConverter.convertDouble(numberOne), NumberConverter.convertDouble(numberTwO));
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double sub(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return math.sub(NumberConverter.convertDouble(numberOne), NumberConverter.convertDouble(numberTwO));
	}

	@RequestMapping(value = "/mul/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double mul(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return math.mul(NumberConverter.convertDouble(numberOne), NumberConverter.convertDouble(numberTwO));
	}

	@RequestMapping(value = "/div/{numberOne}/{numberTwO}", method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwO") String numberTwO) throws Exception {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwO)) {
			throw new UnsupportedMathOperationExceptions("Please set a numeric value");
		}

		return math.div(NumberConverter.convertDouble(numberOne), NumberConverter.convertDouble(numberTwO));
	}

}
