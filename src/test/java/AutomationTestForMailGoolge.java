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

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import java.awt.*;
import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class AutomationTestForMailGoolge {
    String baseUrl = "https://www.google.com/gmail/about/#";
    String myEmail = "tihonovskiytesttest@gmail.com";
    String myPassword = "123Test123!";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void driverSetUp() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver","C:\\IdeaProjects\\webdriver\\chromedriver.exe");
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
    public void mailGoolgeTest() throws InterruptedException, AWTException {
        driver.findElement(By.cssSelector(".header__nav--ltr > li:nth-child(2) > a")).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.close();
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
        WebElement login = driver.findElement(By.id("identifierId"));
        login.sendKeys(myEmail);
        driver.findElement(By.id("identifierNext")).click();
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        password.sendKeys(myPassword + Keys.ENTER);
        WebElement newMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")));
        newMessage.click();
        WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
        to.sendKeys(myEmail);
        driver.findElement(By.name("subjectbox")).sendKeys("mySubjectbox");
        driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).sendKeys("myBody");
        driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
        Thread.sleep(1000);
        StringSelection ss = new StringSelection("C:\\IdeaProjects\\MyAutomatedTests\\src\\main\\resources\\1.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("vI")));
        driver.findElement(By.xpath("//div[@role='button'][@data-tooltip-delay='800']")).click();
        Thread.sleep(1000);
        WebElement newMail = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='yW'][1]")));
        newMail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='hP']")));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='go']")));
        assertEquals(driver.findElement(By.xpath("//h2[@class='hP']")).getText(), "mySubjectbox");
        assertEquals(driver.findElement(By.cssSelector("div[class='a3s aXjCH '] > div[dir='ltr']")).getText(), "myBody");
        assertEquals(driver.findElement(By.cssSelector("span[class='go']")).getText(), "<tihonovskiytesttest@gmail.com>");
        assertEquals(driver.findElement(By.className("aYy")).getText(), "1.txt");
    }
}
