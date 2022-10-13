package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.News_Content;
import com.news.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<News_Content> allNews=dbManager.getAllNews();
        req.setAttribute("news",allNews);

        Users userSession= (Users) req.getSession().getAttribute("USER_SESSION");
        boolean userOnline=false;
        if (userSession!=null){
            userOnline=true;
            req.setAttribute("USER_DATA",userSession);
            req.setAttribute("userOnline",userOnline);
        }else {
            userOnline=false;
            req.setAttribute("USER_DATA",userSession);
            req.setAttribute("userOnline",userOnline);
        }

        req.getRequestDispatcher("views/index.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }
}
