package monthcalendar;

import monthcalendar.Model.CreatingCalendarForMonth;
import monthcalendar.View.Calendar;

/**
 * Created by Mr_Blame on 03.06.2016.
 */

public class Main {
    public static void
    main(String[] args) {
        String[] parameter = new String[]{"-m=7","-y=2016","-o=html"};
        CreatingCalendarForMonth calendarForMonth = new CreatingCalendarForMonth(parameter);
        Calendar calendar = new Calendar(calendarForMonth.getCalendarData(),calendarForMonth.getDateForView()); //My program gets parameter in format -m= *, where m - month
        System.out.print(calendar.getOutputCalendar());
        System.exit(0);
    }
}
