package com.spot.assignmnet.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;

public class CreditCardUtils {
	public static Set<CreditCardResponsePayload> getCreditCardResponse() {
		Set<CreditCardResponsePayload> creditCardResponse = new HashSet<CreditCardResponsePayload>();
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		creditCardResponse.add(creditCardResponsePayload);
		return creditCardResponse;
	}

	public static List<List<Object>> getCreditCardResponseElse() {
		List list1 = new ArrayList();
		List<CreditCardResponsePayload> creditCardResponse = new ArrayList<CreditCardResponsePayload>();
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		return list1;
	}

	public static List<List<Object>> getListOfCreditCardResponse() {
		List list1 = new ArrayList<>();
		List<CreditCardResponsePayload> creditCardResponse = new ArrayList<CreditCardResponsePayload>();
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		creditCardResponse.add(creditCardResponsePayload);
		list1.add(creditCardResponse);
		return list1;
	}

	public static List<CreditCardRequestPayload> getCreditCardRequest() {
		List<CreditCardRequestPayload> creditCardRequest = new ArrayList<CreditCardRequestPayload>();
		CreditCardRequestPayload creditCardRequestPayload = new CreditCardRequestPayload();
		creditCardRequestPayload.setCardHolderName("Test");
		creditCardRequestPayload.setCreditCardNumber("45717360");
		creditCardRequest.add(creditCardRequestPayload);
		return creditCardRequest;
	}

	public static Set<Object> getAllCreditCardList() {
		return null;
	}

	public static CreditCardResponsePayload getCreditCardResponsePayLoad() {
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		return creditCardResponsePayload;
	}

	public static CreditCardResponsePayload getCreditCardResponsePayLoadElse() {
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		return null;
	}

	public static CreditCardResponsePayload getcreditCardResponsePayload() {
		CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
		creditCardResponsePayload.setCardHolderName("Test");
		creditCardResponsePayload.setCardNumber("45717360");
		creditCardResponsePayload.setCountry("Denmark");
		return creditCardResponsePayload;
	}

	public static Set getSetData() {
		Set set = new HashSet();
		set.add("");
		return set;
	}

	public static Set<String> listOfBannedCountry() {
		Set<String> set = new HashSet<String>();
		set.add("Pakistan");
		set.add("United States of America");
		return set;
	}

}
