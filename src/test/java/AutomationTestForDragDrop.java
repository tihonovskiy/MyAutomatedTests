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

import static org.testng.AssertJUnit.assertEquals;

public class AutomationTestForDragDrop {
    String baseUrl = "http://demo.guru99.com/test/drag_drop.html";

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
    public void dragDropTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credit1']/a")));
        WebElement closedIframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='flow_close_btn_iframe']")));
        closedIframe.click();
        WebElement fromElem1 = driver.findElement(By.xpath("//*[@id='credit1']/a"));
        WebElement toElem1 = driver.findElement(By.xpath("//*[@id='loan']/li"));
        WebElement fromElem2 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement toElem2 = driver.findElement(By.xpath("//*[@id='amt8']/li"));
        WebElement fromElem3 = driver.findElement(By.xpath("//*[@id='fourth']/a"));
        WebElement toElem3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));
        WebElement fromElem4 = driver.findElement(By.xpath("//*[@id='credit2']/a"));
        WebElement toElem4 = driver.findElement(By.xpath("//*[@id='bank']/li"));

        Actions action = new Actions(driver);
        action.dragAndDrop(fromElem1, toElem1).build().perform();
        action.dragAndDrop(fromElem2, toElem2).build().perform();
        action.dragAndDrop(fromElem3, toElem3).build().perform();
        action.dragAndDrop(fromElem4, toElem4).build().perform();

        WebElement perfect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Perfect')]")));
        scrollToElement(perfect);
        assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Perfect')]")).getText(), "Perfect!");
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
