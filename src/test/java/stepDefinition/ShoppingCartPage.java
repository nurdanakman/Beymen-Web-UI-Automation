package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static pages.ShoppingCartPage.*;

public class ShoppingCartPage {

    private static final Logger logger = LogManager.getLogger(ShoppingCartPage.class);

    @And("Product details should match the product list")
    public void productDetailsShouldMatchTheProductList() {
        logger.info("Verifying product details match the product list...");
        verifyProductData();
    }

    @When("User changes the product quantity to {int}")
    public void userChangesTheProductQuantityTo(int quantity) throws InterruptedException {
        logger.info("Changing product quantity to: " + quantity);
        Thread.sleep(1000);
        clickQuantitySelection();
        Thread.sleep(1000);
        clickQuantity(quantity);
    }

    @Then("Product count should be {string}")
    public void productCountShouldBe(String productCount) throws InterruptedException {
        logger.info("Verifying product count: " + productCount);
        Thread.sleep(1000);
        verifyProductCount(productCount);
    }

    @And("User deletes products from the shopping cart")
    public void userDeletesProductsFromTheShoppingCart() throws InterruptedException {
        logger.info("Removing product from the shopping cart...");
        Thread.sleep(1000);
        clickRemoveProductBasket();
    }

    @Then("Shopping cart should be empty")
    public void shoppingCartShouldBeEmpty() {
        logger.info("Verifying that the shopping cart is empty...");
        verifyEmptyBasket();
    }
}
