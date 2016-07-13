package monthcalendar.View; /**
 * Created by Mr_Blame on 04.06.2016.
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class Calendar {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_FOR_WEEKENDS = "\u001B[33m";
    public static final String COLOR_FOR_CURRENT_DAY = "\u001B[34m";
    public static final int DAYS_IN_WEAK = 7;
    public static final int INDEX_OF_SATURDAY = 5;
    public static final String STYLE_OF_SHORT_DAYS_NAMES = "en";
    private ArrayList<Integer> data;
    private int month;
    private int year;
    private int dayForTracking;

    public Calendar(ArrayList<Integer> data, LocalDate date) {
        this.data = data;
        this.month = date.getMonthValue();
        this.year = date.getYear();
        if (trackOfCurrentDay() == true) {
            this.dayForTracking = date.getDayOfMonth();
        } else {
            this.dayForTracking = -1;
        }
    }

    private boolean trackOfCurrentDay() {
        return month == LocalDate.now().getMonthValue() && year == LocalDate.now().getYear();
    }

    protected StringBuilder createStringLineForMonthTitle(int month, int year) {
        StringBuilder result = new StringBuilder();
        result.append(COLOR_FOR_WEEKENDS);
        result.append(String.format("%35s", createMonthTitle(month, year)));
        result.append(COLOR_RESET);
        result.append("\n");
        return result;
    }

    private String createMonthTitle(int month, int year) {
        LocalDate dateForTitle = LocalDate.of(year, month, 1);
        String result = dateForTitle.getMonth().toString() + " " + dateForTitle.getYear();
        return result;
    }

    protected StringBuilder createStringLineForDaysTitle() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> daysTitle = createDaysTitle();
        for (String day :
                daysTitle) {
            if (day == daysTitle.get(INDEX_OF_SATURDAY)) {
                result.append(COLOR_FOR_WEEKENDS);
            }
            result.append(String.format("%5s", day));
        }
        return result;
    }

    private ArrayList<String> createDaysTitle() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i <= DAYS_IN_WEAK; i++) {
            result.add(i - 1, DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, new Locale(STYLE_OF_SHORT_DAYS_NAMES)));
        }
        return result;
    }

    protected StringBuilder createStringLineForCalendarData(ArrayList<Integer> data) {
        String emptySpace = "";
        String colorForPrinting;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            colorForPrinting = chooseColorForPrinting(data.get(i));
            if (colorForPrinting != COLOR_RESET) {
                result.append(colorForPrinting);
            }
            if (data.get(i) == 0) {
                result.append(String.format("%5s", emptySpace));
            } else {
                result.append(String.format("%5d", data.get(i)));
            }
            result.append(COLOR_RESET);
            if (gotoNewLineInTable(i)) {
                result.append("\n");
            }
        }
        return result;
    }

    private String chooseColorForPrinting(int valueOfDay) {
        String result = COLOR_RESET;
        if (valueOfDay == 0) {
            return COLOR_RESET;
        }
        LocalDate currentDay = LocalDate.of(year, month, valueOfDay);
        if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY || currentDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            result = COLOR_FOR_WEEKENDS;
        }
        if (valueOfDay == dayForTracking) {
            result = COLOR_FOR_CURRENT_DAY;
        }
        return result;
    }

    private boolean gotoNewLineInTable(int indexOfDay) {
        return indexOfDay % DAYS_IN_WEAK == 0;
    }

    public String getOutputCalendar() {
        String result =
                createStringLineForMonthTitle(month, year).toString()
                        + createStringLineForDaysTitle().toString()
                        + createStringLineForCalendarData(data);
        return result;
    }
}
