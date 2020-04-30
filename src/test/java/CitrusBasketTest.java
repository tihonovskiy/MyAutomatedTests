import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketTest {
    CitrusHomePage homePage;
    CitrusProductListPage productListPage;
    CitrusProductPage productPage;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        homePage = new CitrusHomePage();
        productListPage = new CitrusProductListPage();
        productPage = new CitrusProductPage();
    }

    @BeforeMethod
    public void navigation() {
        open("");
    }

    @Test
    public void AddToBasketUsingProductPage() {
        homePage.hoverMenuLine("Смартфоны")
                .clickLinkInMenuApple();
        productListPage.findProductByNameAndClick("Apple iPhone 11 128Gb Black (MWM02)");
        String productPrice = productPage.getProductPrice();
        productPage.clickAddToBasketButton();

        productPage.getBasket().shouldBe(Condition.visible);
        productPage.getProductNamesPromoBasket().shouldHaveSize(1);
        productPage.getProductNamesPromoBasket().get(0).shouldHave(Condition.text("Apple iPhone 11 128Gb Black"));
        productPage.getBasketTotalPrice().shouldHave(Condition.text(productPrice));

        System.out.println($(".ctrs-number-spinner.ctrs-basket-item__count").getValue());
    }
}
