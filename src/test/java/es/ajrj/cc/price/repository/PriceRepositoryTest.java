package es.ajrj.cc.price.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import es.ajrj.cc.price.entity.Price;

@DataJpaTest
class PriceRepositoryTest {

	@Autowired
	private IPriceRepository priceRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	/**
	 * Test for findAvalaiblePrice. Should return price details from database
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_price_from_database() throws Exception {
		// arrange
		testEntityManager.persistAndFlush(new Price(null, 1L, LocalDateTime.parse("2023-04-23T00:00:00"),
				LocalDateTime.parse("2023-05-23T23:59:59"), 101L, 35455L, 0L, BigDecimal.valueOf(35.50), "EUR"));
		testEntityManager.clear();
		// act and assert
		Price price = priceRepository.findAvalaiblePrice(LocalDateTime.parse("2023-04-23T00:00:00"), 35455L, 1L)
				.orElseThrow();
		Price priceExpected = new Price(5L, 1L, LocalDateTime.parse("2023-04-23T00:00:00"),
				LocalDateTime.parse("2023-05-23T23:59:59"), 101L, 35455L, 0L, new BigDecimal("35.50"), "EUR");
		assertThat(price).isEqualTo(priceExpected);
	}

	/**
	 * Test for findAvalaiblePrice. Should return null when database does not have
	 * it
	 * 
	 * @throws Exception
	 */
	@Test
	void findAvalaiblePrice_should_return_null_when_database_does_not_have_it() throws Exception {
		// act and assert
		Optional<Price> price = priceRepository.findAvalaiblePrice(LocalDateTime.parse("2023-04-23T00:00:00"), 0L,
				35455L);
		assertThat(price).isEmpty();
	}

}
