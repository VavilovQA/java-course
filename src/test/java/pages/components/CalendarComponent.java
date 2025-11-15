package pages.components;

import static com.codeborne.selenide.Selenide.$;

/**
 * @author Arsentiy Vavilov
 */
public class CalendarComponent {

    public void setDate(String month, String year) {

        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--027").click();
    }
}
