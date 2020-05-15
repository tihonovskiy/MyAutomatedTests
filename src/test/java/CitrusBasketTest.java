import Citrus.*;
import Steps.CitrusHomePageSteps;
import Steps.CitrusProductListPageSteps;
import Steps.CitrusProductPageSteps;
import com.codeborne.selenide.Configuration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class CitrusBasketTest {
    CitrusHomePageSteps homePageSteps;
    CitrusProductListPageSteps productListPageSteps;
    CitrusProductPageSteps productPageSteps;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("");
        homePageSteps = new CitrusHomePageSteps();
        productListPageSteps = new CitrusProductListPageSteps();
        productPageSteps = new CitrusProductPageSteps();
    }

    @BeforeMethod
    public void clearCart() {
        clearBrowserLocalStorage();
        open("");
    }

    @AfterMethod
    public void screenshots(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            CitrusBasePage.screenshot();
        }
    }

    @Test
    public void addToBasketUsingProductPage() {
        homePageSteps.clickOnLinkAppleInMenu("Смартфоны");
        productListPageSteps.findProductByNameAndClick("Apple iPhone 11 128Gb Black (MWM02)");
        String productPrice = productPageSteps.getProductPrice();
        productPageSteps.clickAddToBasketButton();

        productPageSteps.checkVisibleBasket();
        productPageSteps.checkQuintityProductsInBasket();
        productPageSteps.checkProductName("Apple iPhone 11 128Gb Black");
        productPageSteps.checkProductPrice(productPrice);
        productPageSteps.checkTotalPrice(productPrice);
    }
}
