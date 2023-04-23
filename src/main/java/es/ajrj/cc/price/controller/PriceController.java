package es.ajrj.cc.price.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ajrj.cc.price.dto.PriceResponseDTO;
import es.ajrj.cc.price.dto.mapper.PriceMapper;
import es.ajrj.cc.price.service.IPriceService;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Price presentation layer
 * 
 * @author ajrj
 *
 */

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prices")
public class PriceController {

	@NonNull
	private PriceMapper priceMapper;

	@NonNull
	private IPriceService priceService;

	/**
	 * Gets the available rate for a given date, product identifier, and chain
	 * identifier
	 * 
	 * @param applicationDate consultation date
	 * @param productId       product identifier
	 * @param brandId         chain identifier
	 * @return price entity
	 */
	@GetMapping(path = "/avalaible", produces = MediaType.APPLICATION_JSON_VALUE)
	public PriceResponseDTO findAvalaiblePrice(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
			@RequestParam @Positive Long productId, @RequestParam @Positive Long brandId) {
		return priceMapper.mapToPriceResponse(priceService.findAvalaiblePrice(applicationDate, productId, brandId));
	}

}
