package es.ajrj.cc.price.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object representing a price
 * 
 * @author ajrj
 *
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponseDTO {

	/**
	 * Group chain foreign key
	 */
	private Long brandId;

	/**
	 * Rate application start date
	 */
	private LocalDateTime startDate;

	/**
	 * Rate application end date
	 */
	private LocalDateTime endDate;

	/**
	 * Rate application start date
	 */
	private Long priceList;

	/**
	 * Product foreign key
	 */
	private Long productId;

	/**
	 * Final sale price
	 */
	private BigDecimal pvp;

}
