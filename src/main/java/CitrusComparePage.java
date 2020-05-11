import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CitrusComparePage {
    ElementsCollection productNames = $$(".ctrs-basket-product");
    SelenideElement popUp = $(".el-dialog.el-dialog--medium");
    SelenideElement totalPrice = $(".ctrs-basket-footer__new-price");
    List<SelenideElement> payButtonList = $$("li:nth-child(1) > i");
    SelenideElement closeButton = $("button:nth-child(3) > i");
    SelenideElement addButton = $("div.container > span > a");
    SelenideElement checkboxProduct = $("label:nth-child(1) > span.el-checkbox__input");
    SelenideElement productName = $("label:nth-child(1) > span.el-checkbox__label > div > p");
    SelenideElement productPrice = $("label:nth-child(1) > span.el-checkbox__label > div > div > span.price-new > span.price-number");
    SelenideElement submitButton = $(".el-button--primary");
    ElementsCollection products = $$(".catalog-item");

    public ElementsCollection getProductNamesPromoBasket() {
        return productNames;
    }

    public SelenideElement getBasket() {
        return popUp;
    }

    public SelenideElement getBasketTotalPrice() {
        return totalPrice;
    }

    public void clickPayButtonById(int id) {
        payButtonList.get(id).click();
    }

    public CitrusComparePage clickCloseButton() {
        closeButton.click();
        return this;
    }

    public void addOneProduct() {
        addButton.click();
        checkboxProduct.click();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void clickAddButton() {
        submitButton.click();
    }

    public ElementsCollection getProductsInCompare() {
        return products;
    }
}