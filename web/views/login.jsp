<%@ page import="com.news.entities.Users" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="/templatesShop/head.jsp"%>
</head>
<%@include file="/templatesShop/navbar.jsp"%>
<body>

<section class="mt-5">
    <div class="container pt-5">
        <div class="row">
            <div class="col-6 offset-3">
                <%
                    Users userData = (Users)request.getAttribute("USER_DATA");
                    if(userData!=null){
                %>
                <div class="list-group">
                    <a href="" class="list-group-item list-group-item-action">
                        <%
                            out.print(userData.getFull_name());
                        %>
                    </a>
                    <a href="" class="list-group-item list-group-item-action">
                        <%
                            if (userData.getRole_id() == 1){
                                out.print("You are Admin");
                            }else {
                                out.print("You are User");
                            }
                        %>
                    </a>
                    <a href="/home" class="list-group-item list-group-item-action">News</a>
                    <a href="/updateProfile" class="list-group-item list-group-item-action"> My Profile</a>
                    <a href="logout" class="list-group-item list-group-item-action alert">Logout</a>
                </div>
                <%
                }else{
                %>
                <%
                    String password = request.getParameter("password");
                    if(password!=null){
                %>
                <div class="alert alert-danger" role="alert">
                    Incorrect password
                </div>
                <%
                    }
                %>
                <%
                    String user = request.getParameter("user");
                    if(user!=null){
                %>
                <div class="alert alert-danger" role="alert">
                    User doesn't exist!
                </div>
                <%
                    }
                %>
                <%
                    String uncomplete = request.getParameter("uncomplete");
                    if(uncomplete!=null){
                %>
                <div class="alert alert-danger" role="alert">
                    Complete form!
                </div>
                <%
                    }
                %>
                            <form action="/login" method="post">
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Email" name="email">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" placeholder="Password" name="password">
                                </div>
                                <div class="form-group form-check">
                                    <input type="checkbox" class="form-check-input" value="remember" name="remember">
                                    <label class="form-check-label">Remember User in Cookie</label>
                                </div>
                                <button type="submit" class="btn btn-primary">Sign In</button>
                            </form>

                <%
                    }
                %>

            </div>
        </div>
    </div>
</section>


</body>

</html>
