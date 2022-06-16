package com.spot.assignmnet.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spot.assignmnet.service.ICreditCardService;
import com.spot.assignmnet.utils.CreditCardUtils;

@ExtendWith(MockitoExtension.class)
public class CreditCardControllerTest {

	@InjectMocks
	private CreditCardController creditCardController;

	@Mock
	private ICreditCardService creditCardService;

	@Test
	void addCreditCard() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any()))
				.thenReturn(CreditCardUtils.getListOfCreditCardResponse());
		ResponseEntity<?> response = creditCardController.addCreditCard(CreditCardUtils.getCreditCardRequest());
		Assertions.assertNotNull(response, "Response should not be null");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
		Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}

	@Test
	void addCreditCardElse() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any()))
				.thenReturn(CreditCardUtils.getCreditCardResponseElse());
		ResponseEntity<?> response = creditCardController.addCreditCard(CreditCardUtils.getCreditCardRequest());
		Assertions.assertNotNull(response, "Response should not be null");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
		Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}

	@Test
	void addCreditCardforException() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any())).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.addCreditCard(CreditCardUtils.getCreditCardRequest());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(),
				"HttpStatus should be INTERNAL_SERVER_ERROR");
	}

	@Test
	void getAllCreditCard() {
		Mockito.when(creditCardService.getAllCreditCard()).thenReturn(CreditCardUtils.getAllCreditCardList());
		ResponseEntity<?> response = creditCardController.getAllCreditCard();
		Assertions.assertNotNull(response, "Response should not be null");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
		Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}

	@Test
	void getAllCreditCardForExceptionCheck() {
		Mockito.when(creditCardService.getAllCreditCard()).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.getAllCreditCard();
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(),
				"HttpStatus should be INTERNAL_SERVER_ERROR");
	}

	@Test
	void getCardByCardNumber() {
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString()))
				.thenReturn(CreditCardUtils.getCreditCardResponsePayLoad());
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
		Assertions.assertNotNull(response, "Response should not be null");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
		Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}

	@Test
	void getCardByCardNumberElse() {
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString()))
				.thenReturn(CreditCardUtils.getCreditCardResponsePayLoadElse());
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
		Assertions.assertNotNull(response, "Response should not be null");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
		Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}

	@Test
	void getCardByCardNumberForExceptionCheck() {
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString())).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(),
				"HttpStatus should be INTERNAL_SERVER_ERROR");
	}

}
