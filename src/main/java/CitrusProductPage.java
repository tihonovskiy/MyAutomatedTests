import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CitrusProductPage {
    SelenideElement productPrice = $x("//div[@class='normal__prices']/div[@class='price']/span/span");
    SelenideElement payButton = $x("//div[@class='normal']/button[contains(text(),'Купить')]");
    SelenideElement totalPrice = $(".ctrs-basket-footer__new-price");
    SelenideElement popUp = $(".el-dialog.el-dialog--medium");
    ElementsCollection productNames = $$(".ctrs-basket-product");

    public String getProductPrice() {
        return productPrice.getText();
    }

    public CitrusProductPage clickAddToBasketButton() {
        payButton.click();
        return this;
    }

    public SelenideElement getBasketTotalPrice() {
        return totalPrice;
    }

    public SelenideElement getBasket() {
        return popUp;
    }

    public ElementsCollection getProductNamesPromoBasket() {
        return productNames;
    }
}
