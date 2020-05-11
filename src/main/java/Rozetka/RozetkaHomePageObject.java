package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomePageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    By searchInput = By.name("search");
    By categoryLeptopPC = By.linkText("Ноутбуки и компьютеры");
    By categoryMonitor = By.xpath("//a[@href='https://hard.rozetka.com.ua/monitors/c80089/']");

    public RozetkaHomePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public void performSearchRequest(String text) {
        webDriver.findElement(searchInput).sendKeys(text + Keys.ENTER);
    }

    public void hoverOnLaptopAndPC(Actions action) {
        WebElement notebook = webDriver.findElement(categoryLeptopPC);
        action.moveToElement(notebook).perform();
    }

    public void waitCategoryMonitorAndClick() {
        WebElement monitors = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(categoryMonitor));
        monitors.click();
    }
}
