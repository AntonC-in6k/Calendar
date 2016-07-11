package monthcalendar;

/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) {
        CreatingCalendarForMonth calendarForMonth = new CreatingCalendarForMonth(args);
        Calendar calendar = new Calendar(calendarForMonth.createDateTable(),calendarForMonth.getDateForView()); //My program gets parameter in format -m= *, where m - month
        calendar.showCalendar();
        System.exit(0);
    }
}
