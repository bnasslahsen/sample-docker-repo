package fr.training.samples.spring.shop.domain.common.exception;

public class NotZeroBalanceException extends BusinessException {

    private static final long serialVersionUID = -8407267565550793580L;

    public NotZeroBalanceException(final String message) {
        super(message);
    }

    public NotZeroBalanceException() {
        super();
    }
}
