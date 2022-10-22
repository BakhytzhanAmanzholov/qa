@shop.kz
Feature: Shop.kz

  In order to check if site is work

  Scenario: Find product in query
  Check working find products in query

    When open browser
    Then go to main
    When close windows
    Then click to query
    When send request
    Then text of title should be
    """
    Результаты поиска по запросу "Acer nitro 5"
    """

  Scenario Outline: Filter products in query
  Check if filter work

    When open browser
    Then go to filter
    When close windows
    When set min price as "<min>" and max price as "<max>"
    Then set filter
    Then text of filter title should be as "<expected>"

    Examples:
      | min   | max    | expected |
      | 40000 | 120000 | 40000    |
      | 30000 | 150000 | 30000    |
