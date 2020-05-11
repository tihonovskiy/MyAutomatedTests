import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CitrusFilterTest {
    CitrusHomePage homePage;
    CitrusProductListPage productListPage;
    CitrusProductPage productPage;
    CitrusComparePage comparePage;

    @BeforeClass
    public void setup() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("");
        homePage = new CitrusHomePage();
        productListPage = new CitrusProductListPage();
        productPage = new CitrusProductPage();
        comparePage = new CitrusComparePage();
    }

    @Test
    public void usePriceFilter() throws Exception {
        homePage.hoverMenuLine("Смартфоны")
                .clickLinkInMenuSamsung();
        productListPage.getCitrusFilterFragment().addMinMaxFilterPrice("3000", "15000");
        productListPage.getCitrusFilterFragment().verifyMinMaxPrice();
        productListPage.getCitrusFilterFragment().verifyBrand("samsung");
    }

    @Test
    public void useMemorySizeFilter() throws Exception {
        homePage.hoverMenuLine("Смартфоны")
                .clickLinkInMenuXiaomi();
        productListPage.getCitrusFilterFragment().addMemoryFilter();
        productListPage.getCitrusFilterFragment().verifySmartphoneMemory();
    }

    @Test
    public void useBodyMaterialFilter() throws Exception {
        homePage.hoverMenuLine("Смартфоны")
                .clickLinkInMenuGoogle();
        productListPage.getCitrusFilterFragment().addBodyMaterialFilter();
        productListPage.getCitrusFilterFragment().verifyBrand("google");
    }
}
