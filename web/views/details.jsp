<%@ page import="java.util.ArrayList" %>
<%@ page import="com.news.entities.News_Content" %>
<%@ page import="com.news.entities.Comments" %>
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
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8" style="margin-top: 60px;">

            <%
                News_Content content= (News_Content) request.getAttribute("content");
            %>

            <h1 class="my-4">
                <%=content.getTitle()%>
            </h1>
            <p>
                <%=content.getContent()%>
            </p>
            <b>
                Posted on <%=content.getPost_date()%> by
                <a href="#"><%=content.getAuthor().getFull_name()%></a>
            </b>
            <b>
                <br>
                <br>
                Comments:
                <br>
                <br>
            </b>
                <%
                ArrayList<Comments> listOfComments= (ArrayList<Comments>) request.getAttribute("comments");
                if (listOfComments!=null){
                    for (Comments comment : listOfComments){
                %>
                        <b>
                            Comment by <%=comment.getAuthor().getFull_name()%>
                            <br>
                            Post time  <%=comment.getPost_date()%>
                        </b>
                        <p>
                            Comment: <%=comment.getComment()%>
                        </p>

                        <br>
                <%
                        }
                    }
                %>
            <br>
            <c:choose>
                <c:when test="${userOnline}">
                <form action="/comment" method="post">
                    <input type="hidden" name="contentId" value="<%=content.getId()%>">
                    <div class="row mt-3">
                        <div class="col-12">
                            <textarea rows="2" class="form-control" id = "comment" name="comment"></textarea>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-12">
                            <button type="submit" id="add_comment_button" class="btn btn-info">Add Comment</button>
                        </div>
                    </div>
                </form>
                </c:when>
            </c:choose>
            <br>
            <div id = "comments_list">
            </div>
            <br><br>
        </div>


        <%@include file="/templates/sidebar.jsp"%>

    </div>

</div>
<!-- /.container -->
<%@include file="/templates/footer.jsp"%>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">

    function loadComments(){
        $.get("ajax", {

            act: "load_comments",
            blog_id : ${param.id}

        }, function(data){

            var comments = JSON.parse(data);
            var comList = "";

            for(var i = 0;i<comments.length;i++){
                comList+="<div class=\"alert alert-secondary\" role=\"alert\">"+comments[i].comment+" by <a href = \"#\">"+comments[i].author.fullName+"</a> at " +comments[i].postDate+"</div>";
            }

            $("#comments_list").html(comList);

        });
    }

    $(document).ready(function(){

        loadComments();

        <c:choose>
            <c:when test="${userOnline}">
                $("#comment").click(function(){
                    $("#comment").attr("rows", 4);
                });

                $("#add_comment_button").click(function(){
                    if($("#comment").val()!=""){
                        $.post("ajax", {

                            blog_id: ${param.id},
                            comment: $("#comment").val(),
                            act: "add_comment"

                        }, function(data){
                            $("#comment").val("");
                            $("#comment").attr("rows", "2");
                            loadComments();
                        });
                    }
                });
            </c:when>
        </c:choose>
    });
</script>


</body>

</html>
