package com.spot.assignmnet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;
import com.spot.assignmnet.service.ICreditCardService;
import com.spot.assignmnet.util.CreditCardUtil;

@Service
public class CreditCardServiceImpl implements ICreditCardService {

	private static Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

	@Value("#{'${banned.countries}'.trim().isEmpty() ? new String[] {} : '${banned.countries}'.split(',')}")
	private Set<String> bannedCountries;

	Map<String, Object> validCreditCard = new HashMap<String, Object>();

	@Override
	public Set<CreditCardResponsePayload> addCreditCard(
			List<CreditCardRequestPayload> creditCardRequestPayloadObjectList) {

		RestTemplate restTemplate = new RestTemplate();

		Set<CreditCardResponsePayload> creditCardResponsePayloadList = new HashSet<CreditCardResponsePayload>();

		JSONObject country = null;
		String countryName = null;
		for (CreditCardRequestPayload creditCardRequestPayload : creditCardRequestPayloadObjectList) {

			String cardNumber = creditCardRequestPayload.getCreditCardNumber();
			String cardHolderName = creditCardRequestPayload.getCardHolderName();

			if (CreditCardUtil.isValidCreditCardNumber(cardNumber)) {
				String urlStr = "https://lookup.binlist.net/" + cardNumber;
				try {
					String creditCardMeta = restTemplate.getForObject(urlStr, String.class);
					JSONObject jsonObject = new JSONObject(creditCardMeta);
					if (jsonObject != null) {
						if (jsonObject.has("country")) {
							country = (JSONObject) jsonObject.get("country");
							if (country != null) {
								if (country.has("name")) {
									countryName = (String) country.get("name");
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("Exception while calling third party api in method : {}",
							Thread.currentThread().getStackTrace()[1].getMethodName(), e);
				}
				if (countryName != null && !countryName.isEmpty()) {
					if (!bannedCountries.contains(countryName)) {

						if (!validCreditCard.containsKey(cardNumber)) {
							if (!validCreditCard.containsKey(cardNumber)) {
								CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload(
										cardNumber, cardHolderName, countryName);
								validCreditCard.put(cardNumber, creditCardResponsePayload);
								creditCardResponsePayloadList.add(creditCardResponsePayload);
							}
						}
					}
				}
			}
		}
		return creditCardResponsePayloadList;

	}

	@Override
	public Set<Object> getAllCreditCard() {

		Set<Object> responseSet = new HashSet<Object>();
		
		for(String cardNumberKey : validCreditCard.keySet()) {
			
			CreditCardResponsePayload creditCardResponsePayload = (CreditCardResponsePayload) validCreditCard.get(cardNumberKey);
			
			List<String> cardData = new ArrayList<String>();
			
			cardData.add(creditCardResponsePayload.getCardNumber());
			cardData.add(creditCardResponsePayload.getCardHolderName());
			cardData.add(creditCardResponsePayload.getCountry());
			
			responseSet.add(cardData);
			
		}
		
		return responseSet;
	}

	@Override
	public CreditCardResponsePayload getCardByCardNumber(String cardNumber) {

		if (validCreditCard.containsKey(cardNumber)) {
			return (CreditCardResponsePayload) validCreditCard.get(cardNumber);
		}
		return null;
	}

}
