package monthcalendartest;

import monthcalendar.Calendar;
import monthcalendar.CreatingCalendarForMonth;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Year;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;



public class TableOutputFormatTest {

    private  String[] parameter;

    @Test
    public void tableOutputTitleTest(){
        parameter = new String[]{"-m=7"};
        CreatingCalendarForMonth creatingCalendarForMonth= new CreatingCalendarForMonth(parameter);
        String[] daysTitle = new String[]{"MON","TUE","WED","THU","FRI","SAT","SUN"};
        assertThat(creatingCalendarForMonth.createDaysTitle().toArray(),is(daysTitle) );

    }


}
