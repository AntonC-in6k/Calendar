package monthcalendartest;

import monthcalendar.CreatingCalendarForMonth;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TableDataTest {
    private String[] parameter;

    @Test
    public void emptyParameterTest() {
        parameter = new String[]{};
        CreatingCalendarForMonth creatingCalendarForMonth = new CreatingCalendarForMonth(parameter);
        int currentMonth = 7;
        assertThat(creatingCalendarForMonth.setMonthForCalendar(), is(currentMonth));
    }

    @Test
    public void numberOfDaysTest() {
        parameter = new String[]{"-m=7"};
        CreatingCalendarForMonth creatingCalendarForMonth = new CreatingCalendarForMonth(parameter);
        int numberOfDays = 31;
        assertThat(creatingCalendarForMonth.getNumberOfDays(), is(numberOfDays));
    }
}
