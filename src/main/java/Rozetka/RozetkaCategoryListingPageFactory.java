package Rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaCategoryListingPageFactory {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @FindBy(partialLinkText = "Планшеты")
    WebElement nameCategoty;

    public RozetkaCategoryListingPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void waitProductFilterAndClick() {
        webDriverWait.until(ExpectedConditions.visibilityOf(nameCategoty));
        nameCategoty.click();
    }
}
