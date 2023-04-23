package es.ajrj.cc.price.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.ajrj.cc.price.entity.Price;

/**
 * Price persistence layer
 * 
 * @author ajrj
 *
 */
public interface IPriceRepository extends JpaRepository<Price, Long> {

	/**
	 * Gets the available rate for a given date, product identifier, and chain
	 * identifier
	 * 
	 * @param applicationDate consultation date
	 * @param productId       product identifier
	 * @param brandId         chain identifier
	 * @return price entity
	 */
	@Query("SELECT price FROM Price price WHERE price.startDate <= :applicationDate AND "
			+ "price.endDate >= :applicationDate AND productId = :productId AND brandId = :brandId "
			+ "ORDER BY price.priority DESC LIMIT 1")
	Optional<Price> findAvalaiblePrice(LocalDateTime applicationDate, Long productId, Long brandId);

}
