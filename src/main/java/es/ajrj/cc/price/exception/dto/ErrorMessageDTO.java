package es.ajrj.cc.price.exception.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {

	private LocalDateTime date;

	private Integer code;

	private String message;

	private String path;

}
