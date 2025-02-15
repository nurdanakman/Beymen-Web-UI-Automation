package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserDriver;

import java.time.Duration;

public class HomePage extends BrowserDriver {

    public static String homePageUrl = "https://www.beymen.com/tr";
    public static String acceptCookiesButtonXpath = "//button[@id='onetrust-accept-btn-handler']";
    public static String closeIconCss = "button.o-modal__closeButton.bwi-close";
    public static String mainPageTitle = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";

    public static void openHomePage() {
        driver.get(homePageUrl);
        acceptCookies();
        closeModal();
    }

    private static void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptCookiesButtonXpath)));
        acceptButton.click();
    }

    private static void closeModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(closeIconCss)));
        closeButton.click();
    }

    public static void verifyHomePageUrl() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL does not match!", homePageUrl, currentUrl);
    }

    public static void verifyHomePageTitle() {
        String currentTitle = driver.getTitle();
        Assert.assertEquals("Page title does not match!", mainPageTitle, currentTitle);
    }

}
