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