package com.news.servlets;

import com.news.db.DBManager;
import com.news.entities.Users;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message="";

        String email=req.getParameter("email");
        String password=req.getParameter("password");

        if (email!=null && !email.trim().equals("") && password!=null && !password.trim().equals("")){

            Users user=dbManager.getUserByEmail(email);

            if (user!=null){

                if (user.getPassword().equals(password)){

                    req.getSession().setAttribute("USER_SESSION",user);
                    System.out.printf(user.getFull_name());

                    String remember=req.getParameter("remember");

                    if (remember!=null && remember.equals("remember")){

                        String rememberUserToken= DigestUtils.sha1Hex(user.getEmail()+user.getPassword());
                        Cookie cookie=new Cookie("rememberUser",rememberUserToken);
                        cookie.setMaxAge(3*3600*24);

                        resp.addCookie(cookie);

                    }

                }else{

                    message="password";

                }

            }else {

                message="user";

            }

        }else{

            message="uncomplete";

        }

        resp.sendRedirect("login?"+message);

    }
}
