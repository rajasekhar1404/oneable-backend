package ai.oneable.oneable.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GeneralInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(GeneralInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle invoked...{}:{}" + request.getRequestURI(), request.getMethod());
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle invoked...{}:{}" + request.getRequestURI(), request.getMethod());
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex != null) {
            logger.error("exception inside afterCompletion");
        }
        logger.info("afterCompletion invoked...{}:{}" + request.getRequestURI(), request.getMethod());
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}