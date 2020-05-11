package Fragment;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CitrusSearchFragment {
    SelenideElement searchInput = $("#search-input");
    SelenideElement submitSearchButton = $(".global-search__submit");

    public CitrusSearchFragment findProductUsingSearch(String s) {
        searchInput.setValue(s);
        submitSearchButton.click();
        return this;
    }
}
