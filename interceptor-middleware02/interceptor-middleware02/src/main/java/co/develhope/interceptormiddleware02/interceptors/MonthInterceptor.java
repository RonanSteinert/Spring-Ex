package co.develhope.interceptormiddleware02.interceptors;

import co.develhope.interceptormiddleware02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "der Januar"),
            new Month(2, "February", "Febbrario", "der Februar"),
            new Month(3, "March", "Marzo", "der März"),
            new Month(4, "April", "Aprile", "der April"),
            new Month(5, "May", "Maggio", "der Mai"),
            new Month(6, "June", "Giugno", "der Juni")));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumber = request.getHeader("monthNumber");
        if (monthNumber == null || monthNumber.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else {
            int monthNumberInt = Integer.parseInt(monthNumber);
            Month month = months.stream().filter(singleMonth -> {
                return singleMonth.getMonthNumber() == monthNumberInt;
            }).findFirst().orElseGet(() -> new Month(monthNumberInt, "nope", "nope", "nope"));
            request.setAttribute("monthNumber", month);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return true;
    }
}
