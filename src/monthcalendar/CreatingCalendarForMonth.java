package monthcalendar; /**
 * Created by Mr_Blame on 03.06.2016.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class CreatingCalendarForMonth {

    private int daysInMonth;
    private int monthForCalendar;
    private int parameterForTable;
    private int yearForCalendar;
    private DayOfWeek firstDayInMonth;
    private int shiftForDaysInTable;
    private boolean trackOfCurrentDay;
    private ArrayList<Integer> calendarData;
    private int currentDayTrack;

    public CreatingCalendarForMonth(String[] parameter) {
        trackOfCurrentDay = false;
        parameterForTable = parseParameterForCalendar(processParameterForCalendar(parameter));
        monthForCalendar = setMonthForCalendar();
        daysInMonth = getNumberOfDays();
        firstDayInMonth = getFirstDayInMonth();
        shiftForDaysInTable = getShiftForDaysInTable();
        calendarData = createDateTable();
        yearForCalendar = 2016;
        if (getTrackOfCurrentDay() == true) {
            currentDayTrack = getCurrentDay();
        } else {
            currentDayTrack = -1;
        }
    }

    public boolean getTrackOfCurrentDay() {
        return trackOfCurrentDay;
    }

    public String processParameterForCalendar(String[] parameter) {
        String result = "";
        if (parameter.length == 0) {
            return (getCurrentMonth() + "");
        }
        for (int i = 0; i < parameter.length; i++) {
            if (parameter[i].trim().startsWith("-m=")) {
                result = parameter[i].substring(3).trim();
                break;
            }
        }
        return result;
    }

    public int parseParameterForCalendar(String parameter) {
        int result = 0;
        try {
            result = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid parameter");
        }
        return result;
    }

    public int setMonthForCalendar() {
        int month = 0;
        if (parameterForTable > 0 && parameterForTable <= 12) {
            month = parameterForTable;
        } else {
            month = getCurrentMonth();
        }

        if (month == getCurrentMonth()) {
            enableTrackOfCurrentDay();
        }
        return month;
    }

    private void enableTrackOfCurrentDay() {
        trackOfCurrentDay = true;
    }

    public int getCurrentDay() {
        LocalDate date = LocalDate.now();
        int day = date.getDayOfMonth();
        return day;
    }

    public int getCurrentMonth() {
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int thisMonth = month.getValue();
        return thisMonth;
    }

    public DayOfWeek getFirstDayInMonth() {
        LocalDate date = LocalDate.of(yearForCalendar, monthForCalendar, 1);
        DayOfWeek result = date.getDayOfWeek();
        return result;
    }

    public LocalDate getDateByMonth(int month, int year) {
        LocalDate result = LocalDate.of(year, month, 1);
        return result;
    }

    public int getNumberOfDays() {
        int result = getDateByMonth(monthForCalendar, yearForCalendar).lengthOfMonth();
        return result;
    }

    public int getShiftForDaysInTable() {
        int result = firstDayInMonth.getValue() - 1;
        return result;
    }

    public ArrayList<Integer> createDateTable() {
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < shiftForDaysInTable; i++) {
            result.add(0);
        }
        for (int i = 0; i < daysInMonth; i++) {
            result.add(i + 1);
        }
        return result;
    }

    public LocalDate getDateForView() {
        LocalDate result = LocalDate.of(yearForCalendar, monthForCalendar, currentDayTrack);
        return result;
    }
}



