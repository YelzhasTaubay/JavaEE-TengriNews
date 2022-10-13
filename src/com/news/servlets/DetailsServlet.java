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

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {

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


        int id= Integer.parseInt(req.getParameter("id"));
        News_Content content=new News_Content();
        ArrayList<News_Content> allNews=dbManager.getAllNews();
        for (int i = 0; i < allNews.size(); i++) {
            if (id == allNews.get(i).getId()){
                content=allNews.get(i);
            }
        }

        ArrayList<Comments> getAllComments=dbManager.getAllComments();
        ArrayList<Comments> listsOfCommentsById=new ArrayList<>();
        for (int i = 0; i < getAllComments.size(); i++) {
            if ( getAllComments.get(i).getBlog().getId() == content.getId() ){
                listsOfCommentsById.add(getAllComments.get(i));
            }
        }


        req.setAttribute("comments",listsOfCommentsById);
        req.setAttribute("content",content);
        req.getRequestDispatcher("views/details.jsp").forward(req,resp);

    }
}
