package Citrus;

import Fragment.CitrusBasketFragment;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CitrusProductPage extends CitrusBasePage {
    SelenideElement productPrice = $x("//div[@class='normal__prices']/div[@class='price']/span/span");
    SelenideElement payButton = $x("//div[@class='normal']/button[contains(text(),'Купить')]");

    CitrusBasketFragment citrusBasketFragment = new CitrusBasketFragment();

    public String getProductPrice() {
        return productPrice.getText();
    }

    public CitrusProductPage clickAddToBasketButton() {
        payButton.click();
        return this;
    }

    public CitrusBasketFragment getCitrusBasketFragment() {
        return citrusBasketFragment;
    }
}
