import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaProductListingPageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By acerFilter = By.cssSelector("a label[for=Acer]");
    By asusFilter = By.cssSelector("a label[for=Asus]");
    By allProductsTitle = By.cssSelector("span[class=goods-tile__title]");
    By minPriceFilter = By.cssSelector("input[formcontrolname=min]");
    By maxPriceFilter = By.cssSelector("input[formcontrolname=max]");
    By OkButtonUpdatePrice = By.cssSelector("fieldset > div > button");
    By allProductsPrice = By.cssSelector("p span[class=goods-tile__price-value]");
    By galaxyTabA10Filter = By.cssSelector("a label[for='Galaxy Tab A 10.1']");

    public RozetkaProductListingPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 20);
    }

    public void addAcerFiler() {
        WebElement acer = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(acerFilter));
        acer.click();
    }

    public void addAsusFilter() {
        WebElement asus = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(asusFilter));
        asus.click();
    }

    public List<WebElement> getAllProductsTitle() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(allProductsTitle));
        return webDriver.findElements(allProductsTitle);
    }

    public void updateFiltersMinAndMaxPrice(String valueMin, String valueMax) {
        WebElement priceMin = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(minPriceFilter));
        priceMin.clear();
        priceMin.sendKeys(valueMin);
        WebElement priceMax = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(maxPriceFilter));
        priceMax.clear();
        priceMax.sendKeys(valueMax);
        webDriver.findElement(OkButtonUpdatePrice).click();
    }

    public List<WebElement> getAllProductPrice() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(allProductsPrice));
        return webDriver.findElements(allProductsPrice);
    }

    public void addGalaxyA10Filter() {
        WebElement GalaxyA10Filter = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(galaxyTabA10Filter));
        GalaxyA10Filter.click();
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
