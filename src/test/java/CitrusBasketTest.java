import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketTest {
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
    public void addToBasketUsingProductPage() {
        homePage.hoverMenuLine("Смартфоны")
                .clickLinkInMenuApple();
        productListPage.findProductByNameAndClick("Apple iPhone 11 128Gb Black (MWM02)");
        String productPrice = productPage.getProductPrice();
        productPage.clickAddToBasketButton();

        productPage.getCitrusBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().shouldHaveSize(1);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text("Apple iPhone 11 128Gb Black"));
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(productPrice));
        productPage.getCitrusBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addToBasketUsingSearch() {
        homePage.getCitrusSearchFragment().findProductUsingSearch("Apple iPhone 11");
        productListPage.waitForPageToLoad();
        int id = productListPage.findProductByNameAndGetId("Apple iPhone 11 128Gb Black (MWM02)");
        String productPrice = productListPage.findProductByIdAndGetPrice(id);
        productListPage.clickPayButton(id);

        productPage.getCitrusBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().shouldHaveSize(1);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text("Apple iPhone 11 128Gb Black (MWM02)"));
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(productPrice));
        productPage.getCitrusBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addTwoProductsToBasket() {
        homePage.getCitrusSearchFragment().findProductUsingSearch("Apple iPhone");
        productListPage.waitForPageToLoad();
        String firstProductPrice = productListPage.findProductByIdAndGetPrice(1);
        String secondProductPrice = productListPage.findProductByIdAndGetPrice(2);
        String firstProductName = productListPage.findProductByIdAndGetName(1);
        String secondProductName = productListPage.findProductByIdAndGetName(2);
        String expectedTotalPrice = productListPage.getTotalPrice(firstProductPrice, secondProductPrice);
        productListPage.clickPayButton(1);
        productPage.getCitrusBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getCitrusBasketFragment().closeBasket();
        productListPage.clickPayButton(2);
        productPage.getCitrusBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().shouldHaveSize(2);
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(firstProductName));
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(1).shouldHave(Condition.text(secondProductName));
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(firstProductPrice));
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(1).shouldHave(Condition.text(secondProductPrice));
        productPage.getCitrusBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(expectedTotalPrice));
    }

    @Test
    public void addTwoProductsToBasketFromComparison() {
        homePage.getCitrusSearchFragment().findProductUsingSearch("Apple iPhone");
        productListPage.waitForPageToLoad();
        String firstProductPrice = productListPage.findProductByIdAndGetPrice(1);
        String secondProductPrice = productListPage.findProductByIdAndGetPrice(2);
        String firstProductName = productListPage.findProductByIdAndGetName(1);
        String secondProductName = productListPage.findProductByIdAndGetName(2);
        String expectedTotalPrice = productListPage.getTotalPrice(firstProductPrice, secondProductPrice);
        productListPage.clickButtonAddToComparison(1);
        productListPage.clickButtonAddToComparison(2);
        productListPage.clickComparisonButton();
        comparePage.clickPayButtonById(1);
        comparePage.clickCloseButton();
        comparePage.clickPayButtonById(3);
        comparePage.getBasket().shouldBe(Condition.visible);
        comparePage.getProductNamesPromoBasket().shouldHaveSize(2);
        comparePage.getProductNamesPromoBasket().get(1).shouldHave(Condition.text(firstProductName));
        comparePage.getProductNamesPromoBasket().get(0).shouldHave(Condition.text(secondProductName));
        comparePage.getProductNamesPromoBasket().get(1).shouldHave(Condition.text(firstProductPrice));
        comparePage.getProductNamesPromoBasket().get(0).shouldHave(Condition.text(secondProductPrice));
        comparePage.getBasketTotalPrice().shouldHave(Condition.text(expectedTotalPrice));
    }
}
