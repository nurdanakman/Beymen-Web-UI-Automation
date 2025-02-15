Feature:  Shopping Cart Behavior

    Background:
        Given User opens home page
        And User enters the search text 1 row and 2 column
        And User presses the enter key on the keyboard
        And User selects a random product to add shopping cart
        And User adds the product to the shopping cart
        And User clicks shopping cart icon

    Scenario: Add a random product to the shopping cart
        Then Product count should be '1 adet'
        And Product details should match the product list

    Scenario: Increase product count in the shopping cart
        When User changes the product quantity to 2
        Then Product count should be '2 adet'

    Scenario: Delete products from the shopping cart
        And User deletes products from the shopping cart
        Then Shopping cart should be empty
