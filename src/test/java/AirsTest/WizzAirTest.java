package AirsTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WizzAirTest {

    @Test
    public void searchTicketsTest() {
        Configuration.timeout = 15000;
        Configuration.startMaximized = true;

        open("https://wizzair.com/");
        $("#search-departure-station").setValue("Vienna");
        $x("//mark[contains(text(),'Vienna')]").click();
        $("#search-arrival-station").setValue("Kyiv - Zhulyany");
        $x("//mark[contains(text(),'Kyiv - Zhulyany')]").click();

        $("#search-departure-date").click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='5'][@data-pika-day='8']").click();
        $x("//button[@data-pika-year='2020'][@data-pika-month='5'][@data-pika-day='11']").click();
        $x("//button[@data-test='calendar-shrinkable-ok-button']").click();

        $("#search-passenger").click();
        $("#search-passenger").shouldHave(Condition.text("1"));
        $(".stepper__button.stepper__button--add").click();
        $("#search-passenger").shouldHave(Condition.text("2"));
        $x("//button[@data-test='flight-search-hide-panel']").click();
        $x("//span[contains(text(), 'Search')]").click();

        switchTo().window(1);
        $$x("//div[@class='flight-select__fare-selector transition-fade-in transition-zoom-in transition-fade-in-enter transition-zoom-in-enter']").shouldHaveSize(2);
        $$x("//time[@class='flight-select__flight-info__day']").get(0).shouldHave(Condition.text("8 Jun 2020"));
        $$x("//time[@class='flight-select__flight-info__day']").get(1).shouldHave(Condition.text("11 Jun 2020"));
    }
}
