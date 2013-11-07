package com.ss.exercise;

import com.ss.exercise.bean.Payment;
import com.ss.exercise.bean.PaymentBuilder;
import com.ss.exercise.bean.PaymentFromLineInputBuilder;
import com.ss.exercise.validator.ValidationException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentFromLineInputBuilderTest {

	private PaymentBuilder builder;
	private Payment result;

	@Test(expected = ValidationException.class)
	public void emptyStringShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void missingAmountShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("USD");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void onlyWhiteSpaceInputShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue(" ");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void twoLetterCurrencyShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("US 200");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void fourLetterCurrencyShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("USSD 200");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void currencyContainingNumbersShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("US2 200");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void inputConsistingMoreThan2WordsShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("USD 200 a");
		whenBuildingPaymentFromLineInput();
	}

	@Test(expected = ValidationException.class)
	public void wrongAmountFormatShouldThrowValidationException() throws ValidationException {
		givenLineInputOfValue("USD 20s");
		whenBuildingPaymentFromLineInput();
	}

	@Test
	public void lowerCaseLetterCurrencyShouldBeValidatedAndTransformedToUpperCase() throws ValidationException {
		givenLineInputOfValue("usd 200");
		whenBuildingPaymentFromLineInput();
		thenTheResultShouldBe("USD", new BigDecimal(200));
	}

	@Test
	public void onlyWhiteSpaceAdditionalParametersShouldBeValidated() throws ValidationException {
		givenLineInputOfValue("USD 200  ");
		whenBuildingPaymentFromLineInput();
		thenTheResultShouldBe("USD", new BigDecimal(200));
	}

	private void givenLineInputOfValue(String lineInput) {
		builder = new PaymentFromLineInputBuilder(lineInput);
	}

	private void whenBuildingPaymentFromLineInput() throws ValidationException {
		this.result = builder.build();
	}

	private void thenTheResultShouldBe(String currency, BigDecimal amount) {
		assertEquals(result.getCurrency(), currency);
		assertEquals(result.getAmount(), amount);
	}
}
