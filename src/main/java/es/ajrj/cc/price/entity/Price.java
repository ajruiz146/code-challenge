package es.ajrj.cc.price.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity that represents a rate between certain dates
 * 
 * @author ajrj
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CC_PRICES")
public class Price {

	/**
	 * Price identifier. Primary key. Auto sequence value, incremented by 1
	 */
	@Id
	@Column(name = "PRICE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long priceId;

	/**
	 * Group chain foreign key
	 */
	@Column(name = "BRAND_ID")
	private Long brandId;

	/**
	 * Rate application start date
	 */
	@Column(name = "START_DATE")
	private LocalDateTime startDate;

	/**
	 * Rate application end date
	 */
	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	/**
	 * Rate application start date
	 */
	@Column(name = "PRICE_LIST")
	private Long priceList;

	/**
	 * Product foreign key
	 */
	@Column(name = "PRODUCT_ID")
	private Long productId;

	/**
	 * Priority in the event that two rates coincide in a temporary space
	 */
	@Column(name = "PRIORITY")
	private Long priority;

	/**
	 * Final sale price
	 */
	@Column(name = "PRICE")
	private BigDecimal pvp;

	/**
	 * ISO currency
	 */
	@Column(name = "CURR")
	private String curr;

}
