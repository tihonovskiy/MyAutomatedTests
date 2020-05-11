import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CitrusBasePage {
    private WebDriver webDriver = WebDriverRunner.getWebDriver();
    public CitrusBasePage waitForPageToLoad() {
        new WebDriverWait(webDriver, 10000).until(
                webDriver1 -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
        return this;
    }
}