package fr.training.samples.spring.shop.domain.common.exception;

public class InsufficientFundsException extends BusinessException {

	private static final long serialVersionUID = -6954419602515651819L;

	public InsufficientFundsException(final String message) {
		super(message);
	}

	public InsufficientFundsException() {
		super();
	}

}
