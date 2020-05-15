package Citrus;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CitrusBasePage {
    private WebDriver webDriver = WebDriverRunner.getWebDriver();
    public CitrusBasePage waitForPageToLoad() {
        new WebDriverWait(webDriver, 10000).until(
                webDriver1 -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
        return this;
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot() {
        File file = Screenshots.takeScreenShotAsFile();
        try {
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream arrayOs = new ByteArrayOutputStream();
            BufferedOutputStream os = new BufferedOutputStream(arrayOs);
            image.flush();
            ImageIO.write(image, "png", os);
            return arrayOs.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}