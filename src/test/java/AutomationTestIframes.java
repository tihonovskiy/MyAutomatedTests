import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.AssertJUnit.assertTrue;

public class AutomationTestIframes {
    String baseUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

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
    public void testIframes() throws AWTException, InterruptedException {
        WebElement iframe1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[id^='primis_playerSekindoSPlayer']")));
        driver.switchTo().frame(iframe1);
        WebElement iframe2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe[id^='Video-iFrame-SekindoSPlayer']")));
        driver.switchTo().frame(iframe2);
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("playBtn")));
        playButton.click();
        Robot robot = new Robot();
        robot.mouseMove(800, 800);
        robot.mouseMove(350, 900);
        WebElement pauseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pauseBtn")));
        robot.mouseMove(800, 800);
        wait.until(ExpectedConditions.invisibilityOf(pauseButton));
        robot.mouseMove(350, 900);
        wait.until(ExpectedConditions.visibilityOf(pauseButton));
        assertTrue(pauseButton.isDisplayed());
        robot.mouseMove(800, 800);
        wait.until(ExpectedConditions.invisibilityOf(pauseButton));
        robot.mouseMove(350, 900);
        wait.until(ExpectedConditions.visibilityOf(pauseButton));
        assertTrue(pauseButton.isDisplayed());
        robot.mouseMove(800, 800);
        wait.until(ExpectedConditions.invisibilityOf(pauseButton));
    }
}
