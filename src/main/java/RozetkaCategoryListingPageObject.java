import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaProductListingPageObject {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By nameProductFilter = By.partialLinkText("Планшеты");
    By 

    public RozetkaProductListingPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public void waitProductFilterAndClick() {
        WebElement tablet = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(nameProductFilter));
        tablet.click();
    }

    WebElement acerFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a label[for=Acer]")));
        acerFilter.click();
        driver.findElement(By.cssSelector("a label[for=Asus]")).click();

}
