package com.spot.assignmnet.service;

import java.util.List;
import java.util.Set;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;

public interface ICreditCardService {

	Set<CreditCardResponsePayload> addCreditCard(List<CreditCardRequestPayload> creditCardRequestPayloadObjectList);

	Set<Object> getAllCreditCard();

	CreditCardResponsePayload getCardByCardNumber(String cardNumber);

}
