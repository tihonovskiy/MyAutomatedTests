import static com.codeborne.selenide.Selenide.$x;

public class CitrusProductListPage {
    public CitrusProductListPage findProductByNameAndClick(String productName) {
        $x("//a[@title='" + productName + "']").click();
        return  this;
    }
}
