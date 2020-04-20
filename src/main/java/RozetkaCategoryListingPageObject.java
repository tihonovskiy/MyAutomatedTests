import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaCategoryListingPageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By nameCategoty = By.partialLinkText("Планшеты");

    public RozetkaCategoryListingPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public void waitProductFilterAndClick() {
        WebElement tablet = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(nameCategoty));
        tablet.click();
    }
}
