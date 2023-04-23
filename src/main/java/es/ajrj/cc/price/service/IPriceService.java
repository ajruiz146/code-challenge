package es.ajrj.cc.price.service;

import java.time.LocalDateTime;

import es.ajrj.cc.price.entity.Price;

/**
 * Price business layer
 * 
 * @author ajrj
 *
 */
public interface IPriceService {

	/**
	 * Gets the available rate for a given date, product identifier, and chain
	 * identifier
	 * 
	 * @param applicationDate consultation date
	 * @param productId       product identifier
	 * @param brandId         chain identifier
	 * @return price entity
	 */
	Price findAvalaiblePrice(LocalDateTime applicationDate, Long productId, Long brandId);

}
