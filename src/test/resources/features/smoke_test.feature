Feature: Smoke test suite for purchasing items from various online retailers.

  @smoke @smk1
  Scenario Outline: Smoke tests for electrical goods purchasing.

    Given the customer goes shopping on "<site name>"
    When they search for item: "<item name>" on "<site name>"
    And if it is less than their maximum budget of <amount>

    Examples:
      | item name                                                                 | site name                 | amount         |
      | Tha Doggfather: The Times, Trials, and Hardcore Truths of Snoop Dogg      | www.amazon.co.uk          | 20.00          |
      | Phoenix Project: A Novel About It, Devops, And Helping Your Business Win  | www.amazon.co.uk          | 10.00          |
      | An item of your own choice                                                | www.currys.co.uk          | 950.00         |

