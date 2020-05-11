package AirsTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;

public class RyanairTest {

    @Test
    public void searchTicketsTest() {
        Configuration.timeout = 15000;
        Configuration.startMaximized = true;

        open("https://www.ryanair.com/gb/en");
        $("#input-button__departure").click();
        $("#input-button__departure").clear();
        $("#input-button__departure").setValue("Vienna");
        $x("//span[contains(text(),'Vienna')]").click();
        $("#input-button__destination").setValue("Kyiv");
        $x("//span[contains(text(),'Kyiv')]").click();

        $x("//div[contains(text(),'Jun')]").click();
        $x("//div[@data-id='2020-06-08']").click();
        $x("//div[@data-id='2020-06-11']").click();
        $x("//div[@class='input-button__input input-button__display-value--truncate-text ng-star-inserted']").shouldHave(Condition.text(" 1 Adult "));
        $x("//div[@class='counter__button-wrapper--enabled']").click();
        $x("//div[@class='input-button__input input-button__display-value--truncate-text ng-star-inserted']").shouldHave(Condition.text(" 2 Adult "));
        $x("//button[@class='passengers__confirm-button ry-button--anchor-blue ry-button--anchor']").click();
        $("button > ry-spinner").click();

        $$x("//div[@class='card-info__cols-container']").shouldHaveSize(2);
        $$x("//h3[@class='header__title']").get(0).shouldHave(Condition.text(" Vienna to Kyiv  "));
        $$x("//h3[@class='header__title']").get(1).shouldHave(Condition.text(" Kyiv to Vienna  "));
        $$x("//span[@class='date-item__month h4 date-item__month--selected']").get(0).shouldHave(Condition.text("Jun"));
        $$x("//span[@class='date-item__month h4 date-item__month--selected']").get(1).shouldHave(Condition.text("Jun"));
    }
}
