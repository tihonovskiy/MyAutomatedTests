import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusComparisonTest {
    CitrusHomePage homePage;
    CitrusProductListPage productListPage;
    CitrusProductPage productPage;
    CitrusComparePage comparePage;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("");
        homePage = new CitrusHomePage();
        productListPage = new CitrusProductListPage();
        productPage = new CitrusProductPage();
        comparePage = new CitrusComparePage();
    }

    @Test
    public void compareTwoPlusOneroducts() {
        homePage.hoverMenuLine("Ноутбуки, планшеты, МФУ")
                .clickLinkInMenuLaptopAcer();
        productListPage.waitForPageToLoad();
        String firstProductPrice = productListPage.findLaptopByIdAndGetPrice(7);
        String secondProductPrice = productListPage.findLaptopByIdAndGetPrice(8);
        String firstProductName = productListPage.findLaptopByIdAndGetName(7);
        String secondProductName = productListPage.findLaptopByIdAndGetName(8);
        productListPage.clickButtonAddToComparisonLaptop(7);
        productListPage.clickButtonAddToComparisonLaptop(8);
        productListPage.clickComparisonButton();
        comparePage.addOneProduct();
        String thirdProductName = comparePage.getProductName();
        String thirdProductPrice = comparePage.getProductPrice();
        comparePage.clickAddButton();
        ElementsCollection elements = comparePage.getProductsInCompare();
        elements.get(0).shouldHave(Condition.text(secondProductPrice));
        elements.get(2).shouldHave(Condition.text(thirdProductPrice));
        elements.get(4).shouldHave(Condition.text(firstProductPrice));
        elements.get(0).shouldHave(Condition.text(secondProductName.substring(0, secondProductName.length()-10)));
        elements.get(2).shouldHave(Condition.text(thirdProductName.substring(0, thirdProductName.length()-10)));
        elements.get(4).shouldHave(Condition.text(firstProductName.substring(0, firstProductName.length()-10)));
    }
}
