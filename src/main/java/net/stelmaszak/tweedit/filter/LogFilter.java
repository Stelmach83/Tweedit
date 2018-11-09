package net.stelmaszak.tweedit.filter;

import net.stelmaszak.tweedit.repository.ReqInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Component
@Order(1)
public class LogFilter implements Filter {

    @Autowired
    private ReqInfoRepository reqInfoRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String browserDetails = httpReq.getHeader("User-Agent");
        String user = browserDetails.toLowerCase();
        String os;


        if (user.indexOf("windows") >= 0) {
            os = "Windows";
        } else if (user.indexOf("x11") >= 0) {
            os = "Unix";
        } else if (user.indexOf("android") >= 0) {
            os = "Android";
        } else if (user.indexOf("iphone") >= 0) {
            os = "iPhone";
        } else if (user.indexOf("mac") >= 0) {
            os = "MacOS";
        } else {
            os = "unknown";
        }


        String browser;
        if (browserDetails.contains("Chrome")) {
            browser = "Chrome";
        } else if (browserDetails.contains("Firefox")) {
            browser = "FireFox";
        } else if (browserDetails.contains("Opera")) {
            browser = "Opera";
        } else if (browserDetails.contains("Safari")) {
            browser = "Safari";
        } else {
            browser = "unknown";
        }

        String przeglad = os + " - " + browser;
        Date dateTime = new Date();
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long stopTime = System.currentTimeMillis();
        int czasZadania = (int) (stopTime - startTime);
        String ipAddress = httpReq.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String servletPath = ((HttpServletRequest) request).getServletPath();
        reqInfoRepository.save(new ReqInfo(przeglad, dateTime, czasZadania, ipAddress, servletPath));
    }

    @Override
    public void destroy() {
    }
}