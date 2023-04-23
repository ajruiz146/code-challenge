package es.ajrj.cc.price.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import es.ajrj.cc.price.entity.Price;
import es.ajrj.cc.price.exception.ResourceNotFoundException;
import es.ajrj.cc.price.repository.IPriceRepository;
import es.ajrj.cc.price.service.IPriceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Price business logic layer implementation
 * 
 * @author ajrj
 *
 */

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements IPriceService {

	@NonNull
	private IPriceRepository priceRepository;

	@Override
	public Price findAvalaiblePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
		return priceRepository.findAvalaiblePrice(applicationDate, productId, brandId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Not found Price with applicationDate: '%s', productId: '%d', brandId: '%d'",
								applicationDate, productId, brandId)));
	}

}
