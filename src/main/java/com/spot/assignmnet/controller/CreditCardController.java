package com.spot.assignmnet.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;
import com.spot.assignmnet.service.ICreditCardService;

@RestController
@RequestMapping("/api/credit-card")
public class CreditCardController {

	private static Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	@Autowired
	ICreditCardService creditCardService;

	@PostMapping
	public ResponseEntity<? extends Object> addCreditCard(
			@RequestBody List<CreditCardRequestPayload> creditCardRequestPayloadObjectList) {

		try {
			List<List<Object>> validCreditCardMap = creditCardService
					.addCreditCard(creditCardRequestPayloadObjectList);

			if (validCreditCardMap != null && !validCreditCardMap.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body("Valid cards entered successfully: " + validCreditCardMap);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Invalid card number, Please enter valid card number");
			}

		} catch (Exception exception) {
			logger.error("Exception in method: {}, creditCardList: {}, exception: {}",
					Thread.currentThread().getStackTrace()[1].getMethodName(), creditCardRequestPayloadObjectList,
					exception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong while adding credit-cards. Please try again later");

		}
	}

	@GetMapping()
	public ResponseEntity<? extends Object> getAllCreditCard() {

		try {
			Set<Object> validCreditCard = creditCardService.getAllCreditCard();

			if (validCreditCard != null && !validCreditCard.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body("List of credit cards: " + validCreditCard);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No valid cards found");
			}
		} catch (Exception exception) {
			logger.error("Exception while fetching credit card list in method: {}, exception: {}",
					Thread.currentThread().getStackTrace()[1].getMethodName(), exception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong while getting all credit-cards. Please try again later");
		}

	}

	@GetMapping("/{card-number}")
	public ResponseEntity<? extends Object> getCardByCardNumber(
			@PathVariable(value = "card-number") String cardNumber) {

		try {
			CreditCardResponsePayload creditCard = creditCardService.getCardByCardNumber(cardNumber);

			if (creditCard != null) {
				return ResponseEntity.status(HttpStatus.OK).body(creditCard);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("No card found: " + cardNumber);
			}

		} catch (Exception exception) {
			logger.error("Exception while fetching card from card number in method: {}, card number: {}, exception: {}",
					Thread.currentThread().getStackTrace()[1].getMethodName(), cardNumber, exception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Something went wrong while getting credit-card information. Please try again later");

		}
	}

}
