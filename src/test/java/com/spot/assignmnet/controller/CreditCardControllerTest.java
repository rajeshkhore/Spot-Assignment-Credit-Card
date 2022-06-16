package com.spot.assignmnet.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;
import com.spot.assignmnet.service.ICreditCardService;

@ExtendWith(MockitoExtension.class)
public class CreditCardControllerTest {
	
	@InjectMocks
	private CreditCardController creditCardController;
	
	@Mock
	private ICreditCardService creditCardService;
	
	public Set<CreditCardResponsePayload> getCreditCardResponse(){
		Set<CreditCardResponsePayload> creditCardResponse = new HashSet<CreditCardResponsePayload>();
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		creditCardResponse.add(creditCardResponsePayload);
		return creditCardResponse;
	}
	public Set<CreditCardResponsePayload> getCreditCardResponseElse(){
		Set<CreditCardResponsePayload> creditCardResponse = new HashSet<CreditCardResponsePayload>();
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		return creditCardResponse;
	}
	
	public List<CreditCardRequestPayload> getCreditCardRequest(){
		List<CreditCardRequestPayload> creditCardRequest = new ArrayList<CreditCardRequestPayload>();
		CreditCardRequestPayload creditCardRequestPayload = new CreditCardRequestPayload();
		creditCardRequestPayload.setCardHolderName("Test");
		creditCardRequestPayload.setCreditCardNumber("45717360");
		creditCardRequest.add(creditCardRequestPayload);
		return creditCardRequest;
	}
	public Set<Object> getAllCreditCardList(){
		return null;
	}
	public CreditCardResponsePayload getCreditCardResponsePayLoad() {
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		return creditCardResponsePayload;
	}
	public CreditCardResponsePayload getCreditCardResponsePayLoadElse() {
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		return null;
	}
	@Test
	void addCreditCard() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any())).thenReturn(getCreditCardResponse());
		ResponseEntity<?> response = creditCardController.addCreditCard(getCreditCardRequest());
		Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
        Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}
	
	@Test
	void addCreditCardElse() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any())).thenReturn(getCreditCardResponseElse());
		ResponseEntity<?> response = creditCardController.addCreditCard(getCreditCardRequest());
		Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
        Assertions.assertTrue(response.hasBody(), "Response should contain body");
	} 
	@Test
	void addCreditCardforException() {
		Mockito.when(creditCardService.addCreditCard(Mockito.any())).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.addCreditCard(getCreditCardRequest());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "HttpStatus should be INTERNAL_SERVER_ERROR");
	}
	@Test 
	void getAllCreditCard (){
		Mockito.when(creditCardService.getAllCreditCard()).thenReturn(getAllCreditCardList());
		ResponseEntity<?> response = creditCardController.getAllCreditCard();
		Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
        Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}
	@Test 
	void getAllCreditCardForExceptionCheck (){
		Mockito.when(creditCardService.getAllCreditCard()).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.getAllCreditCard();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "HttpStatus should be INTERNAL_SERVER_ERROR");
	}
	@Test
	void getCardByCardNumber() {
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString())).thenReturn(getCreditCardResponsePayLoad());
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
		Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
        Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}
	@Test
	void getCardByCardNumberElse() {
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString())).thenReturn(getCreditCardResponsePayLoadElse());
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
		Assertions.assertNotNull(response, "Response should not be null");
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "HttpStatus should be OK");
        Assertions.assertTrue(response.hasBody(), "Response should contain body");
	}
	@Test 
	void getCardByCardNumberForExceptionCheck (){
		Mockito.when(creditCardService.getCardByCardNumber(Mockito.anyString())).thenThrow(NullPointerException.class);
		ResponseEntity<?> response = creditCardController.getCardByCardNumber("45717360");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "HttpStatus should be INTERNAL_SERVER_ERROR");
	}

}
