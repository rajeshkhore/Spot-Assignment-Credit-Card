package com.spot.assignmnet.requestPayload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardRequestPayload {

	private String creditCardNumber;

	private String cardHolderName;

	public CreditCardRequestPayload(String creditCardNumber, String cardHolderName) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.cardHolderName = cardHolderName;
	}

}
