package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.News_Category;
import com.news.entities.News_Content;
import com.news.entities.News_Language;
import com.news.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/updateNew")
public class UpdateNewServlet extends HttpServlet {

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

        int id= Integer.parseInt(req.getParameter("updateNew"));
        System.out.printf("Update New:  "+ id);

        News_Content content=null;
        ArrayList<News_Content> allNews=dbManager.getAllNews();
        for (int i = 0; i < allNews.size(); i++) {
            if (allNews.get(i).getId() == id){
                content=allNews.get(i);
            }
        }
        ArrayList<News_Category> categories=dbManager.getAllCategories();
        ArrayList<News_Language> languages=dbManager.getAllLanguage();

        req.setAttribute("languages",languages);
        req.setAttribute("categories",categories);
        req.setAttribute("content",content);
        req.getRequestDispatcher("views/update.jsp").forward(req,resp);

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


        int newsId= Integer.parseInt(req.getParameter("newsId"));
        String title=req.getParameter("title");
        System.out.printf(" t "+ title);
        String content=req.getParameter("content");
        int categoryId= Integer.parseInt(req.getParameter("category"));
        int languageId= Integer.parseInt(req.getParameter("language"));
        int authorId=Integer.parseInt(req.getParameter("authorId"));

        ArrayList<News_Content> arrayList=dbManager.getAllNews();
        News_Content content1=null;
        for (int i = 0; i < arrayList.size() ;i++) {
            if (newsId == arrayList.get(i).getId()){
                content1=arrayList.get(i);
            }
        }

        News_Language language=dbManager.getLanguageById((long) languageId);
        News_Category category=dbManager.getCategoryById((long) categoryId);
        Users user=dbManager.getUserById((long) authorId);

        content1.setTitle(title);
        content1.setContent(content);
        content1.setLanguage(language);
        content1.setCategory(category);
        content1.setAuthor(user);

        dbManager.updateNews_Content(content1);

        resp.sendRedirect("/home");

    }


}
