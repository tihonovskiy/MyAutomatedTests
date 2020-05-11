package Rozetka;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RozetkaComparisonPageFactory {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    @FindBy (css = "span[class='sprite g-rating-stars-i']")
    WebElement comparisonPage;
    @FindBy (css = "div[class='comparison-t-head-cell valigned-top']")
    List<WebElement> quantityProducts;
    @FindBy (xpath = "//div[@class='g-price-uah']")
    List<WebElement> pricesProducts;
    @FindBy (css = "a[class='comparison-g-title g-title novisited']")
    List<WebElement> namesProducts;

    public RozetkaComparisonPageFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 20);
        PageFactory.initElements(webDriver, this);
    }

    public void waitLoadingComparisonPage() {
        webDriverWait.until(ExpectedConditions.visibilityOf(comparisonPage));
    }

    public List<WebElement> getQuantityProducts() {
        return quantityProducts;
    }

    public List<WebElement> getPriceProducts() {
        return pricesProducts;
    }

    public List<WebElement> getNameProducts() {
        return namesProducts;
    }
}
