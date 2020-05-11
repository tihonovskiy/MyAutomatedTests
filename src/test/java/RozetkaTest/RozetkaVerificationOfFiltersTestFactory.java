package RozetkaTest;

import Rozetka.RozetkaCategoryListingPageFactory;
import Rozetka.RozetkaHomePageFactory;
import Rozetka.RozetkaProductListingPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaVerificationOfFiltersTestFactory {
    String baseUrl = "https://rozetka.com.ua/";
    String searchText = "samsung";
    String minPrice = "5000";
    String maxPrice = "15000";
    RozetkaHomePageFactory homePage;
    RozetkaCategoryListingPageFactory categoryListingPage;
    RozetkaProductListingPageFactory productListingPage;

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @Test
    public void addToFiltersAcerAndAsus() throws Exception {
        homePage = new RozetkaHomePageFactory(driver);
        categoryListingPage = new RozetkaCategoryListingPageFactory(driver);
        productListingPage = new RozetkaProductListingPageFactory(driver);
        homePage.performSearchRequest(searchText);
        categoryListingPage.waitProductFilterAndClick();
        productListingPage.addAcerFiler();
        productListingPage.addAsusFilter();
        List<WebElement> result = productListingPage.getAllProductsTitle();
        for (int i = 0; i < result.size(); i++) {
            String productName = result.get(i).getText().toLowerCase();
            if (!(productName.contains("samsung") || productName.contains("acer") || productName.contains("asus"))) {
                throw new Exception(productName + " this product is not related to the next brands: samsung, acer, asus");
            }
        }
    }

    @Test
    public void priceFilter() throws Exception {
        homePage = new RozetkaHomePageFactory(driver);
        categoryListingPage = new RozetkaCategoryListingPageFactory(driver);
        productListingPage = new RozetkaProductListingPageFactory(driver);
        homePage.performSearchRequest(searchText);
        categoryListingPage.waitProductFilterAndClick();
        productListingPage.updateFiltersMinAndMaxPrice(minPrice, maxPrice);
        List<WebElement> result = productListingPage.getAllProductPrice();
        for (int i = 0; i < result.size(); i++) {
            int priceInt = Integer.parseInt(result.get(i).getText().replaceAll(" ", ""));
            if (priceInt < Integer.parseInt(minPrice) || priceInt > Integer.parseInt(maxPrice)) {
                throw new Exception("price " + priceInt + " is not in range  from " + minPrice + " to " + maxPrice);
            }
        }
    }


    @Test
    public void addToFilterGalaxyA10() throws Exception {
        homePage = new RozetkaHomePageFactory(driver);
        categoryListingPage = new RozetkaCategoryListingPageFactory(driver);
        productListingPage = new RozetkaProductListingPageFactory(driver);
        homePage.performSearchRequest(searchText);
        categoryListingPage.waitProductFilterAndClick();
        productListingPage.addGalaxyA10Filter();
        List<WebElement> result = productListingPage.getAllProductsTitle();
        for (int i = 0; i < result.size(); i++) {
            if (!(result.get(i).getText().toLowerCase().contains("galaxy tab a 10.1"))) {
                throw new Exception(result.get(i).getText().toLowerCase() + " no Galaxy Tab A 10.1");
            }
        }
    }
}
