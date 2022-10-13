package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.Comments;
import com.news.entities.News_Content;
import com.news.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/comment")
public class CommentServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
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

        String comment=req.getParameter("comment");
        int contentId= Integer.parseInt(req.getParameter("contentId"));

        News_Content news_content=null;
        ArrayList<News_Content> allNews=dbManager.getAllNews();
        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == contentId){
                news_content=allNews.get(i);
            }
        }

        dbManager.addComment(new Comments(null,comment,null,userSession,news_content));


        resp.sendRedirect("/home");
    }


}
