package monthcalendartest;

/**
 * Created by employee on 7/12/16.
 */

import monthcalendar.ParamHandler;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParamHandlerTest {

    @Test
    public void emptyParameterForMonth() {
        String[] parameter = new String[]{};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForMonth(null), is(LocalDate.now().getMonthValue()));
    }

    @Test
    public void wrongParameterForMonth() throws Exception {
        String[] parameter = new String[]{"-m=13","-y=2016","-o=html"};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForMonth(parameter), is(LocalDate.now().getMonthValue()));
    }

    @Test
    public void parameterForMonth(){
        String[] parameter = new String[]{"-m=5","-y=2016","-o=html"};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForMonth(parameter), is(5));
    }

    @Test
    public void emptyParameterForYear() throws Exception {
        String[] parameter = new String[]{};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForYear(null), is(LocalDate.now().getYear()));
    }

    @Test
    public void wrongParameterForYear() throws Exception {
        String[] parameter = new String[]{"-m=8","-y=2200","-o=html"};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForYear(parameter), is(LocalDate.now().getYear()));
    }

    @Test
    public void parameterForYear(){
        String[] parameter = new String[]{"-m=5","-y=2015","-o=html"};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForYear(parameter), is(2015));
    }

    @Test
    public void parameterForOutputFormat(){
        String[] parameter = new String[]{"-m=5", "-y=2016", "-o=html"};
        ParamHandler paramHandler=new ParamHandler();
        assertThat(paramHandler.getParameterForOutputFormat(parameter), is("html"));
    }
}
