package Steps;

import Citrus.CitrusProductListPage;
import io.qameta.allure.Step;

public class CitrusProductListPageSteps {
    CitrusProductListPage productListPage = new CitrusProductListPage();

    @Step
    public void addFilterMinMaxProce(String min, String max) {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().addMinMaxFilterPrice(min, max);
    }

    @Step
    public void verifyBrandAndMinMaxPrice(String brand) throws Exception {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().verifyMinMaxPrice();
        productListPage.getCitrusFilterFragment().verifyBrand(brand);
    }

    @Step
    public void addFilterMemory() {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().addMemoryFilter();
    }

    @Step
    public void verifyMemory() throws Exception {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().verifySmartphoneMemory();
    }

    @Step
    public void addFilterMaterial() {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().addBodyMaterialFilter();
    }

    @Step
    public void verifyBrandAndBody(String brand) throws Exception {
        productListPage.waitForPageToLoad();
        productListPage.getCitrusFilterFragment().verifyBrand(brand);
    }

    @Step
    public String findLaptopByIdAndGetPrice(int id) {
        productListPage.waitForPageToLoad();
        return productListPage.findLaptopByIdAndGetPrice(id);
    }

    @Step
    public String findLaptopByIdAndGetName(int id) {
        return productListPage.findLaptopByIdAndGetName(id);
    }

    @Step
    public void clickButtonAddToComparisonLaptopById(int id) {
        productListPage.waitForPageToLoad();
        productListPage.clickButtonAddToComparisonLaptop(id);
    }

    @Step
    public void clickComparisonButton() {
        productListPage.waitForPageToLoad();
        productListPage.clickComparisonButton();
    }

    @Step
    public void findProductByNameAndClick(String searcText) {
        productListPage.findProductByNameAndClick(searcText);
    }
}
