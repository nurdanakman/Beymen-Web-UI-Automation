package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utility.ExcelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static pages.ProductListPage.*;

public class ProductListPage {

    private static final Logger logger = LogManager.getLogger(ProductListPage.class);

    @When("User enters the search text {int} row and {int} column")
    public void userEntersTheSearchText(int row, int column) throws Exception {
        String searchText = ExcelReader.getSearchTextFromExcel("src/test/resources/SearchTextInput.xls", row - 1, column - 1);
        logger.info("Entering search text from Excel: Row " + row + ", Column " + column + ", Search Text: " + searchText);
        enterSearchText(searchText);
    }

    @When("User clears the search text")
    public void userClearsTheSearchText() {
        logger.info("Clearing the search text...");
        clickSearchCleanButton();
    }

    @Then("User should see the filtered product list for {int} row and {int} column text")
    public void userShouldSeeTheFilteredListOfProducts(int row, int column) throws Exception {
        String searchText = ExcelReader.getSearchTextFromExcel("src/test/resources/SearchTextInput.xls", row - 1, column - 1);
        logger.info("Verifying that the filtered product list contains the search text: " + searchText);
        Thread.sleep(1000);
        verifyProductContains(searchText);
    }

    @Then("User shouldn't see the filtered product list for {int} row and {int} column text")
    public void userShouldNotSeeTheFilteredListOfProducts(int row, int column) throws Exception {
        String searchText = ExcelReader.getSearchTextFromExcel("src/test/resources/SearchTextInput.xls", row - 1, column - 1);
        logger.info("Verifying that the filtered product list does not contain the search text: " + searchText);
        verifyProductNotContains(searchText);
    }

    @And("User presses the enter key on the keyboard")
    public void userPressesEnterKeyOnKeyboard() {
        logger.info("User presses the Enter key on the keyboard...");
        clickEnterOnKeyboard();
    }

    @And("User selects a random product to add shopping cart")
    public void userSelectsARandomProductToAddShoppingCart() {
        logger.info("Selecting a random product to click to the shopping cart...");
        randomClickShoppingCartButton();
    }

    @And("User adds the product to the shopping cart")
    public void userAddsTheProductToTheShoppingCart() throws InterruptedException {
        logger.info("Select a random product variation to add shopping cart...");
        Thread.sleep(1000);
        randomSelectEnabledVariation();
        logger.info("Write product info to the txt file...");
        writeProductDetails();
        logger.info("Adding the selected product to the shopping cart...");
        Thread.sleep(1000);
        addProductToShoppingCart();
    }

    @And("User clicks shopping cart icon")
    public void userClicksShoppingCartIcon() throws InterruptedException {
        logger.info("Clicking the shopping cart icon...");
        Thread.sleep(1000);
        openShoppingCartPage();
    }
}
