import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaComparisonPageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By comparisonPage = By.cssSelector("span[class='sprite g-rating-stars-i']");
    By quantityProducts = By.cssSelector("div[class='comparison-t-head-cell valigned-top']");
    By pricesProducts = By.xpath("//div[@class='g-price-uah']");
    By namesProducts = By.cssSelector("a[class='comparison-g-title g-title novisited']");

    public RozetkaComparisonPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 20);
    }

    public void waitLoadingComparisonPage() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(comparisonPage));
    }

    public List<WebElement> getQuantityProducts() {
        return webDriver.findElements(quantityProducts);
    }

    public List<WebElement> getPriceProducts() {
        return webDriver.findElements(pricesProducts);
    }

    public List<WebElement> getNameProducts() {
        return webDriver.findElements(namesProducts);
    }
}
