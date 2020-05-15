package Steps;

import Citrus.CitrusComparePage;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

public class CitrusComparePageSteps {
    CitrusComparePage comparePage = new CitrusComparePage();

    @Step
    public void addOneProduct() {
        comparePage.waitForPageToLoad();
        comparePage.addOneProduct();
    }

    @Step
    public String getNameNewProduct() {
        comparePage.waitForPageToLoad();
        return comparePage.getProductName();
    }

    @Step
    public String getPriceNewProduct() {
        comparePage.waitForPageToLoad();
        return comparePage.getProductPrice();
    }

    @Step
    public void clickAddNewProductButton() {
        comparePage.clickAddButton();
        comparePage.waitForPageToLoad();
    }

    @Step
    public ElementsCollection getAllProductInCompare() {
        return comparePage.getProductsInCompare();
    }
}
