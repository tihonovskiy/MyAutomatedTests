import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.AssertJUnit.assertTrue;

public class AutomationTestWhichHandleCookies {
    String baseUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";
    String login = "1303";
    String password = "Guru99";

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
    public void handleCookiesTest() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        Set<Cookie> cookies = driver.manage().getCookies();
        assertThat(cookies, notNullValue());
        driver.manage().deleteAllCookies();
        cookies = driver.manage().getCookies();
        assertThat(cookies.toString(), equalTo("[]"));
        driver.navigate().refresh();
        WebElement logout = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertThat(cookies, notNullValue());
        assertTrue(logout.isDisplayed());
    }
}
