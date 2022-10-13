<%@ page import="java.util.ArrayList" %>
<%@ page import="com.news.entities.News_Content" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="/templates/head.jsp"%>
    <style>
        img{
            width: 100%;
        }
    </style>
</head>

<body>

<%@include file="/templates/navbar.jsp"%>

<!-- Page Content -->
<div class="container" >

    <div class="row" style="margin-top: 50px;">

        <!-- Blog Entries Column -->
        <div class="col-md-8" >

            <h1 class="my-4">
            </h1>
            <%
                ArrayList<News_Content> content= (ArrayList<News_Content>) request.getAttribute("news");
                if (content!=null){
                    for (News_Content content1 : content){
            %>

            <div class="card mb-4">
                <div class="card-body">
                    <h2 class="card-title"><%=content1.getTitle()%></h2>
                    <p class="card-text">
                        <%=content1.getContent()%>
                    </p>
                    <a href="/details?id=<%=content1.getId()%>" class="btn btn-primary">Read More &rarr;</a>
                </div>
                <div class="card-footer text-muted">
                    Posted on <%=content1.getPost_date()%> by
                    <a href="#"><%=content1.getAuthor().getFull_name()%></a>
                </div>
            </div>

            <%
                    }
                }
            %>

            <!-- Pagination -->
            <ul class="pagination justify-content-center mb-4">
                <li class="page-item">
                    <a class="page-link" href="#">&larr; Older</a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="#">Newer &rarr;</a>
                </li>
            </ul>

        </div>

        <%@include file="/templates/sidebar.jsp"%>

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->
<%@include file="/templates/footer.jsp"%>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

