package Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaProductListingPageFactory {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    @FindBy(css = "a label[for=Acer]")
    WebElement acerFilter;
    @FindBy(css = "a label[for=Asus]")
    WebElement asusFilter;
    @FindBy(css = "span[class=goods-tile__title]")
    List<WebElement> allProductsTitle;
    @FindBy(css = "input[formcontrolname=min]")
    WebElement minPriceFilter;
    @FindBy(css = "input[formcontrolname=max]")
    WebElement maxPriceFilter;
    @FindBy(css = "fieldset > div > button")
    WebElement OkButtonUpdatePrice;
    @FindBy(css = "p span[class=goods-tile__price-value]")
    List<WebElement> allProductsPrice;
    @FindBy(css = "a label[for='Galaxy Tab A 10.1']")
    WebElement galaxyTabA10Filter;

    public RozetkaProductListingPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(webDriver, this);
    }

    public void addAcerFiler() {
        webDriverWait.until(ExpectedConditions.visibilityOf(acerFilter));
        acerFilter.click();
    }

    public void addAsusFilter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(asusFilter));
        asusFilter.click();
    }

    public List<WebElement> getAllProductsTitle() {
        return allProductsTitle;
    }

    public void updateFiltersMinAndMaxPrice(String valueMin, String valueMax) {
        webDriverWait.until(ExpectedConditions.visibilityOf(minPriceFilter));
        minPriceFilter.clear();
        minPriceFilter.sendKeys(valueMin);
        webDriverWait.until(ExpectedConditions.visibilityOf(maxPriceFilter));
        maxPriceFilter.clear();
        maxPriceFilter.sendKeys(valueMax);
        OkButtonUpdatePrice.click();
    }

    public List<WebElement> getAllProductPrice() {
        return allProductsPrice;
    }

    public void addGalaxyA10Filter() {
        webDriverWait.until(ExpectedConditions.visibilityOf(galaxyTabA10Filter));
        galaxyTabA10Filter.click();
    }

    public void waitScrollClickOnProductImage(int position) {
        WebElement firstMonitorsPosition = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + position + ") > app-goods-tile-default > div > div.goods-tile__inner")));
        scrollToElement(firstMonitorsPosition);
        WebElement firstMonitorsImg = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + position + ") > app-goods-tile-default > div > div.goods-tile__inner > a.goods-tile__picture > img.ng-lazyloaded")));
        firstMonitorsImg.click();
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
