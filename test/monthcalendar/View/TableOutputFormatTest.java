package monthcalendar.View;


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

    public void loadCalendarForMonth(int year,int month,int day) {
        LocalDate date = LocalDate.of(year, month, day);
        calendar = new Calendar(getTableForJulyMonth(), date);
    }

    @Test
    public void firstDayInOutput() {
        loadCalendarForMonth(2016, 7, 14);
        String firstDay = "Mon";
        assertThat(calendar.createStringLineForDaysTitle().toString().trim().substring(0, 3), is(firstDay));
    }

    @Test
    public void tableOutputTitle() {
        loadCalendarForMonth(2016, 7, 14);
        String[] daysTitle = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String title = calendar.createStringLineForDaysTitle().toString().trim();
        title = title.replace(Calendar.COLOR_FOR_WEEKENDS, "");
        String[] days = title.split("  ");
        assertThat(days, is(daysTitle));
    }

    @Test
    public void tableOutputMonthTitle() {
        loadCalendarForMonth(2016, 7, 14);
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
        loadCalendarForMonth(2016, 7, 14);
        int[] mondayDayIndex = {4, 11, 18, 25};
        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth()).toString();
        assertThat(line, allOf(containsString("\n    " + (mondayDayIndex[0])),
                containsString("\n   " + (mondayDayIndex[1])),
                containsString("\n   " + (mondayDayIndex[2])),
                containsString("\n   " + (mondayDayIndex[3]))));
    }

    @Test
    public void anotherColorForWeekend() {
        loadCalendarForMonth(2016, 7, 14);
        int[] weekendsDaysIndex = {2, 3};
        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth()).toString();
        assertThat(line, allOf(
                containsString(Calendar.COLOR_FOR_WEEKENDS + "    " + (weekendsDaysIndex[0])),
                containsString(Calendar.COLOR_FOR_WEEKENDS + "    " + (weekendsDaysIndex[1]))));
    }

    @Test
    public void currentDayColor() {
        loadCalendarForMonth(2016, 7, 14);
        int currentDay = 14;
        String line = calendar.createStringLineForCalendarData(getTableForJulyMonth()).toString();
        assertThat(line, containsString(Calendar.COLOR_FOR_CURRENT_DAY + "   " + (currentDay)));
    }

}