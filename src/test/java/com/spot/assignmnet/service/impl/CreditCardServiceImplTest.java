package com.spot.assignmnet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.spot.assignmnet.requestPayload.CreditCardRequestPayload;
import com.spot.assignmnet.responsepayload.CreditCardResponsePayload;

@SpringBootTest
@SpringJUnitConfig
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreditCardServiceImplTest {
	@InjectMocks
	@Spy
	private CreditCardServiceImpl creditCardServiceImpl;
	
	@Value("#{'${banned.countries}'.trim().isEmpty() ? new String[] {} : '${banned.countries}'.split(',')}")
	private Set<String> bannedCountries;
	
	@Mock
	RestTemplate restTemplate;
	
		 public CreditCardResponsePayload getcreditCardResponsePayload() {
			CreditCardResponsePayload creditCardResponsePayload = new CreditCardResponsePayload();
			creditCardResponsePayload.setCardHolderName("Test");
			creditCardResponsePayload.setCardNumber("45717360");
			creditCardResponsePayload.setCountry("Denmark");
		 return creditCardResponsePayload;
	 }
		 public Set getSetData() {
			 Set set = new HashSet();
			 set.add("");
			 return set;
		 }
	
	@BeforeAll
	public void setUp() {
	    ReflectionTestUtils.setField(creditCardServiceImpl, "bannedCountries", listOfBannedCountry());
	  }
	public List<CreditCardRequestPayload> getCreditCardRequest(){
		List<CreditCardRequestPayload> creditCardRequest = new ArrayList<CreditCardRequestPayload>();
		CreditCardRequestPayload creditCardRequestPayload = new CreditCardRequestPayload();
		creditCardRequestPayload.setCardHolderName("Test");
		creditCardRequestPayload.setCreditCardNumber("45717360");
		creditCardRequest.add(creditCardRequestPayload);
		return creditCardRequest;
	}
	public Set<String> listOfBannedCountry(){
		Set<String> set = new HashSet<String>();
		set.add("Pakistan");
		set.add("United States of America");
		return set;
	}
	@Test
	void addCreditCard() {
		Mockito.when(restTemplate.getForObject(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenReturn("{\n"
				+ "    \"number\": {\n"
				+ "        \"length\": 16,\n"
				+ "        \"luhn\": true\n"
				+ "    },\n"
				+ "    \"scheme\": \"visa\",\n"
				+ "    \"type\": \"debit\",\n"
				+ "    \"brand\": \"Visa/Dankort\",\n"
				+ "    \"prepaid\": false,\n"
				+ "    \"country\": {\n"
				+ "        \"numeric\": \"208\",\n"
				+ "        \"alpha2\": \"DK\",\n"
				+ "        \"name\": \"Denmark\",\n"
				+ "        \"emoji\": \":flag-dk:\",\n"
				+ "        \"currency\": \"DKK\",\n"
				+ "        \"latitude\": 56,\n"
				+ "        \"longitude\": 10\n"
				+ "    },\n"
				+ "    \"bank\": {\n"
				+ "        \"name\": \"Jyske Bank\",\n"
				+ "        \"url\": \"www.jyskebank.dk\",\n"
				+ "        \"phone\": \"+4589893300\",\n"
				+ "        \"city\": \"Hjørring\"\n"
				+ "    }\n"
				+ "}");
		
		
		creditCardServiceImpl.addCreditCard(getCreditCardRequest());
		getAllCreditCard();
		getCardByCardNumber();
	}
	
	@Test
	void getAllCreditCard() {
		Mockito.when(creditCardServiceImpl.getAllCreditCard()).thenReturn(getSetData());
	}
	@Test
	void getCardByCardNumber() {
		Mockito.when(creditCardServiceImpl.getCardByCardNumber("45717360")).thenReturn(getcreditCardResponsePayload());
	}
	@Test
	void addCreditCard1() {
		Mockito.when(restTemplate.getForObject(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).thenThrow(NullPointerException.class);
		creditCardServiceImpl.addCreditCard(getCreditCardRequest());
	}
	

}
