import static com.codeborne.selenide.Selenide.$x;

public class CitrusHomePage {
    public CitrusHomePage hoverMenuLine(String lineName) {
        $x("//span[contains(text(),'" + lineName + "')]").hover();
        return this;
    }

    public CitrusHomePage clickLinkInMenuApple() {
        $x("//a[@href='/smartfony/brand-apple/']").click();
        return this;
    }
}
