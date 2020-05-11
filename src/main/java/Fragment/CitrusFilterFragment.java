package Fragment;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CitrusFilterFragment extends CitrusBasePage {
    SelenideElement inputMinPrice = $("div.roww > div:nth-child(1) > div > input");
    SelenideElement inputMaxPrice = $("div:nth-child(2) > div > input");
    ElementsCollection productNameList = $$(".product-card__name");
    ElementsCollection productPriceList = $$("div.prices__price > span.price");
    SelenideElement input16gbMemory = $("div:nth-child(4) > ul > li:nth-child(2) > label > span.el-checkbox__input");
    SelenideElement input32gbMemory = $("div:nth-child(4) > ul > li:nth-child(3) > label > span.el-checkbox__input");
    SelenideElement MetalBodyFilter = $("div:nth-child(14) > ul > li:nth-child(3) > label > span.el-checkbox__input");


    public void addMinMaxFilterPrice(String min, String max) {
        super.waitForPageToLoad();
        inputMinPrice.clear();
        inputMinPrice.setValue(min);
        inputMaxPrice.clear();
        inputMaxPrice.setValue(max);
        inputMaxPrice.sendKeys(Keys.ENTER);
    }

    public void addMemoryFilter() {
        super.waitForPageToLoad();
        input16gbMemory.click();
        super.waitForPageToLoad();
        input32gbMemory.click();
        super.waitForPageToLoad();
    }

    public void addBodyMaterialFilter() {
        MetalBodyFilter.click();
        super.waitForPageToLoad();
    }

    public Boolean verifyMinMaxPrice() throws Exception {
        super.waitForPageToLoad();
        for (int i = 1; i < productPriceList.size(); i++) {
            String temp = productPriceList.get(i).getText().replaceAll("\\s", "");
            if(Integer.parseInt(temp) < 3000 || Integer.parseInt(temp) > 15000) {
                throw new Exception(productPriceList.get(i).getText() + " price is <3000 || >15000");
            }
        }
        return true;
    }

    public Boolean verifyBrand(String brand) throws Exception {
        for (int i = 1; i < productNameList.size(); i++) {
            if(!(productNameList.get(i).getText().toLowerCase().contains(brand))) {
                throw new Exception(productNameList.get(i).getText() + " not samsung brand");
            }
        }
        return true;
    }

    public Boolean verifySmartphoneMemory() throws Exception {
        for (int i = 1; i < productNameList.size(); i++) {
            String lowerCaseName = productNameList.get(i).getText().toLowerCase();
            if(!(lowerCaseName.contains("xiaomi") && (lowerCaseName.contains("32gb") || lowerCaseName.contains("16gb")))) {
                throw new Exception(productNameList.get(i).getText() + " not xiaomi brand and memory 16/32gb");
            }
        }
        return true;
    }
}
