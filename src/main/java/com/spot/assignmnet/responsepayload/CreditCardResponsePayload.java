package com.spot.assignmnet.responsepayload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardResponsePayload {


	private String cardNumber;

	private String cardHolderName;
	
	private String country;
	
	
	public CreditCardResponsePayload(String cardNumber, String cardHolderName, String country) {
		
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.country = country;
	}
 
}
