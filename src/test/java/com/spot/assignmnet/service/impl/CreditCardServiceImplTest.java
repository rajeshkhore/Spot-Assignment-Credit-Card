package com.spot.assignmnet.service.impl;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.spot.assignmnet.utils.CreditCardUtils;

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

	@BeforeAll
	public void setUp() {
		ReflectionTestUtils.setField(creditCardServiceImpl, "bannedCountries", CreditCardUtils.listOfBannedCountry());
	}

	@Test
	void addCreditCard() {
		Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn("{\n" + "    \"number\": {\n" + "        \"length\": 16,\n" + "        \"luhn\": true\n"
						+ "    },\n" + "    \"scheme\": \"visa\",\n" + "    \"type\": \"debit\",\n"
						+ "    \"brand\": \"Visa/Dankort\",\n" + "    \"prepaid\": false,\n" + "    \"country\": {\n"
						+ "        \"numeric\": \"208\",\n" + "        \"alpha2\": \"DK\",\n"
						+ "        \"name\": \"Denmark\",\n" + "        \"emoji\": \":flag-dk:\",\n"
						+ "        \"currency\": \"DKK\",\n" + "        \"latitude\": 56,\n"
						+ "        \"longitude\": 10\n" + "    },\n" + "    \"bank\": {\n"
						+ "        \"name\": \"Jyske Bank\",\n" + "        \"url\": \"www.jyskebank.dk\",\n"
						+ "        \"phone\": \"+4589893300\",\n" + "        \"city\": \"Hjørring\"\n" + "    }\n"
						+ "}");

		creditCardServiceImpl.addCreditCard(CreditCardUtils.getCreditCardRequest());
		getAllCreditCard();
		getCardByCardNumber();
	}

	@Test
	void getAllCreditCard() {
		Mockito.when(creditCardServiceImpl.getAllCreditCard()).thenReturn(CreditCardUtils.getSetData());
	}

	@Test
	void getCardByCardNumber() {
		Mockito.when(creditCardServiceImpl.getCardByCardNumber("45717360"))
				.thenReturn(CreditCardUtils.getcreditCardResponsePayload());
	}

	@Test
	void addCreditCard1() {
		Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
				.thenThrow(NullPointerException.class);
		creditCardServiceImpl.addCreditCard(CreditCardUtils.getCreditCardRequest());
	}

}
