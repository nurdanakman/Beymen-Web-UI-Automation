package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utility.BrowserDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ShoppingCartPage extends BrowserDriver {

    public static String productQuantitySelectionId = "quantitySelect0-key-0";
    public static String productQuantityText = "aria-label";
    public static String productQuantityOptionCss = "option[value='%d']";
    public static String removeProductBasketXpath = "//span[contains(text(), 'Sil')]";
    public static String emptyBasketCss = "strong.m-empty__messageTitle";
    public static String basketProductTitleCss = ".m-basket__productInfo--link .m-basket__productInfoCategory";
    public static String basketProductDescriptionCss = ".m-basket__productInfo--link .m-basket__productInfoName";
    public static String basketProductLastPriceCss = ".m-basket__productPrice .priceBox__priceWrapper.-end";
    public static String basketProductOnlyOnePriceCss = ".m-basket__productPrice .priceBox__salePrice";

    public static void verifyProductCount(String productCount) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement selectElement = driver.findElement(By.id(productQuantitySelectionId));
        String ariaLabel = (String) js.executeScript(
                "return arguments[0].getAttribute(arguments[1]);",
                selectElement,
                productQuantityText
        );

        Assert.assertEquals("Product count does not match the expected value.", productCount, ariaLabel);
    }

    public static void clickQuantitySelection(){
        driver.findElement(By.id(productQuantitySelectionId)).click();
    }

    public static void clickQuantity(int quantity) {
        String locator = String.format(productQuantityOptionCss, quantity);
        driver.findElement(By.cssSelector(locator)).click();
    }

    public static void clickRemoveProductBasket(){
        driver.findElement(By.xpath(removeProductBasketXpath)).click();
    }

    public static void verifyEmptyBasket() {
        Assert.assertTrue(driver.findElement(By.cssSelector(emptyBasketCss)).isDisplayed());
    }

    private static String normalizePrice(String price) {
        if (price.equals("N/A")) {
            return price;
        }
        price = price.split(",")[0] + " TL";
        return price;
    }

    public static void verifyProductData() {
        String basketTitle = driver.findElement(By.cssSelector(basketProductTitleCss)).getText();
        String basketDescription = driver.findElement(By.cssSelector(basketProductDescriptionCss)).getText();

        String basketLastPrice = "N/A";
        if (!driver.findElements(By.cssSelector(basketProductLastPriceCss)).isEmpty()) {
            basketLastPrice = normalizePrice(driver.findElement(By.cssSelector(basketProductLastPriceCss)).getText());
        } else if (!driver.findElements(By.cssSelector(basketProductOnlyOnePriceCss)).isEmpty()) {
            basketLastPrice = normalizePrice(driver.findElement(By.cssSelector(basketProductOnlyOnePriceCss)).getText());
        }

        String expectedTitle = "";
        String expectedDescription = "";
        String expectedLastPrice = "";

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ProductDetails.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Title:")) {
                    expectedTitle = line.replace("Title: ", "").trim();
                } else if (line.startsWith("Description:")) {
                    expectedDescription = line.replace("Description: ", "").trim();
                } else if (line.startsWith("Last Price:")) {
                    expectedLastPrice = line.replace("Last Price: ", "").trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue("Title does not match!", expectedTitle.equalsIgnoreCase(basketTitle));
        Assert.assertTrue("Description does not match!", expectedDescription.equalsIgnoreCase(basketDescription));
        Assert.assertTrue("Last Price does not match!", expectedLastPrice.equalsIgnoreCase(basketLastPrice));
    }
}
