package Fragment;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketFragment {
    SelenideElement totalPrice = $(".ctrs-basket-footer__new-price");
    SelenideElement popUp = $(".el-dialog.el-dialog--medium");
    ElementsCollection productNames = $$(".ctrs-basket-product");
    SelenideElement closeButton = $x("//button[@class='el-dialog__headerbtn']");


    public SelenideElement getBasketTotalPrice() {
        return totalPrice;
    }

    public SelenideElement getBasket() {
        return popUp;
    }

    public ElementsCollection getProductNamesPromoBasket() {
        return productNames;
    }

    public void closeBasket() {
        closeButton.click();
    }
}
