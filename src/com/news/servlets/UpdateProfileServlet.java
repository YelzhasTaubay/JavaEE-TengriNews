package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

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

        req.setAttribute("user",userSession);
        req.getRequestDispatcher("views/updateProfile.jsp").forward(req,resp);

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

        int userId= Integer.parseInt(req.getParameter("usersId"));
        int usersRole=Integer.parseInt(req.getParameter("role_id"));
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String fullName=req.getParameter("full_name");

        Users findUser=dbManager.getUserById((long) userId);
        findUser.setPassword(password);
        findUser.setEmail(email);
        findUser.setFull_name(fullName);

        dbManager.updateProfile(findUser);

        resp.sendRedirect("/home");
    }
}
