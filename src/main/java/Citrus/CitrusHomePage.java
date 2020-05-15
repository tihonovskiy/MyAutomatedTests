package Citrus;

import Fragment.CitrusSearchFragment;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CitrusHomePage extends CitrusBasePage {
    CitrusSearchFragment citrusSearchFragment = new CitrusSearchFragment();

    SelenideElement appleLink = $x("//a[@href='/smartfony/brand-apple/']");
    SelenideElement samsungLink = $x("//a[@href='/smartfony/brand-samsung/']");
    SelenideElement xiaomiLink = $x("//a[@href='/smartfony/brand-xiaomi/']");
    SelenideElement googleSmartphoneLink = $x("//a[@href='/smartfony/brand-google/']");
    SelenideElement acerLaptopLink = $x("//a[@href='/noutbuki-i-ultrabuki/brand-acer/']");

    public CitrusHomePage hoverMenuLine(String lineName) {
        $x("//span[contains(text(),'" + lineName + "')]").hover();
        return this;
    }

    public CitrusHomePage clickLinkInMenuApple() {
        appleLink.click();
        return this;
    }

    public CitrusHomePage clickLinkInMenuSamsung() {
        samsungLink.click();
        return this;
    }

    public CitrusHomePage clickLinkInMenuXiaomi() {
        xiaomiLink.click();
        return this;
    }

    public CitrusHomePage clickLinkInMenuGoogle() {
        googleSmartphoneLink.click();
        return this;
    }

    public CitrusHomePage clickLinkInMenuLaptopAcer() {
        acerLaptopLink.click();
        return this;
    }

    public CitrusSearchFragment getCitrusSearchFragment() {
        return citrusSearchFragment;
    }
}
