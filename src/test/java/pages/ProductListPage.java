package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utility.BrowserDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;

public class ProductListPage extends BrowserDriver {

    private static final Logger logger = LogManager.getLogger(ProductListPage.class);

    public static String searchCss = "input[class='o-header__search--input']";
    public static String searchInputCss = "input[name='qSugesstion']";
    public static String searchCleanXpath = "//button[contains(@class, 'o-header__search--close')]";
    public static String productNameClass = "o-productCard__content--desc";
    public static String shoppingCartButtonCss = "m-productCard__stockCartIcon";
    public static String variationItemCss = ".m-variation__item";
    public static String addBasketId = "addBasket";
    public static String shoppingCartPageCss = "a[title='Sepetim']";
    public static String productTitleCss = ".m-productModal__detailInfo .m-productCard__title";
    public static String productDescriptionCss = ".m-productModal__detailInfo .m-productCard__desc";
    public static String productLastPriceCss = ".m-productModal__detailInfo .m-productCard__lastPrice";
    public static String productOnlyOnePriceCss = ".m-productModal__detailInfo .m-productCard__newPrice";

    public static void enterSearchText(String searchText) {
        driver.findElement(By.cssSelector(searchCss)).click();
        driver.findElement(By.cssSelector(searchInputCss)).sendKeys(searchText);
    }

    public static void clickSearchCleanButton() {
        driver.findElement(By.xpath(searchCleanXpath)).click();
    }

    private static boolean isSortedTextPresent(String searchText){
        List<WebElement> productDescriptions = driver.findElements(By.className(productNameClass));

        boolean isShortPresent = false;
        for (WebElement product : productDescriptions) {
            String text = product.getText().toLowerCase().trim();

            if (text.isEmpty()) {
                continue;
            }
            if (text.contains(searchText.toLowerCase())) {
                isShortPresent = true;
                break;
            }
        }
        return isShortPresent;
    }

    public static void verifyProductContains(String searchText)  {
        Assert.assertTrue(searchText + " not found in product descriptions!", isSortedTextPresent(searchText));
    }

    public static void verifyProductNotContains(String searchText)  {
        isSortedTextPresent(searchText);
        assertFalse(searchText + " not found in product descriptions!", isSortedTextPresent(searchText));
    }

    public static void clickEnterOnKeyboard() {
        WebElement activeElement = driver.switchTo().activeElement();
        activeElement.sendKeys(Keys.ENTER);
    }

    public static void randomClickShoppingCartButton()  {
        List<WebElement> shoppingCarts = driver.findElements(By.className(shoppingCartButtonCss));

        if (!shoppingCarts.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(shoppingCarts.size());
            System.out.println(shoppingCarts.get(randomIndex));
            shoppingCarts.get(randomIndex).click();
        } else {
            logger.info("No shopping cart buttons found.");
        }
    }

    public static void randomSelectEnabledVariation()  {
        List<WebElement> enabledItems = driver.findElements(By.cssSelector(variationItemCss + ":not([class*='-disabled'])"));

        if (!enabledItems.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(enabledItems.size());
            enabledItems.get(randomIndex).click();
        } else {
            logger.info("No enabled variation items found.");
        }
    }

    public static void addProductToShoppingCart()  {
        driver.findElement(By.id(addBasketId)).click();
    }

    public static void writeProductDetails()  {
        String title = driver.findElement(By.cssSelector(productTitleCss)).getText();
        String description = driver.findElement(By.cssSelector(productDescriptionCss)).getText();

        String lastPrice = "N/A";
        if (!driver.findElements(By.cssSelector(productLastPriceCss)).isEmpty()) {
            lastPrice = driver.findElement(By.cssSelector(productLastPriceCss)).getText();
        } else if (!driver.findElements(By.cssSelector(productOnlyOnePriceCss)).isEmpty()) {
            lastPrice = driver.findElement(By.cssSelector(productOnlyOnePriceCss)).getText();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/ProductDetails.txt"))) {
            writer.write("Title: " + title);
            writer.newLine();
            writer.write("Description: " + description);
            writer.newLine();
            writer.write("Last Price: " + lastPrice);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Failed to write product details to file.");
        }
    }

    public static void openShoppingCartPage() {
        driver.findElement(By.cssSelector(shoppingCartPageCss)).click();
    }
}
