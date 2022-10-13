package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/addnew")
public class AddNewsServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



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

        ArrayList<News_Category> allCategories=dbManager.getAllCategories();
        ArrayList<News_Language> allLanguages=dbManager.getAllLanguage();


        req.setAttribute("categories",allCategories);
        req.setAttribute("allLanguage",allLanguages);
        req.getRequestDispatcher("views/addnews.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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


        String title=req.getParameter("title");
        String content=req.getParameter("content");
        int category= Integer.parseInt(req.getParameter("category"));
        int language= Integer.parseInt(req.getParameter("language"));
        int author=Integer.parseInt(req.getParameter("author"));


        Users foundUser=dbManager.getUserById((long) author);
        News_Language foundLanguage=dbManager.getLanguageById((long) language);
        News_Category category1=dbManager.getCategoryById((long) category);

        News_Content addContent=new News_Content(null,title,content,null,category1,foundLanguage,foundUser);

        dbManager.addNews_Content(addContent);


        ArrayList<News_Content> allNews=dbManager.getAllNews();
        req.setAttribute("news",allNews);

        resp.sendRedirect("/home");

    }



}
