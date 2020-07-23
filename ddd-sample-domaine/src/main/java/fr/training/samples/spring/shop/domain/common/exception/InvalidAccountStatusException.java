package fr.training.samples.spring.shop.domain.common.exception;

public class InvalidAccountStatusException extends BusinessException {

    private static final long serialVersionUID = 3785250769222204973L;

    public InvalidAccountStatusException(final String message) {
        super(message);
    }

    public InvalidAccountStatusException() {
        super();
    }
}
