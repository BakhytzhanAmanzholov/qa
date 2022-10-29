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

  # DRAFT
  Scenario Outline: Compare products
  Check if comparison works

    When open browser
    Then go to main
    When close windows
    When go to catalog
    Then click to link to gadgets
    Then add to compare gadgets
    Then comparison should be as "<expected>"
    Examples:
      | expected       |
      | Цена по прайсу |

  # DAURENBEK BEKNAZAROV
  Scenario Outline: Login to website
  Simple login to website

    When open browser
    Then go to main
    When close windows
    When go to login page
    Then set username as "<login>" and password as "<password>"
    Then username should be as "<username>"

    Examples:
      | login                  | password    | username            |
      | janjan.06.kz@gmail.com | 5nFy2UWCJ2b | Zhandos Kudaybergen |
      | janjan.05.kz@gmail.com | 5nFy2UWCJ2b | Zhandos Kudaybergen |

  #Temirlan Myrzagaliev
  #noinspection NonAsciiCharacters
  Scenario Outline: City Change Test
  An attempt to change the city to Astana

    When open browser
    Then go to main
    When close windows
    Then Click on the name of the city
    Then Choose the city "<Selected city>"
    Then Name of city should be "<Expected city>"
    Examples:
      | Selected city | Expected city|
      | Астана        | Астана       |
      | Павлодар      | Павлодар     |


