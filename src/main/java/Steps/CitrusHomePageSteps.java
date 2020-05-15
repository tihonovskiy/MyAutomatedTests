package Steps;

import Citrus.CitrusHomePage;
import io.qameta.allure.Step;

public class CitrusHomePageSteps {
    CitrusHomePage homePage = new CitrusHomePage();

    @Step
    public void clickOnLinkAppleInMenu(String product) {
        homePage.hoverMenuLine(product)
                .clickLinkInMenuApple();
    }

    @Step
    public void clickOnLinkSamsungInMenu(String product) {
        homePage.hoverMenuLine(product)
                .clickLinkInMenuSamsung();
    }

    @Step
    public void clickOnLinkXioamiInMenu(String product) {
        homePage.hoverMenuLine(product)
                .clickLinkInMenuXiaomi();
    }

    @Step
    public void clickOnLinkGoogleInMenu(String product) {
        homePage.hoverMenuLine(product)
                .clickLinkInMenuGoogle();
    }

    @Step
    public void searchUsedSearchField(String searchText) {
        homePage.getCitrusSearchFragment().findProductUsingSearch(searchText);
    }

    @Step
    public void clickOnLinkAcerInMenu(String laptop) {
        homePage.hoverMenuLine(laptop)
                .clickLinkInMenuLaptopAcer();
    }
}
