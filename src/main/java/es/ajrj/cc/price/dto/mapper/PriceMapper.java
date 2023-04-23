package es.ajrj.cc.price.dto.mapper;

import org.springframework.stereotype.Component;

import es.ajrj.cc.price.dto.PriceResponseDTO;
import es.ajrj.cc.price.entity.Price;

/**
 * Conversion from entity to data transfer object and vice versa for the Price
 * entity
 * 
 * @author ajrj
 *
 */
@Component
public class PriceMapper {

	/**
	 * Map from Price entity to PriceResponse DTO
	 * 
	 * @param price represents a price entity object
	 * @return price response DTO mapped object
	 */
	public PriceResponseDTO mapToPriceResponse(Price price) {
		PriceResponseDTO priceResponse = new PriceResponseDTO();
		if (price != null) {
			priceResponse.setBrandId(price.getBrandId());
			priceResponse.setStartDate(price.getStartDate());
			priceResponse.setEndDate(price.getEndDate());
			priceResponse.setPriceList(price.getPriceList());
			priceResponse.setProductId(price.getProductId());
			priceResponse.setPvp(price.getPvp());
		}
		return priceResponse;
	}

}
