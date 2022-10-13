package com.news.Filter;



import com.news.db.DBManager;
import com.news.entities.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpSession session = request.getSession();

        Users userData = null;
        boolean userOnline=false;

        Users sessionTokenUser = (Users)session.getAttribute("USER_SESSION");

        if(sessionTokenUser==null){

            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            for(Cookie c : cookies){
                if(c.getName().equals("rememberUser")){

                    String token = c.getValue();
                    Users tokenUser = dbManager.getUserToken(token);
                    if(tokenUser!=null){
                        session.setAttribute("USER_SESSION", tokenUser);
                    }

                    break;
                }
            }





        }

        Users user = (Users)session.getAttribute("USER_SESSION");


        if(user!=null){

            userData = dbManager.getUserByEmail( user.getEmail());

            if(userData!=null){
                if(userData.getPassword().equals(user.getPassword())){

                    req.setAttribute("userOnline",userOnline);
                    req.setAttribute("USER_DATA", userData);

                }else{
                    session.removeAttribute("USER_SESSION");
                }
            }else{
                session.removeAttribute("USER_SESSION");
            }

        }

        chain.doFilter(req, resp);
    }

    private DBManager dbManager;

    public void init(FilterConfig config) throws ServletException {
        dbManager = new DBManager();
        dbManager.connecting();
    }

}
