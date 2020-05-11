package OtherTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GuruAutorizationTest {

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
        driver.get(baseUrl);
    }

    @AfterSuite
    public void quiteBrowser() {
        driver.quit();
    }

    @Test(priority = 1)
    public void positivLoginTest() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        WebElement logoutLink = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));

        logoutLink.click();
        assertEquals(driver.switchTo().alert().getText(), "You Have Succesfully Logged Out!!");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
        assertTrue(driver.findElement(By.cssSelector("h2.barone")).isDisplayed());
    }

    @Test(priority = 2)
    public void negativeLoginTestInvalidLogin(){
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("login");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 3)
    public void negativeLoginTestInvalidPassword() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 4)
    public void negativeLoginTestEmptyLogin() {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 5)
    public void negativeLoginTestEmptyPassword() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 6)
    public void negativeLoginTestEmptyPasswordAndLogin(){
        driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

        assertEquals(driver.switchTo().alert().getText(), "User or Password is not valid");
        driver.switchTo().alert().accept();
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 7)
    public void positivResetButtonLoginTest() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='RESET']")).click();

        assertEquals(driver.findElement(By.xpath("//input[@name='uid']")).getAttribute("value"), "");
        assertEquals(driver.findElement(By.xpath("//input[@name='password']")).getAttribute("value"), "");
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 8)
    public void TooltipUserIdLoginTest() {
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='uid']")).clear();

        assertTrue(driver.findElement(By.xpath("//label[@id='message23']")).isDisplayed());
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }

    @Test(priority = 9)
    public void TooltipPasswordLoginTest() {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='password']")).clear();

        assertTrue(driver.findElement(By.xpath("//label[@id='message18']")).isDisplayed());
        assertEquals(driver.getCurrentUrl(), baseUrl);
    }
}