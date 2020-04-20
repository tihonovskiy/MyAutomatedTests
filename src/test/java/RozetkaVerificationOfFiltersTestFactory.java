import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaVerificationOfFiltersFactory {
    String baseUrl = "https://rozetka.com.ua/";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @Test
    public void addToFiltersAcerAndAsus() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        WebElement tablet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Планшеты")));
        tablet.click();
        WebElement acerFilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a label[for=Acer]")));
        acerFilter.click();
        driver.findElement(By.cssSelector("a label[for=Asus]")).click();
        List<WebElement> result = driver.findElements(By.cssSelector("span[class=goods-tile__title]"));
        for (int i = 0; i < result.size(); i++) {
            if (!(result.get(i).getText().toLowerCase().contains("samsung") || result.get(i).getText().toLowerCase().contains("acer") || result.get(i).getText().toLowerCase().contains("asus"))) {
                throw new Exception(result.get(i).getText().toLowerCase() + " no samsung or acer or asus");
            }
        }
    }

    @Test
    public void priceFilter() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        WebElement tablet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Планшеты")));
        tablet.click();
        WebElement priceMin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=min]")));
        priceMin.click();
        priceMin.clear();
        priceMin.sendKeys("5000");
        WebElement priceMax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=max]")));
        priceMax.click();
        priceMax.clear();
        priceMax.sendKeys("15000");
        driver.findElement(By.cssSelector("fieldset > div > button")).click();
        List<WebElement> searchResults = driver.findElements(By.cssSelector("p span[class=goods-tile__price-value]"));
        for (int i = 0; i < searchResults.size(); i++) {
            int temp = Integer.parseInt(searchResults.get(i).getText().replaceAll(" ", ""));
            if (temp < 5000 || temp > 15000) {
                throw new Exception(searchResults.get(i).getText() + "  <5000 || >15000");
            }
        }
    }

    @Test
    public void addToFilterGalaxyA10() throws Exception {
        driver.findElement(By.name("search")).sendKeys("samsung" + Keys.ENTER);
        WebElement tablet = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Планшеты")));
        tablet.click();
        WebElement GalaxyA10Filter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a label[for='Galaxy Tab A 10.1']")));
        GalaxyA10Filter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class=goods-tile__title]")));
        List<WebElement> result = driver.findElements(By.cssSelector("span[class=goods-tile__title]"));
        for (int i = 0; i < result.size(); i++) {
            if (!(result.get(i).getText().toLowerCase().contains("galaxy tab a 10.1"))) {
                throw new Exception(result.get(i).getText().toLowerCase() + " no Galaxy Tab A 10.1");
            }
        }
    }
}
