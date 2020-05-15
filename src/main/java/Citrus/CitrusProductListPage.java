package Citrus;

import Fragment.CitrusFilterFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CitrusProductListPage extends CitrusBasePage {
    List<SelenideElement> productList = $$x("//div[@class='catalog-item product-card__']");
    SelenideElement comparisonButton = $("div.user-actions__compare.tips-parent > i");
    ElementsCollection productNameList = $$(".product-card__name");
    ElementsCollection productPriceList = $$("div.prices__price > span.price");
    ElementsCollection materialBodyList = $$("product-card product-card--mini");

    CitrusFilterFragment citrusFilterFragment = new CitrusFilterFragment();

    public CitrusProductListPage findProductByNameAndClick(String productName) {
        $x("//a[@title='" + productName + "']").click();
        return  this;
    }

    public String findProductByIdAndGetPrice(int id) {
        return $("div:nth-child(" + id + ") > div > div > div.short-itm-desc > div.price-itm > div > div.base-price > span").getText();
    }

    public CitrusProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public int findProductByNameAndGetId(String productName) {
        for (int i = 1; i < productList.size(); i++) {
            if ($("div:nth-child(" + i + ") > div > div > div.short-itm-desc > a > div > h5").getText().equals(productName)) {
                return i;
            }
        }
        return 0;
    }

    public CitrusProductListPage clickPayButton(int id) {
        $("div:nth-child(" + id + ") > div > div > div.short-itm-desc > div.itm-footer-desc > ul > li:nth-child(1) > i").click();
        return this;
    }

    public String findProductByIdAndGetName(int id) {
        return $("div:nth-child(" + id + ") > div > div > div.short-itm-desc > a > div > h5").getText();
    }

    public CitrusProductListPage clickButtonAddToComparison(int id) {
        $("div:nth-child(" + id + ") > div > div > div.short-itm-desc > div.itm-footer-desc > ul > li:nth-child(2) > i").click();
        return this;
    }

    public void clickComparisonButton() {
        comparisonButton.click();
    }

    public ElementsCollection getBodyMaterialProduct() {
        return materialBodyList;
    }

    public String findLaptopByIdAndGetPrice(int id) {
        return $("div:nth-child(" + id + ") > div > div.product-card__body > div.product-card__prices > div.prices__price > span.price").getText();
    }

    public String findLaptopByIdAndGetName(int id) {
        return $("div:nth-child(" + id + ") > div > div.product-card__body > div:nth-child(3) > div.product-card__name > a > span").getText();
    }

    public void clickButtonAddToComparisonLaptop(int id) {
        $("div:nth-child(" + id + ") > div > div.product-card__footer > div.product-card__actions").hover();
        $("div:nth-child(" + id + ") > div > div.product-card__footer > div.product-card__actions > button.product-card__to-compare").click();
    }

    public String getTotalPrice(String price1, String price2) {
        Integer expectedTotalPriceInt = (Integer.parseInt(price1.replaceAll("\\s", "")) + Integer.parseInt(price2.replaceAll("\\s", "")));
        StringBuilder sb = new StringBuilder(expectedTotalPriceInt.toString());
        sb.insert(2, " ");
        return sb.toString();
    }

    public CitrusFilterFragment getCitrusFilterFragment() {
        return citrusFilterFragment;
    }
}