package udemy.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Objects;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("TimeInterceptor: preHandle(...)");
        long init = System.currentTimeMillis();
        request.setAttribute("init", init);
        Thread.sleep(SecureRandom.getInstanceStrong().nextInt(500));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        long init = (Long) request.getAttribute("init");
        long end = System.currentTimeMillis();
        long time = end - init;
        if (Objects.nonNull(modelAndView)) {
            modelAndView.addObject("time", time);
        }
        log.info("Time: {}", time);
        log.info("TimeInterceptor: postHandle(...)");
    }
}
