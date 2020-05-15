import Citrus.*;
import Steps.CitrusComparePageSteps;
import Steps.CitrusHomePageSteps;
import Steps.CitrusProductListPageSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusComparisonTest {
    CitrusHomePageSteps homePageSteps;
    CitrusProductListPageSteps productListPageSteps;
    CitrusComparePageSteps comparePageSteps;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("");
        homePageSteps = new CitrusHomePageSteps();
        productListPageSteps = new CitrusProductListPageSteps();
        comparePageSteps = new CitrusComparePageSteps();
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
    public void compareTwoPlusOneroducts() throws Exception {
        homePageSteps.clickOnLinkAcerInMenu("Ноутбуки, планшеты, МФУ");
        Thread.sleep(3000);
        String firstProductPrice = productListPageSteps.findLaptopByIdAndGetPrice(7);
        String secondProductPrice = productListPageSteps.findLaptopByIdAndGetPrice(8);
        String firstProductName = productListPageSteps.findLaptopByIdAndGetName(7);
        String secondProductName = productListPageSteps.findLaptopByIdAndGetName(8);
        productListPageSteps.clickButtonAddToComparisonLaptopById(7);
        Thread.sleep(1000);
        productListPageSteps.clickButtonAddToComparisonLaptopById(8);
        productListPageSteps.clickComparisonButton();
        comparePageSteps.addOneProduct();
        String thirdProductName = comparePageSteps.getNameNewProduct();
        String thirdProductPrice = comparePageSteps.getPriceNewProduct();
        comparePageSteps.clickAddNewProductButton();
        ElementsCollection elements = comparePageSteps.getAllProductInCompare();
        elements.get(0).shouldHave(Condition.text(secondProductPrice));
        elements.get(2).shouldHave(Condition.text(thirdProductPrice));
        elements.get(4).shouldHave(Condition.text(firstProductPrice));
        elements.get(0).shouldHave(Condition.text(secondProductName.substring(0, secondProductName.length()-10)));
        elements.get(2).shouldHave(Condition.text(thirdProductName.substring(0, thirdProductName.length()-10)));
        elements.get(4).shouldHave(Condition.text(firstProductName.substring(0, firstProductName.length()-10)));
    }
}
