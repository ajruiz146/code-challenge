package es.ajrj.cc.price.exception;

import lombok.NoArgsConstructor;

/**
 * Custom RuntimeException for not founds resources
 * 
 * @author ajrj
 *
 */

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5236525467128161780L;

	/**
	 * ResourceNotFoundException constructor
	 * 
	 * @param message caused information message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
