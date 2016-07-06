package monthcalendar; /**
 * Created by Mr_Blame on 04.06.2016.
 */

import java.util.ArrayList;


public class Calendar {

    private ArrayList<String> titleForCalendarDays;
    private CreatingCalendarForMonth creatingCalendarForMonth;


    public Calendar(String[] parameter) {
        creatingCalendarForMonth = new CreatingCalendarForMonth(parameter);
        titleForCalendarDays = creatingCalendarForMonth.createDaysTitle();

    }

    public void showTitleForMonth() {
        System.out.print(creatingCalendarForMonth.YELLOW_COLOR);
        System.out.printf("%35s", creatingCalendarForMonth.createMonthTitle());
        System.out.println(creatingCalendarForMonth.COLOR_RESET);
    }

    public void showTitleForDays() {
        for (String day :
                titleForCalendarDays) {
            if (day == titleForCalendarDays.get(5)) {
                System.out.print(creatingCalendarForMonth.YELLOW_COLOR);
            }
            System.out.printf("%5s", day);
        }
        System.out.println(creatingCalendarForMonth.COLOR_RESET);
    }

    public void showCalendar() {

        String emptySpace = "";
        String colorForPrinting;

        showTitleForMonth();
        showTitleForDays();


        for (int i = 0; i < creatingCalendarForMonth.getCalendarData().size(); i++) {

            colorForPrinting = creatingCalendarForMonth.setColorForPrinting(i);

            System.out.print(colorForPrinting);

            if (creatingCalendarForMonth.getCalendarData().get(i) == 0) {
                System.out.printf("%5s", emptySpace);
            } else {
                System.out.printf("%5d", creatingCalendarForMonth.getCalendarData().get(i));
            }

            System.out.print(creatingCalendarForMonth.COLOR_RESET);

            creatingCalendarForMonth.gotoNewLineInTable(i);
        }

    }

}
