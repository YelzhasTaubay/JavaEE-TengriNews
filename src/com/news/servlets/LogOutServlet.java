package com.news.servlets;

import com.news.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/logout")
public class LogOutServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("USER_SESSION");
        Cookie[] getCookies=req.getCookies();
        for(Cookie c: getCookies){
            if (c.getName().equals("rememberUser")){
                c.setMaxAge(0);
                resp.addCookie(c);
                break;
            }
        }

        req.getRequestDispatcher("/login").forward(req,resp);

    }


}
