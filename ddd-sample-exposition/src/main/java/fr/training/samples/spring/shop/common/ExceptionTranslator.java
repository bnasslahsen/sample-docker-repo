package fr.training.samples.spring.shop.common;

import java.util.UUID;

import fr.training.samples.spring.shop.domain.common.exception.BusinessException;
import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.common.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionTranslator {
	/**
	 * logger of type Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionTranslator.class);

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorModel> handleRunTimeException(RuntimeException e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	@ExceptionHandler({ BusinessException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorModel> handleBusinessException(BusinessException e) {
		return error(HttpStatus.BAD_REQUEST, e);
	}

	@ExceptionHandler({ TechnicalException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorModel> handleTechnicalException(TechnicalException e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	@ExceptionHandler({ NotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException e) {
		return error(HttpStatus.NOT_FOUND, e);
	}

	private ResponseEntity<ErrorModel> error(HttpStatus status, Exception e) {
		LOG.error("Exception : ", e);
		return ResponseEntity.status(status).body(new ErrorModel(UUID.randomUUID().toString(), e.getMessage()));
	}
}