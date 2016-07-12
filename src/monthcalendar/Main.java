package monthcalendar;

/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) {
        String[] parameter = new String[]{"-m=5","-y=2016","-o=html"};
        CreatingCalendarForMonth calendarForMonth = new CreatingCalendarForMonth(parameter);
        Calendar calendar = new Calendar(calendarForMonth.createDateTable(),calendarForMonth.getDateForView()); //My program gets parameter in format -m= *, where m - month
        calendar.showCalendar();
        System.exit(0);
    }
}
