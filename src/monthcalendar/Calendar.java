package monthcalendar; /**
 * Created by Mr_Blame on 04.06.2016.
 */

import java.time.LocalDate;
import java.util.ArrayList;


public class Calendar {

    private ArrayList<String> titleForCalendarDays;
    private CreatingCalendarForMonth creatingCalendarForMonth;


    public Calendar(String[] parameter) {
        creatingCalendarForMonth = new CreatingCalendarForMonth(parameter);
        titleForCalendarDays = creatingCalendarForMonth.createDaysTitle();

    }


    public StringBuilder createStringLineForMonthTitle(int month, int year){
        StringBuilder result = new StringBuilder();
        result.append(creatingCalendarForMonth.COLOR_FOR_WEEKENDS);
        result.append(String.format("%35s", creatingCalendarForMonth.createMonthTitle(month, year)));
        result.append(creatingCalendarForMonth.COLOR_RESET);
        return result;
    }

    public StringBuilder createStringLineForDaysTitle(){
        StringBuilder result = new StringBuilder();
        for (String day :
                titleForCalendarDays) {
            if (day == titleForCalendarDays.get(5)) {
                result.append(creatingCalendarForMonth.COLOR_FOR_WEEKENDS);
            }
            result.append(String.format("%5s", day));
        }
        return result;
    }

    public StringBuilder createStringLineForCalendarData(ArrayList<Integer> data, int currentDay){
        String emptySpace = "";
        String colorForPrinting;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {

            colorForPrinting = creatingCalendarForMonth.setColorForPrinting(i,currentDay);

            if (colorForPrinting!=creatingCalendarForMonth.COLOR_RESET){
                result.append(colorForPrinting);
            }

            if (data.get(i) == 0) {
                result.append(String.format("%5s",emptySpace));
            } else {
                result.append(String.format("%5d", creatingCalendarForMonth.getCalendarData().get(i)));
            }

            result.append(creatingCalendarForMonth.COLOR_RESET);

            if(creatingCalendarForMonth.gotoNewLineInTable(i)==true){
                result.append("\n");
            }
        }

        return result;
    }

    public void showCalendar(){
        LocalDate date = LocalDate.now();
        int currentDay = date.getDayOfMonth();
        System.out.println(createStringLineForMonthTitle(creatingCalendarForMonth.getMonthForCalendar(),2016));
        System.out.println(createStringLineForDaysTitle());
        System.out.println(createStringLineForCalendarData(creatingCalendarForMonth.getCalendarData(),currentDay));
    }


}
