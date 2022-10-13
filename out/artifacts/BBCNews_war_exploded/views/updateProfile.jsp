<%@ page import="java.util.ArrayList" %>
<%@ page import="com.news.entities.News_Content" %>
<%@ page import="com.news.entities.News_Category" %>
<%@ page import="com.news.entities.News_Language" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="/templates/head.jsp"%>
    <script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>

</head>

<body>

<%@include file="/templates/navbar.jsp"%>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8" style="
        margin-top: 60px;">

            <h1 class="my-4">
            </h1>

            <form action="/updateProfile" method="post">
                <%
                    Users users= (Users) request.getAttribute("user");
                    if (users!=null){
                %>
                <input type="hidden" name="usersId" value="<%=users.getId()%>">
                <input type="hidden" name="role_id" value="<%=users.getRole_id()%>">
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Email</label>
                    <div class="col-sm-9">
                        <input type="email" name="email" class="form-control" value="<%=users.getEmail()%>">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label"> Password</label>
                    <div class="col-sm-9">
                        <input type="password" name="password" class="form-control" value="<%=users.getPassword()%>">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label"> Full Name</label>
                    <div class="col-sm-9">
                        <input type="text" name="full_name" class="form-control" value="<%=users.getFull_name()%>">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-primary float-right">Update My Profile</button>
                    </div>
                </div>
                <%
                    }
                %>
            </form>

        </div>

        <%@include file="/templates/sidebar.jsp"%>

    </div>
    <!-- /.row -->
    <script>
        ClassicEditor
            .create( document.querySelector( '#content' ) )
            .catch( error => {
                console.error( error );
            } );
        ClassicEditor
            .create( document.querySelector( '#short_content' ) )
            .catch( error => {
                console.error( error );
            } );
    </script>
</div>
<!-- /.container -->
<%@include file="/templates/footer.jsp"%>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
