Feature: Account operations

  Scenario: Account Withdrawals is in credit
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And a balance of 200
    When I withdraw 100 "EUR"
    Then Account "FR7630004729017644105573683" has balance 100

  Scenario: Account Withdrawals fails with Insufficient funds
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And a balance of 200
    When I withdraw 250 "EUR"
    Then it Fails with InsufficientFundsException
    And Account "FR7630004729017644105573683" has balance 200

  Scenario: Account Withdrawals below overdraft ceiling success with negative balance
    Given An account "FR7630004729017644105573683" with overdraft of 100 "EUR"
    And a balance of 200
    When I withdraw 250 "EUR"
    And Account "FR7630004729017644105573683" has balance -50

  Scenario: Account deposit is in credit
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And a balance of 100
    When I deposit 100 "EUR"
    Then Account "FR7630004729017644105573683" has balance 200

  Scenario: Account deposit in USD fails for account in EUR
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And a balance of 100
    When I deposit 100 "USD"
    Then it Fails with MonetaryException
    And Account "FR7630004729017644105573683" has balance 100

  Scenario: Account Withdrawals on suspended account fails with Invalid Account Status
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And a balance of 100
    And account is suspended
    When I withdraw 100 "EUR"
    Then it Fails with InvalidAccountStatusException
    And Account "FR7630004729017644105573683" has balance 100

  Scenario: Account Deposit on closed account fails with Invalid Account Status
    Given An account "FR7630004729017644105573683" with Currency is "EUR"
    And account is closed
    When I deposit 100 "EUR"
    Then it Fails with InvalidAccountStatusException
    And Account "FR7630004729017644105573683" has balance 0