Feature: Smoke test suite for purchasing items from various online retailers.

  @smoke @smk1
  Scenario Outline: Smoke tests for electrical goods purchasing.

    Given the client opens "<site name>"
    When they search for item: "<item name>" on "<site name>"
    And it is less than their maximum budget of <amount>
#    Then they will add it to their basket

    Examples:
      | item name                                                            | site name                 | amount         |
      | The Doggfather: The Times, Trials and Hardcore Truths of Snoop Dogg  | www.amazon.co.uk          | 20.00          |
      | Echo Dot (4th generation) - Charcoal with a Fire TV Stick 4K         | www.amazon.co.uk          | 30.00          |
#      | MacBook Pro 13 2020 Space Grey                                       | www.currys.co.uk          | 950.00         |
#      | iPhone 12 Pro 256GB Pacific Blue                                     | www.apple.com             | 3000.12        |
