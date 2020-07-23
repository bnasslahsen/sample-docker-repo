package fr.training.samples.spring.shop.domain.common.exception;

public class NotSupportedException extends BusinessException {

	private static final long serialVersionUID = 2566093349888562731L;

	public NotSupportedException(final String message) {
		super(message);
	}

	public NotSupportedException() {
		super();
	}

}
