package fr.training.samples.spring.shop.domain.common.exception;

public class InvalidAccountNumberException extends BusinessException {

	private static final long serialVersionUID = -7881771952438394017L;

	public InvalidAccountNumberException(final String message) {
		super(message);
	}

	public InvalidAccountNumberException() {
		super();
	}
}
