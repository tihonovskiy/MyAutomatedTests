import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class RozetkaVerificationOfComparisonTestFactory {
    String baseUrl = "https://rozetka.com.ua/";
    int firstMonitorPriceInt = 0;
    String firstMonitorName = "";
    int secondMonitorPriceInt = 0;
    String secondMonitorName = "";

    RozetkaHomePageFactory homePage;
    RozetkaProductListingPageFactory productListingPage;
    RozetkaProductDetailePageFactory productDetailePage;
    RozetkaComparisonPageFactory comparisonPage;

    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 100);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        action = new Actions(driver);
    }

   @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @Test
    public void verificationOfComparisonTest() {
        homePage = new RozetkaHomePageFactory(driver);
        productListingPage = new RozetkaProductListingPageFactory(driver);
        productDetailePage = new RozetkaProductDetailePageFactory(driver);
        comparisonPage = new RozetkaComparisonPageFactory(driver);
        homePage.hoverOnLaptopAndPC(action);
        homePage.waitCategoryMonitorAndClick();
        List<WebElement> result = productListingPage.getAllProductPrice();
        for (int i = 0; i < result.size(); i++) {
            if (Integer.parseInt(result.get(i).getText().replaceAll(" ", "")) < 3000) {
                firstMonitorPriceInt = Integer.parseInt(result.get(i).getText().replaceAll(" ", ""));
                productListingPage.waitScrollClickOnProductImage(i+1);
                break;
            }
        }
        productDetailePage.waitScrollClickOnCompareButton();
        assertEquals(productDetailePage.getCompareCounter(), "1");
        firstMonitorName = productDetailePage.getProductName();
        driver.navigate().back();
        List<WebElement> results2 = productListingPage.getAllProductPrice();

        for (int i = 0; i < results2.size(); i++) {
            if (Integer.parseInt(results2.get(i).getText().replaceAll(" ", "")) < firstMonitorPriceInt) {
                secondMonitorPriceInt = Integer.parseInt(results2.get(i).getText().replaceAll(" ", ""));
                productListingPage.waitScrollClickOnProductImage(i+1);
                break;
            }
        }
        productDetailePage.waitScrollClickOnCompareButton();
        assertEquals(productDetailePage.getCompareCounter(), "2");
        secondMonitorName = productDetailePage.getProductName();
        productDetailePage.goToComparePage(action);
        comparisonPage.waitLoadingComparisonPage();
        List<WebElement> quantityMonitors = comparisonPage.getQuantityProducts();
        List<WebElement> priceMonitors = comparisonPage.getPriceProducts();
        List<WebElement> nameMonitors = comparisonPage.getNameProducts();
        String expectedPriceFirstMonitor = priceMonitors.get(0).getText().replaceAll("грн", "");
        expectedPriceFirstMonitor = expectedPriceFirstMonitor.replaceAll("\\s", "");
        String expectedPriceSecondMonitor = priceMonitors.get(1).getText().replaceAll("грн", "");
        expectedPriceSecondMonitor = expectedPriceSecondMonitor.replaceAll("\\s", "");
        assertEquals(quantityMonitors.size(), 2);
        assertEquals(nameMonitors.get(0).getText(), firstMonitorName);
        assertEquals(nameMonitors.get(1).getText(), secondMonitorName);
        assertEquals(Integer.parseInt(expectedPriceFirstMonitor.substring(0, expectedPriceFirstMonitor.length()-1)), firstMonitorPriceInt);
        assertEquals(Integer.parseInt(expectedPriceSecondMonitor.substring(0, expectedPriceSecondMonitor.length()-1)), secondMonitorPriceInt);
    }
}