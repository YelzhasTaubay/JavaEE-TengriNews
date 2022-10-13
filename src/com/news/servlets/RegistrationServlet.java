package com.news.servlets;


import com.news.db.DBManager;
import com.news.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {

    private DBManager dbManager;
    public void init(){
        dbManager=new DBManager();
        dbManager.connecting();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Users> allUsers=dbManager.getAllUsers();

        String message="error";

        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String rePassword=req.getParameter("re-password");
        String fullName=req.getParameter("full_name");


        if (email!=null && !email.trim().equals("") && password!=null && !password.trim().equals("") &&
            rePassword!=null && !rePassword.trim().equals("") && fullName!=null && !fullName.trim().equals("") ){

            if (password.equals(rePassword)){

                Users getUserExists=dbManager.getUserByEmail(email);

                    if (getUserExists == null){


                        dbManager.addUser(new Users(null,email,password,fullName,2));
                        message="success";

                    }else {

                        message="exists";

                    }

            }else {
                message="mismatch";

            }

        }else{
            message="uncomplete";

        }

        resp.sendRedirect("registration?"+message);


    }
}
