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
        <div class="col-md-8" style="margin-top: 60px;">

            <h1 class="my-4">
            </h1>

            <form action="/updateNew" method="post">
                <%
                    News_Content content= (News_Content) request.getAttribute("content");
                    if (content!=null){
                %>
                <input type="hidden" name="authorId" value="<%=usersData.getId()%>">
                <input type="hidden" name="newsId" value="<%=content.getId()%>">
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Title</label>
                    <div class="col-sm-9">
                        <input type="text" name="title" class="form-control" value="<%=content.getTitle()%>">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label"> Content</label>
                    <div class="col-sm-9">
                        <textarea name="content" id = "short_content" ><%=content.getContent()%></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Category</label>
                    <div class="col-sm-9">
                        <select  class="form-select" name="category">
                            <%
                                ArrayList<News_Category> allCategory= (ArrayList<News_Category>) request.getAttribute("categories");
                                if (allCategory!=null){
                                    for (News_Category category : allCategory) {
                            %>
                            <option value="<%=category.getId()%>" <%if (category.getId() == content.getCategory().getId()) {out.print("selected");}%> ><%=category.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Category</label>
                    <div class="col-sm-9">
                        <select  class="form-select" name="language">
                            <%
                                ArrayList<News_Language> languages= (ArrayList<News_Language>) request.getAttribute("languages");
                                if (languages!=null){
                                    for (News_Language language : languages) {
                            %>
                            <option value="<%=language.getId()%>" <%if (content.getLanguage().getId() == language.getId()) {out.print("selected");}%>><%=language.getName()%></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-primary float-right">Update</button>
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
