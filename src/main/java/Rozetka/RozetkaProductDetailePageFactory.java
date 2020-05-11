package Rozetka;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RozetkaProductDetailePageFactory {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    @FindBy (css = "app-compare-button > button")
    WebElement compareButton;
    @FindBy (xpath = "//span[@class='header-actions__button-counter']")
    WebElement compareCounter;
    @FindBy (className = "product__title")
    WebElement productName;
    @FindBy (css = "div a[class='header-actions__button header-actions__button_type_compare header-actions__button_state_active']")
    WebElement compareProductButton;
    @FindBy (className = "header-comparison__link")
    WebElement comparisonLink;
    @FindBy (css = "app-product-buy-btn > app-buy-button > button")
    WebElement payButton;

    public RozetkaProductDetailePageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 20);
        PageFactory.initElements(webDriver, this);
    }

    public void waitScrollClickOnCompareButton() {
        webDriverWait.until(visibilityOf(payButton));
        WebElement element = webDriverWait.until(visibilityOf(compareButton));
        scrollToElement(element);
        element.click();
    }

    public String getCompareCounter() {
        webDriverWait.until(visibilityOf(compareCounter));
        scrollToElement(compareCounter);
        return compareCounter.getText();
    }

    public String getProductName() {
        WebElement element = webDriverWait.until(visibilityOf(productName));
        return element.getText();
    }

    public void goToComparePage(Actions action) {
        action.moveToElement(compareProductButton).perform();
        webDriverWait.until(visibilityOf(comparisonLink));
        comparisonLink.click();
    }

    private void scrollToElement(WebElement elem) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", elem);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
