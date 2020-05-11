package Rozetka;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomePageFactory {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @FindBy(name = "search")
    WebElement searchInput;
    @FindBy(linkText = "Ноутбуки и компьютеры")
    WebElement categoryLeptopPC;
    @FindBy(xpath = "//a[@href='https://hard.rozetka.com.ua/monitors/c80089/']")
    WebElement categoryMonitor;

    public RozetkaHomePageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void performSearchRequest(String text) {
        searchInput.sendKeys(text + Keys.ENTER);
    }

    public void hoverOnLaptopAndPC(Actions action) {
        action.moveToElement(categoryLeptopPC).perform();
    }

    public void waitCategoryMonitorAndClick() {
        WebElement monitors = webDriverWait.until(ExpectedConditions.visibilityOf(categoryMonitor));
        monitors.click();
    }
}
