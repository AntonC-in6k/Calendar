package monthcalendar;

import java.time.LocalDate;

/**
 * Created by employee on 7/12/16.
 */
public class ParamHandler {



    public ParamHandler() {
    }


    public Integer getParameterForMonth(String[] param) {
        Integer result = 0;

        if (param == null) {
            return LocalDate.now().getMonthValue();
        }
        if (param[0].contains("-m=")) {
            result = Integer.parseInt(param[0].substring(3).trim());
        }
        if (result < 1 || result > 12) {
            return LocalDate.now().getMonthValue();
        }
        return result;
    }


    public Integer getParameterForYear(String[] param) {
        Integer result = 0;

        if (param == null) {
            return LocalDate.now().getYear();
        }
        if (param[1].contains("-y=")) {
            result = Integer.parseInt(param[1].substring(3).trim());
        }
        if (result < 1950 || result > 2020) {
            return LocalDate.now().getYear();
        }
        return result;
    }

    public String getParameterForOutputFormat(String[] param) {
        String result = "ansii";

        if (param == null) {
            return result;
        }

        if (param[2].contains("-o=")) {
            result = param[2].substring(3).trim();
        }
        if (!(result.equals("ansii") || result.equals("html"))) {
            return "ansii";
        }
        return result;
    }
}
