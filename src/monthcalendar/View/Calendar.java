package monthcalendar.View;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by employee on 7/13/16.
 */
public abstract class Calendar {
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

    protected abstract StringBuilder reateOutputFormatMonthTitle(int month, int year);

    private String createMonthTitle(int month, int year) {
        LocalDate dateForTitle = LocalDate.of(year, month, 1);
        String result = dateForTitle.getMonth().toString() + " " + dateForTitle.getYear();
        return result;
    }

    protected abstract StringBuilder createOutputFormatDaysTitle();

    private ArrayList<String> createDaysTitle() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i <= DAYS_IN_WEAK; i++) {
            result.add(i - 1, DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, new Locale(STYLE_OF_SHORT_DAYS_NAMES)));
        }
        return result;
    }

    protected abstract StringBuilder createOutputFormatCalendarData(ArrayList<Integer> data);

    protected abstract String chooseColorForPrinting(int valueOfDay);

    private boolean gotoNewLineInTable(int indexOfDay) {
        if (indexOfDay==0){return false;}
        return (indexOfDay+1) % DAYS_IN_WEAK == 0;
    }



}
