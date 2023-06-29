package udemy.springboot.hours.app.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component("hoursInterceptor")
public class HoursInterceptor implements HandlerInterceptor {

    @Value("${config.hours.open}")
    private Integer open;

    @Value("${config.hours.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);
        if (hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("Bienvenido al horario de atención a clientes");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append(" hasta las ");
            message.append(close);
            message.append(". Gracias por su visita.");
            request.setAttribute("message", message.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/close"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        modelAndView.addObject("message", message);
    }
}
