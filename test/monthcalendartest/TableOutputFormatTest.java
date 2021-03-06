package monthcalendartest;

import monthcalendar.Calendar;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class TableOutputFormatTest {

    private Calendar calendar;


    public ArrayList<Integer> getTableForJulyMonth() {
        Integer[] listOfDays = new Integer[]{
                0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
        };
        ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(listOfDays));
        return result;
    }

    @Before
    public void loadCalendarForMonth() {
        LocalDate date = LocalDate.of(2016,7,11);
        calendar = new Calendar(getTableForJulyMonth(),date);
    }

    @Test
    public void firstDayInOutput() {
        String firstDay = "MON";
        assertThat(calendar.createStringLineForDaysTitle().toString().trim().substring(0, 3), is(firstDay));

    }

    @Test
    public void tableOutputTitle() {

        String[] daysTitle = new String[]{"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
        String title = calendar.createStringLineForDaysTitle().toString().trim();
        title = title.replace(Calendar.COLOR_FOR_WEEKENDS, "");
        String[] days = title.split("  ");
        assertThat(days, is(daysTitle));

    }

    @Test
    public void tableOutputMonthTitle() {
        String outputLine;
        String expectedMonthAndYear = "JULY 2016";
        int month = 7;
        int year = 2016;
        outputLine = calendar.createStringLineForMonthTitle(month, year).toString().substring(5).trim();
        outputLine = outputLine.substring(0, outputLine.length() - 4);
        assertThat(outputLine, is(expectedMonthAndYear));

    }

    @Test
    public void tableOutputFormat() {
        int numberOfDaysInWeek = 7;
        int currentDay = 12;
        int daysFromThisMonthInFirstWeek = 3;

        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth(), currentDay).toString();
        assertThat(line, allOf(containsString("\n    " + (numberOfDaysInWeek - daysFromThisMonthInFirstWeek)),
                containsString("\n   " + (numberOfDaysInWeek*2 - daysFromThisMonthInFirstWeek)),
                containsString("\n   " + (numberOfDaysInWeek*3 - daysFromThisMonthInFirstWeek)),
                containsString("\n   " + (numberOfDaysInWeek*4 - daysFromThisMonthInFirstWeek))));
    }

    @Test
    public void anotherColorForWeekend() {
        int numberOfDaysInWeek = 7;
        int currentDay = 11;
        int firstDayOfWeekInMonth = 5;

        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth(), currentDay).toString();
        assertThat(line, allOf(
                containsString(Calendar.COLOR_FOR_WEEKENDS + "    " + (numberOfDaysInWeek - firstDayOfWeekInMonth)),
                containsString(Calendar.COLOR_FOR_WEEKENDS + "    " + (numberOfDaysInWeek - firstDayOfWeekInMonth+1))));
    }

    @Test
    public void currentDayColor() {

        int currentDay = 14;
        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth(), currentDay).toString();
        assertThat(line, containsString(Calendar.COLOR_FOR_CURRENT_DAY + "   " + (currentDay)));

    }

}