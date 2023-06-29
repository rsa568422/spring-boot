package udemy.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Objects;

@Component("timeInterceptor")
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getMethod().equalsIgnoreCase("post")) {
            return true;
        }
        if (handler instanceof HandlerMethod) {
            log.info("Controller method: {}", ((HandlerMethod) handler).getMethod().getName());
        }
        log.info("TimeInterceptor: preHandle(...)");
        log.info("Intercepting: {}", handler);
        long init = System.currentTimeMillis();
        request.setAttribute("init", init);
        Thread.sleep(SecureRandom.getInstanceStrong().nextInt(100));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        Object object = request.getAttribute("init");
        if (Objects.nonNull(object)) {
            long init = (Long) object;
            long end = System.currentTimeMillis();
            long time = end - init;
            if (handler instanceof HandlerMethod && Objects.nonNull(modelAndView)) {
                modelAndView.addObject("time", time);
            }
            log.info("Time: {}", time);
            log.info("TimeInterceptor: postHandle(...)");
        }
    }
}
