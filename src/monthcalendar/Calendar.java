package monthcalendar; /**
 * Created by Mr_Blame on 04.06.2016.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Calendar {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_FOR_WEEKENDS = "\u001B[33m";
    public static final String COLOR_FOR_CURRENT_DAY = "\u001B[34m";
    public static final int daysInWeak = 7;
    public static final int indexOfSaturday = 5;
    private ArrayList<Integer> data;
    private int month;
    private int year;
    private int dayForTracking;

    public Calendar(ArrayList<Integer> data, LocalDate date) {
        this.data = data;
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.dayForTracking = date.getDayOfMonth();
    }

    public StringBuilder createStringLineForMonthTitle(int month, int year) {
        StringBuilder result = new StringBuilder();
        result.append(COLOR_FOR_WEEKENDS);
        result.append(String.format("%35s", createMonthTitle(month, year)));
        result.append(COLOR_RESET);
        return result;
    }

    public String createMonthTitle(int month, int year) {
        LocalDate dateForTitle = LocalDate.of(year, month, 1);
        String result = dateForTitle.getMonth().toString() + " " + dateForTitle.getYear();
        return result;
    }

    public StringBuilder createStringLineForDaysTitle() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> daysTitle = createDaysTitle();
        for (String day :
                daysTitle) {
            if (day == daysTitle.get(indexOfSaturday)) {
                result.append(COLOR_FOR_WEEKENDS);
            }
            result.append(String.format("%5s", day));
        }
        return result;
    }

    public ArrayList<String> createDaysTitle() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i <= daysInWeak; i++) {
            result.add(i - 1, DayOfWeek.of(i).toString().substring(0, 3));
        }
        return result;
    }

    public StringBuilder createStringLineForCalendarData(ArrayList<Integer> data, int currentDay) {
        String emptySpace = "";
        String colorForPrinting;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            colorForPrinting = setColorForPrinting(i, data.get(i), currentDay);
            if (colorForPrinting != COLOR_RESET) {
                result.append(colorForPrinting);
            }
            if (data.get(i) == 0) {
                result.append(String.format("%5s", emptySpace));
            } else {
                result.append(String.format("%5d", data.get(i)));
            }
            result.append(COLOR_RESET);
            if (gotoNewLineInTable(i) == true) {
                result.append("\n");
            }
        }
        return result;
    }

    public String setColorForPrinting(int indexOfDay, int valueOfDay, int dayForTracking) {
        String result = COLOR_RESET;
        if ((indexOfDay + 1) % daysInWeak == 0 || (indexOfDay) % daysInWeak == 0) {
            result = COLOR_FOR_WEEKENDS;
        }
        if (valueOfDay == dayForTracking) {
            result = COLOR_FOR_CURRENT_DAY;
        }
        return result;
    }

    public boolean gotoNewLineInTable(int indexOfDay) {
        if (indexOfDay % daysInWeak == 0) {
            return true;
        }
        return false;
    }

    public void showCalendar() {
        System.out.println(createStringLineForMonthTitle(month, year));
        System.out.println(createStringLineForDaysTitle());
        System.out.println(createStringLineForCalendarData(data, dayForTracking));
    }
}
