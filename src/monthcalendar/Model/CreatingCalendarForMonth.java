package monthcalendar.Model; /**
 * Created by Mr_Blame on 03.06.2016.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class CreatingCalendarForMonth {

    private int daysInMonth;
    private int monthForCalendar;
    private int yearForCalendar;
    private DayOfWeek firstDayInMonth;
    private int shiftForDaysInTable;
    private ArrayList<Integer> calendarData;
    private ParamHandler paramHandler;

    public CreatingCalendarForMonth(String[] parameter) {
        paramHandler = new ParamHandler();
        monthForCalendar = paramHandler.getParameterForMonth(parameter);
        yearForCalendar = paramHandler.getParameterForYear(parameter);
        daysInMonth = getNumberOfDays();
        firstDayInMonth = getFirstDayInMonth();
        shiftForDaysInTable = getShiftForDaysInTable();
        calendarData = createDateTable();
    }

    public ArrayList<Integer> getCalendarData() {
        return calendarData;
    }

    private DayOfWeek getFirstDayInMonth() {
        LocalDate date = LocalDate.of(yearForCalendar, monthForCalendar, 1);
        DayOfWeek result = date.getDayOfWeek();
        return result;
    }

    private int getNumberOfDays() {
        int result = getDateByMonth().lengthOfMonth();
        return result;
    }

    private LocalDate getDateByMonth() {
        LocalDate result = LocalDate.of(yearForCalendar, monthForCalendar, 1);
        return result;
    }

    private int getShiftForDaysInTable() {
        int result = firstDayInMonth.getValue();
        return result;
    }

    private ArrayList<Integer> createDateTable() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < shiftForDaysInTable; i++) {
            result.add(0);
        }
        for (int i = 1; i <= daysInMonth; i++) {
            result.add(LocalDate.of(yearForCalendar, monthForCalendar, i).getDayOfMonth());
        }
        return result;
    }

    public LocalDate getDateForView() {
        LocalDate result = LocalDate.of(yearForCalendar, monthForCalendar, LocalDate.now().getDayOfMonth());
        return result;
    }

}



