package fr.training.samples.spring.shop.domain.common.vo;

import fr.training.samples.spring.shop.domain.common.exception.IllegalAccountStatusException;

/**
 * Account Status value object.
 */
public enum AccountStatus {

    ACTIVE, SUSPENDED(ACTIVE), CLOSED(ACTIVE, SUSPENDED);

    private AccountStatus[] previousStates;

    private AccountStatus(final AccountStatus... state) {
        previousStates = state;
    }

    /**
     * Transition statusFS validation.
     *
     * @param state
     *            the new Account status
     * @return new state if transition is valid
     * @throws IllegalStateException
     *             if transition isnt valid
     */
    public AccountStatus transition(final AccountStatus state) {
        for (final AccountStatus tmp : state.previousStates) {
            if (this == tmp) {
                return state;
            }
        }
        throw new IllegalAccountStatusException("Illegal transition: " + this + " cannot be changed to " + state);
    }

}
