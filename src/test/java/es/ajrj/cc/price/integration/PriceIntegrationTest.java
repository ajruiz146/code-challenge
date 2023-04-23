package es.ajrj.cc.price.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.ajrj.cc.price.dto.PriceResponseDTO;

/**
 * Integration tests for Price
 * 
 * @author ajrj
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PriceIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * Test for findAvalaiblePrice. Request at "2020-06-14T10:00:00"
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_url_01() throws Exception {
		// arrange
		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("applicationDate", "2020-06-14T10:00:00");
		uriParams.put("productId", "35455");
		uriParams.put("brandId", "1");
		PriceResponseDTO priceResponseExpected = PriceResponseDTO.builder().brandId(1L)
				.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).priceList(1L).productId(35455L)
				.pvp(new BigDecimal("35.50")).build();
		findAvalaiblePrice_request(uriParams, priceResponseExpected);
	}

	/**
	 * Test for findAvalaiblePrice. Request at "2020-06-14T16:00:00"
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_url_02() throws Exception {
		// arrange
		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("applicationDate", "2020-06-14T16:00:00");
		uriParams.put("productId", "35455");
		uriParams.put("brandId", "1");
		PriceResponseDTO priceResponseExpected = PriceResponseDTO.builder().brandId(1L)
				.startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
				.endDate(LocalDateTime.parse("2020-06-14T18:30:00")).priceList(2L).productId(35455L)
				.pvp(new BigDecimal("25.45")).build();
		findAvalaiblePrice_request(uriParams, priceResponseExpected);
	}

	/**
	 * Test for findAvalaiblePrice. Request at "2020-06-14T21:00:00"
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_url_03() throws Exception {
		// arrange
		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("applicationDate", "2020-06-14T21:00:00");
		uriParams.put("productId", "35455");
		uriParams.put("brandId", "1");
		PriceResponseDTO priceResponseExpected = PriceResponseDTO.builder().brandId(1L)
				.startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).priceList(1L).productId(35455L)
				.pvp(new BigDecimal("35.50")).build();
		findAvalaiblePrice_request(uriParams, priceResponseExpected);
	}

	/**
	 * Test for findAvalaiblePrice. Request at "2020-06-15T10:00:00"
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_url_04() throws Exception {
		// arrange
		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("applicationDate", "2020-06-15T10:00:00");
		uriParams.put("productId", "35455");
		uriParams.put("brandId", "1");
		PriceResponseDTO priceResponseExpected = PriceResponseDTO.builder().brandId(1L)
				.startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
				.endDate(LocalDateTime.parse("2020-06-15T11:00:00")).priceList(3L).productId(35455L)
				.pvp(new BigDecimal("30.50")).build();
		findAvalaiblePrice_request(uriParams, priceResponseExpected);
	}

	/**
	 * Test for findAvalaiblePrice. Request at "2020-06-16T21:00:00"
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_url_05() throws Exception {
		// arrange
		Map<String, String> uriParams = new HashMap<>();
		uriParams.put("applicationDate", "2020-06-16T21:00:00");
		uriParams.put("productId", "35455");
		uriParams.put("brandId", "1");
		PriceResponseDTO priceResponseExpected = PriceResponseDTO.builder().brandId(1L)
				.startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
				.endDate(LocalDateTime.parse("2020-12-31T23:59:59")).priceList(4L).productId(35455L)
				.pvp(new BigDecimal("38.95")).build();
		findAvalaiblePrice_request(uriParams, priceResponseExpected);
	}

	private void findAvalaiblePrice_request(Map<String, String> uriParams, PriceResponseDTO priceResponseExpected) {
		// act & assert
		ResponseEntity<PriceResponseDTO> responseEntity = testRestTemplate.getForEntity(
				"/api/v1/prices/avalaible?applicationDate={applicationDate}&productId={productId}&brandId={brandId}",
				PriceResponseDTO.class, uriParams);
		assertThat(responseEntity.getBody()).usingRecursiveComparison().isEqualTo(priceResponseExpected);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
