import Citrus.*;
import Steps.CitrusHomePageSteps;
import Steps.CitrusProductListPageSteps;
import com.codeborne.selenide.Configuration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class CitrusFilterTest {
    CitrusHomePageSteps homePageSteps;
    CitrusProductListPageSteps productListPageSteps;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("");
        homePageSteps = new CitrusHomePageSteps();
        productListPageSteps = new CitrusProductListPageSteps();
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
    public void usePriceFilter() throws Exception {
        homePageSteps.clickOnLinkSamsungInMenu("Смартфоны");
        productListPageSteps.addFilterMinMaxProce("3000", "15000");
        productListPageSteps.verifyBrandAndMinMaxPrice("samsung");
    }

    @Test
    public void useMemorySizeFilter() throws Exception {
        homePageSteps.clickOnLinkXioamiInMenu("Смартфоны");
        productListPageSteps.addFilterMemory();
        productListPageSteps.verifyMemory();
    }

    @Test
    public void useBodyMaterialFilter() throws Exception {
        homePageSteps.clickOnLinkGoogleInMenu("Смартфоны");
        productListPageSteps.addFilterMaterial();
        productListPageSteps.verifyBrandAndBody("google");
    }
}
