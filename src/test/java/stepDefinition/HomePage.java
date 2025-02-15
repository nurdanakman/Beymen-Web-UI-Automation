package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static pages.HomePage.*;

public class HomePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    @Given("User opens home page")
    public void userOpensHomePage(){
        logger.info("Opening the home page...");
        openHomePage();
    }

    @Then("Homepage is verified to be displayed")
    public void homepageIsVerifiedToBeDisplayed() {
        logger.info("Verifying the home page URL...");
        verifyHomePageUrl();
        logger.info("Verifying the home page title...");
        verifyHomePageTitle();
    }
}
