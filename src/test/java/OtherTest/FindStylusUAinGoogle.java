package OtherTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.AssertJUnit.assertTrue;

public class FindStylusUAinGoogle {
    WebDriver driver;
    WebDriverWait wait;

    private int maxPageNumber = 5;
    private String textForSearchRequest = "iphone kyiv buy";
    private String expectedText = "stylus.ua";
    private String baseUrl = "https://google.com/ncr";

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToUrl() {
        driver.get(baseUrl);
    }

    @Test
    public void googleDemoTest() throws Exception {
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(textForSearchRequest + Keys.ENTER);
        WebElement stats = wait.until(presenceOfElementLocated(By.cssSelector("#result-stats")));

        aa:
        for (int i = 1; i <= maxPageNumber; i++) {
            List<WebElement> searchResults = driver.findElements(By.cssSelector("div cite"));
            bb:
            for (WebElement elem : searchResults) {
                if (elem.getText().contains(expectedText)) {
                    System.out.println("[" + expectedText + "] was found on page number " + i);
                    break aa;
                }
            }
            driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
            if (maxPageNumber == i){
                throw new Exception("[" + expectedText + "] was NOT found on " + maxPageNumber + " pages");
            }
        }
    }

    /*WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.get("https://www.google.com/webhp");
    }

    @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @Test
    public void iphoneKyivBuyFind() {
        driver.findElement(By.name("q")).sendKeys("iphone kyiv buy");
        driver.findElement(By.name("q")).submit();
        try {
            assertTrue(driver.findElement(By.partialLinkText("stylus.ua")).isDisplayed());
        } catch (NoSuchElementException e1) {
            driver.findElement(By.xpath("//a[@aria-label='Page 2']")).click();
            try {
                assertTrue(driver.findElement(By.partialLinkText("stylus.ua")).isDisplayed());
            } catch (NoSuchElementException e2) {
                driver.findElement(By.xpath("//a[@aria-label='Page 3']")).click();
                try{
                    assertTrue(driver.findElement(By.partialLinkText("stylus.ua")).isDisplayed());
                } catch (NoSuchElementException e3) {
                    driver.findElement(By.xpath("//a[@aria-label='Page 4']")).click();
                    try {
                        assertTrue(driver.findElement(By.partialLinkText("stylus.ua")).isDisplayed());
                    } catch (NoSuchElementException e4) {
                        driver.findElement(By.xpath("//a[@aria-label='Page 5']")).click();
                        assertTrue(driver.findElement(By.partialLinkText("stylus.ua")).isDisplayed());
                    }
                }
            }

        }
    }*/
}
