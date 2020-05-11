package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaProductDetailePageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By compareButton = By.cssSelector("app-compare-button > button");
    By compareCounter = By.xpath("//span[@class='header-actions__button-counter']");
    By productName = By.className("product__title");
    By compareProductButton = By.cssSelector("div a[class='header-actions__button header-actions__button_type_compare header-actions__button_state_active']");
    By comparisonLink = By.className("header-comparison__link");

    public RozetkaProductDetailePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 20);
    }

    public void waitScrollClickOnCompareButton() {
        WebElement compareButtonElem = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(compareButton));
        scrollToElement(compareButtonElem);
        compareButtonElem.click();
    }

    public String getCompareCounter() {
        WebElement compareCounterElem = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(compareCounter));
        scrollToElement(compareCounterElem);
        return compareCounterElem.getText();
    }

    public String getProductName() {
        return webDriver.findElement(productName).getText();
    }

    public void goToComparePage(Actions action) {
        WebElement comparison = webDriver.findElement(compareProductButton);
        action.moveToElement(comparison).perform();
        WebElement comparisonMonitors = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(comparisonLink));
        comparisonMonitors.click();
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
