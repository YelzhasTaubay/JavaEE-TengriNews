<%@ page import="com.news.entities.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-info fixed-top" >
    <div class="container" >
        <a class="navbar-brand" href="/home">TengriNews</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <%
            boolean userOnline= (boolean) request.getAttribute("userOnline");
            Users usersData= (Users) request.getAttribute("USER_DATA");
            if (usersData!=null && userOnline == true){
                String user="";
            if (usersData.getRole_id() == 1) {
                user = "Admin";
        %>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">You are <%=user%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/home"><%=usersData.getFull_name()%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addnew">Add Blog</a>
                </li>

                <li class="nav-item">
                    <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/updateOrDelete">Update or Delete User</a>
                </li>
                <li class="nav-item">
                    <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/logout">LogOut</a>
                </li>
                <li class="nav-item">
                    <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/login">My Profile</a>
                </li>
                <br>
                <li class="nav-item">
                    <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/language">Language</a>
                </li>
            </ul>
            <%--                    <form action="" method="post" id = "logout_form">--%>
            <%--                        <input type="hidden" value="logout" name="act">--%>
            <%--                    </form>--%>
            <%--                    <script type="text/javascript">--%>
            <%--                        function logout(){--%>
            <%--                            document.getElementById("logout_form").submit();--%>
            <%--                        }--%>
            <%--                    </script>--%>
        </div>
        <%
            }else if (usersData.getRole_id() == 2){
                user="Just User";
        %>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">You are <%=user%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/home"><%=usersData.getFull_name()%></a>
                </li>
                <li class="nav-item">
                        <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/logout">LogOut</a>
                </li>
                <li class="nav-item">
                        <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/login">My Profile</a>
                </li>
                <br>
                <li class="nav-item">
                        <%--                            <a href="JavaScript:void(0)" class="nav-link" onclick="logout()">Logout</a>--%>
                    <a class="nav-link" href="/language">Language</a>
                </li>
            </ul>

        </div>



        <%
                }
            }else {
        %>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="?page=home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/registration">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/language">Language</a>
                        </li>
                    </ul>
                </div>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
                <%
                    }
                %>
    </div>
</nav>