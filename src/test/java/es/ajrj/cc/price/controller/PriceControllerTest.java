package es.ajrj.cc.price.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import es.ajrj.cc.price.dto.PriceResponseDTO;
import es.ajrj.cc.price.dto.mapper.PriceMapper;
import es.ajrj.cc.price.entity.Price;
import es.ajrj.cc.price.exception.ResourceNotFoundException;
import es.ajrj.cc.price.service.IPriceService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

/**
 * Unit test for Price presentation layer
 * 
 * @author ajrj
 *
 */
@WebMvcTest(PriceController.class)
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPriceService priceService;

	@MockBean
	private PriceMapper priceMapper;

	/**
	 * Test for findAvalaiblePrice. Should return http 200 given valid parameters
	 * 
	 * @throws Exception
	 */

	@Test
	void findAvalaiblePrice_should_return_http_200_given_valid_parameters() throws Exception {
		// arrange - train your mock
		given(priceService.findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong()))
				.willReturn(new Price(1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
						LocalDateTime.parse("2020-12-31T23:59:59"), 1L, 35455L, 0L, BigDecimal.valueOf(35.50), "EUR"));
		given(priceMapper.mapToPriceResponse(any(Price.class)))
				.willReturn(new PriceResponseDTO(1L, LocalDateTime.parse("2020-06-14T00:00:00"),
						LocalDateTime.parse("2020-12-31T23:59:59"), 1L, 35455L, BigDecimal.valueOf(35.50)));
		// act & assert
		mockMvc.perform(get("/api/v1/prices/avalaible").param("applicationDate", "2020-06-14T10:00:00")
				.param("productId", "35455").param("brandId", "1")).andExpect(status().isOk())
				.andExpect(jsonPath("brandId").value(1)).andExpect(jsonPath("startDate").value("2020-06-14T00:00:00"))
				.andExpect(jsonPath("endDate").value("2020-12-31T23:59:59")).andExpect(jsonPath("priceList").value(1))
				.andExpect(jsonPath("productId").value(35455)).andExpect(jsonPath("pvp").value(35.50));
		// verify that dependency is invoked
		verify(priceService).findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong());
	}

	/**
	 * Test for findAvalaiblePrice. Should return http 404 given invalid parameters
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_http_404_given_invalid_parameters() throws Exception {
		// arrange
		given(priceService.findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong()))
				.willThrow(new ResourceNotFoundException());
		// act & assert
		mockMvc.perform(get("/api/v1/prices/avalaible").param("applicationDate", "2020-06-13T23:59:59")
				.param("productId", "35455").param("brandId", "1")).andExpect(status().isNotFound());
		// verify
		verify(priceService).findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong());
	}

}
