package Steps;

import Citrus.CitrusProductPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class CitrusProductPageSteps {
    CitrusProductPage productPage = new CitrusProductPage();

    @Step
    public String getProductPrice() {
        return productPage.getProductPrice();
    }

    @Step
    public void clickAddToBasketButton() {
        productPage.clickAddToBasketButton();
    }

    @Step
    public void checkVisibleBasket() {
        productPage.getCitrusBasketFragment().getBasket().shouldBe(Condition.visible);
    }

    @Step
    public void checkQuintityProductsInBasket() {
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().shouldHaveSize(1);
    }

    @Step
    public void checkProductName(String name) {
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(name));
    }

    @Step
    public void checkProductPrice(String productPrice) {
        productPage.getCitrusBasketFragment().getProductNamesPromoBasket().get(0).shouldHave(Condition.text(productPrice));
    }

    @Step
    public void checkTotalPrice(String totalPrice) {
        productPage.getCitrusBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalPrice));
    }
}
