package es.ajrj.cc.price.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ajrj.cc.price.entity.Price;
import es.ajrj.cc.price.exception.ResourceNotFoundException;
import es.ajrj.cc.price.repository.IPriceRepository;
import es.ajrj.cc.price.service.IPriceService;

/**
 * Unit test for Price business logic layer
 * 
 * @author ajrj
 *
 */
@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

	private IPriceService priceService;

	@Mock
	private IPriceRepository priceRepository;

	@BeforeEach
	public void setUp() throws Exception {
		priceService = new PriceServiceImpl(priceRepository);
	}

	/**
	 * Test for findAvalaiblePrice. Should return price details given valid
	 * parameters
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_price_details_given_valid_parameters() throws Exception {
		// arrange
		given(priceRepository.findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong()))
				.willReturn(Optional.of(new Price(1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
						LocalDateTime.parse("2020-12-31T23:59:59"), 1L, 35455L, 0L, new BigDecimal("35.50"), "EUR")));
		// act and assert
		Price price = priceService.findAvalaiblePrice(LocalDateTime.parse("2020-06-14T00:00:00"), 35455L, 1L);
		Price priceExpected = new Price(1L, 1L, LocalDateTime.parse("2020-06-14T00:00:00"),
				LocalDateTime.parse("2020-12-31T23:59:59"), 1L, 35455L, 0L, new BigDecimal("35.50"), "EUR");
		assertThat(price).isEqualTo(priceExpected);
		// verify
		verify(priceRepository).findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong());
	}

	/**
	 * Test for findAvalaiblePrice. Should return ResourceNotFoundException given
	 * invalid parameters
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_ResourceNotFoundException_given_invalid_parameters() throws Exception {
		// arrange
		given(priceRepository.findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong()))
				.willReturn(Optional.empty());
		// act and assert
		LocalDateTime date = LocalDateTime.parse("2020-06-13T23:59:59");
		assertThrows(ResourceNotFoundException.class, () -> {
			priceService.findAvalaiblePrice(date, 35455L, 1L);
		});
		// verify
		verify(priceRepository).findAvalaiblePrice(any(LocalDateTime.class), anyLong(), anyLong());
	}

}
