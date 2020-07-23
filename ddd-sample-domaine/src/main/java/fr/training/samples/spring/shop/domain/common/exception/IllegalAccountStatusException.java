package fr.training.samples.spring.shop.domain.common.exception;

public class IllegalAccountStatusException extends BusinessException {

    private static final long serialVersionUID = -5580602390032282239L;

    public IllegalAccountStatusException(final String message) {
        super(message);
    }

    public IllegalAccountStatusException() {
        super();
    }
}
