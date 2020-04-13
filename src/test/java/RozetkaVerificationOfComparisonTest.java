import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class RozetkaVerificationOfComparisonTest {
    String baseUrl = "https://rozetka.com.ua/";
    int firstMonitorPriceInt = 0;
    int firstMonitorCounter = 0;
    String firstMonitorPriceString = "";
    String firstMonitorName = "";

    int secondMonitorPriceInt = 0;
    int secondMonitorCounter = 0;
    String secondMonitorPriceString = "";
    String secondMonitorName = "";

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
    public void verificationOfComparisonTest() {
        Actions action = new Actions(driver);
        WebElement notebook = driver.findElement(By.linkText("Ноутбуки и компьютеры"));
        action.moveToElement(notebook).perform();
        WebElement monitors = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://hard.rozetka.com.ua/monitors/c80089/']")));
        monitors.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p span[class=goods-tile__price-value]")));
        List<WebElement> searchResults = driver.findElements(By.cssSelector("p span[class=goods-tile__price-value]"));
        for (int i = 0; i < searchResults.size(); i++) {
            int temp = Integer.parseInt(searchResults.get(i).getText().replaceAll(" ", ""));
            if (temp < 3000) {
                firstMonitorPriceString = searchResults.get(i).getText();
                firstMonitorPriceInt = temp;
                firstMonitorCounter = i + 1;
                break;
            }
        }
        WebElement firstMonitorsPosition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + firstMonitorCounter + ") > app-goods-tile-default > div > div.goods-tile__inner")));
        scrollToElement(firstMonitorsPosition);
        WebElement firstMonitorsImg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + firstMonitorCounter + ") > app-goods-tile-default > div > div.goods-tile__inner > a.goods-tile__picture > img.ng-lazyloaded")));
        firstMonitorsImg.click();

        WebElement compareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-compare-button > button")));
        scrollToElement(compareButton);
        compareButton.click();

        WebElement compareCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='header-actions__button-counter']")));
        scrollToElement(compareCounter);
        assertEquals(compareCounter.getText(), "1");

        firstMonitorName = driver.findElement(By.className("product__title")).getText();
        driver.navigate().back();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p span[class=goods-tile__price-value]")));
        List<WebElement> searchResults2 = driver.findElements(By.cssSelector("p span[class=goods-tile__price-value]"));

        for (int i = 0; i < searchResults2.size(); i++) {
            int temp = Integer.parseInt(searchResults2.get(i).getText().replaceAll(" ", ""));
            if (temp < firstMonitorPriceInt) {
                secondMonitorPriceString = searchResults2.get(i).getText();
                secondMonitorPriceInt = temp;
                secondMonitorCounter = i + 1;
                break;
            }
        }

        WebElement secondMonitorsPosition = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + secondMonitorCounter + ") > app-goods-tile-default > div > div.goods-tile__inner")));
        scrollToElement(secondMonitorsPosition);
        WebElement secondMonitorsImg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(" + secondMonitorCounter + ") > app-goods-tile-default > div > div.goods-tile__inner > a.goods-tile__picture > img.ng-lazyloaded")));
        secondMonitorsImg.click();

        compareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("app-compare-button > button")));
        scrollToElement(compareButton);
        compareButton.click();

        compareCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='header-actions__button-counter']")));
        scrollToElement(compareCounter);
        assertEquals(compareCounter.getText(), "2");

        secondMonitorName = driver.findElement(By.className("product__title")).getText();

        WebElement comparison = driver.findElement(By.cssSelector("div a[class='header-actions__button header-actions__button_type_compare header-actions__button_state_active']"));
        action.moveToElement(comparison).perform();
        WebElement comparisonMonitors = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-comparison__link")));
        comparisonMonitors.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='sprite g-rating-stars-i']")));
        List<WebElement> quantityMonitors = driver.findElements(By.cssSelector("div[class='comparison-t-head-cell valigned-top']"));
        List<WebElement> priceMonitors = driver.findElements(By.xpath("//div[@class='g-price-uah']"));
        List<WebElement> nameMonitors = driver.findElements(By.cssSelector("a[class='comparison-g-title g-title novisited']"));

        firstMonitorPriceString = firstMonitorPriceString + " грн";
        secondMonitorPriceString =secondMonitorPriceString + " грн";
        assertEquals(quantityMonitors.size(), 2);
        assertEquals(priceMonitors.get(0).getText(), firstMonitorPriceString);
        assertEquals(priceMonitors.get(1).getText(), secondMonitorPriceString);
        assertEquals(nameMonitors.get(0).getText(), firstMonitorName);
        assertEquals(nameMonitors.get(1).getText(), secondMonitorName);
    }

    private void scrollToElement(WebElement elem) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
