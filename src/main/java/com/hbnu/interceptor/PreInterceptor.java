package com.hbnu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: PreInterceptor <br/>
 * Description: <br/>
 * date: 2020/2/25 9:11<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public class PreInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String topPath = request.getContextPath();
        String url = request.getRequestURI();
        String  path =topPath+"/user/index";
        System.out.println("拦截路径为"+path);
        System.out.println("登录路径为"+url);
        int i =url.indexOf(path);

        if (i>=0) {
//            判断是否有session
            if (request.getSession().getAttribute("userinfo") != null) {
                return true;
            } else {
                request.getRequestDispatcher("/WEB-INF/view/jsp/login.jsp").forward(request, response);
                System.out.println("请求转发再放行");
                return true;
            }
        }
        System.out.println("直接放行");
        return true;
    }
}

