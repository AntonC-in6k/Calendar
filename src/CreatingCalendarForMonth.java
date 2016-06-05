/**
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
    private DayOfWeek firstDayInMonth;
    private int shiftForDaysInTable;
    private boolean trackOfCurrentDay;
    private ArrayList<Integer> calendarData;
    private int currentDayTrack;
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String YELLOW_COLOR = "\u001B[33m";
    public static final String BLUE_COLOR = "\u001B[34m";
    public static final int daysInWeak = 7;

    public CreatingCalendarForMonth(String[] parameter) {

        trackOfCurrentDay = false;
        parameterForTable = parseParameterForCalendar(processParameterForCalendar(parameter));
        monthForCalendar = setMonthForCalendar();
        daysInMonth = getNumberOfDays();
        firstDayInMonth = getFirstDayInMonth();
        shiftForDaysInTable = getShiftForDaysInTable();
        calendarData = createDateTable();
        if (getTrackOfCurrentDay() == true) {
            currentDayTrack = getCurrentDay();
        } else {
            currentDayTrack = -1;
        }
    }

    public ArrayList<Integer> getCalendarData() {
        return calendarData;
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

    private int setMonthForCalendar() {
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

        int year = Year.now().getValue();
        LocalDate date = LocalDate.of(year, monthForCalendar, 1);
        DayOfWeek result = date.getDayOfWeek();
        return result;

    }

    public LocalDate getDateByMonth() {
        int year = Year.now().getValue();
        LocalDate result = LocalDate.of(year, monthForCalendar, 1);
        return result;
    }

    public int getNumberOfDays() {
        int result = getDateByMonth().lengthOfMonth();
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

    public ArrayList<String> createDaysTitle() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 1; i <= daysInWeak; i++) {
            result.add(i - 1, DayOfWeek.of(i).toString().substring(0, 3));
        }
        return result;
    }

    public String createMonthTitle() {
        String result = getDateByMonth().getMonth().toString()+" "+Year.now();
        return result;
    }

    public String setColorForPrinting(int i) {

        String result = COLOR_RESET;
        if ((i + 1) % daysInWeak == 0 || (i + 2) % daysInWeak == 0) {
            result = YELLOW_COLOR;
        }

        if (calendarData.get(i) == currentDayTrack) {
            result = BLUE_COLOR;
        }

        return result;
    }

    public void gotoNewLineInTable(int i) {
        if ((calendarData.get(i) + getShiftForDaysInTable()) % daysInWeak == 0) {
            System.out.println();

        }
    }
}
