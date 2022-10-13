<%@ page import="java.util.ArrayList" %>
<%@ page import="com.news.entities.Users" %>
<%@ page import="com.news.entities.News_Content" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <%@include file="/templates/head.jsp"%>
</head>
<body>
<%@include file="/templates/navbar.jsp"%>
<section class="mt-5">
  <div class="container pt-5">
    <div class="row">
      <div class="col-3">


      </div>
      <div class="col-9">
        <nav aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/" style="text-decoration: none;">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">All News</li>
          </ol>
        </nav>
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Title</th>
            <th scope="col">Content</th>
            <th scope="col">Delete/Update</th>
          </tr>
          </thead>
          <tbody>
          <%

            ArrayList<News_Content> news = (ArrayList<News_Content>)request.getAttribute("news");

            int total = 0;

            if(news!=null){

              for(News_Content neww :  news){

          %>
          <tr>
            <th scope="row"><%=neww.getId()%></th>
            <td><%=neww.getTitle()%></td>
            <td><%=neww.getContent()%> KZT</td>
<%--            <td><button  class="btn btn-danger btn-sm" onclick="toDeleteItem(<%=neww.getId()%>)">DELETE</button></td>--%>
            <td>
              <form action="/updateOrDelete" method="post">
                <input type="hidden" name="deleteNew" value="<%=neww.getId()%>"/>
              <button  class="btn btn-danger btn-sm">DELETE</button>
              </form>
              <br>
              <br>
                <form action="/updateNew" method="get">
                    <input type="hidden" name="updateNew" value="<%=neww.getId()%>">
                    <button  class="btn btn-success btn-sm" >UPDATE</button>
                </form>
            </td>
          </tr>
          <%
                total = (int) (total+neww.getId());
              }
            }
          %>
          </tbody>
          <tfoot>
          <tr>
            <td colspan="4">
              <b>
                TOTAL : <%=total%> KZT
              </b>
            </td>
          </tr>
          </tfoot>
        </table>

        <form action="/basket" method="post" id="delete_form">
          <input type="hidden" value="delete" name="operation">
          <input type="hidden" name="id" id = "delete_id">
        </form>
        <script type="text/javascript">
          function toDeleteItem(id) {

            document.getElementById("delete_id").value = id;
            document.getElementById("delete_form").submit();

          }
        </script>
      </div>
    </div>
  </div>
</section>
<%@include file="/templates/footer.jsp"%>
</body>
</html>
