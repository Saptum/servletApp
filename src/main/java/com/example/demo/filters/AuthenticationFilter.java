package com.example.demo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext  servletContext;

    public void init(FilterConfig fConfig) throws ServletException {
        this.servletContext = fConfig.getServletContext();
        this.servletContext.log(">>> AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        this.servletContext.log("Requested Resource::http://localhost:8080" + uri);

        HttpSession session = req.getSession(false);

        if (session == null){
            if (AuthentificationCheck.checkLogin(uri)){
                chain.doFilter(request, response);
            }else {
                this.context.log("<<< Unauthorized access request");
                PrintWriter out = res.getWriter();
                out.println("No access because you have not logged in!!!");
            }
       }else if (!AuthentificationCheck.checkListUri(uri)) {
            this.context.log("<<< Unauthorized access request");
            PrintWriter out = res.getWriter();
            out.println("You have no rights to do that!!!");
        } else {
            chain.doFilter(request, response);
       }
    }

    public void destroy() {
        //close any resources here
    }
}
