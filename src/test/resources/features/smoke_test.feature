Feature: Smoke test suite for purchasing items from various online retailers.

  @smoke @smk1
  Scenario Outline: Smoke tests for electrical goods purchasing.

    Given the client wants to purchase <item>
    When they search for this <item> on the <site name> website
    And it is less than their <maximum budget>
    Then they will add it to their basket

    Examples:
      | item                                                   | site name                 | maximum budget |
      | The Doggfather CD album by Snoop Dogg                  | www.amazon.co.uk          | 10.00          |
      | A 4th generation Amazon Echo Dot                       | www.amazon.co.uk          | 30.00          |
      | A MacBook Pro 13 2020 Space Grey                       | www.currys.co.uk          | 950.00         |
      | iPhone 12 Pro 256GB Pacific Blue                       | www.apple.com             | 3000.12        |
