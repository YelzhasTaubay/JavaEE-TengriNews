package com.news.db;

import com.news.entities.*;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private Connection connection;

    public void connecting(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/tengrinews",
                    "root",
                    "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> allUsers=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement("select * from users");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){

                Long id =resultSet.getLong("id");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String full_name=resultSet.getString("full_name");
                int role_Id=resultSet.getInt("role_id");

                allUsers.add(new Users(id,email,password,full_name,role_Id));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return allUsers;
    }

    public ArrayList<Comments> getAllComments(){
        ArrayList<Comments> getAllComments=new ArrayList<>();
        try {

            PreparedStatement statement=connection.prepareStatement("select * from comments");
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){

                Long id = resultSet.getLong("id");
                String comment= resultSet.getString("comment");
                Timestamp post_date=resultSet.getTimestamp("post_date");
                long users_id=resultSet.getLong("users_id");
                long news_id=resultSet.getLong("news_id");

                Users user=getUserById(users_id);
                News_Content content=null;
                ArrayList<News_Content> arrayList=getAllNews();
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getId() == news_id){
                        content=arrayList.get(i);

                    }
                }
                getAllComments.add(new Comments(id,comment,post_date,user,content));

            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return getAllComments;
    }

    public ArrayList<News_Content> getAllNews(){
        ArrayList<News_Content> allNews=new ArrayList<>();
        try {

            PreparedStatement statement=connection.prepareStatement("select" +
                    " n.id,n.content,n.post_date,n.title," +
                    "nc.id,nc.name,nl.id,nl.name,nl.code,u.id,u.email,u.full_name,u.password,u.role_id" +
                    " from news_content n left join " +
                    "news_category nc on n.category_id = nc.id left join " +
                    "news_language nl on n.language_id=nl.id left join users u on u.id=n.id;");

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){

                News_Category category=new News_Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));

                News_Language language=new News_Language();
                language.setId(resultSet.getLong("id"));
                language.setName(resultSet.getString("name"));
                language.setCode(resultSet.getString("code"));

                Users user=new Users();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFull_name(resultSet.getString("full_name"));
                user.setRole_id(resultSet.getInt("role_id"));

                News_Content content=new News_Content();
                content.setId(resultSet.getLong("id"));
                content.setTitle(resultSet.getString("title"));
                content.setContent(resultSet.getString("content"));
                content.setPost_date(resultSet.getTimestamp("post_date"));
                content.setCategory(category);
                content.setLanguage(language);
                content.setAuthor(user);

                allNews.add(content);
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allNews;
    }


    public void addNews_Content(News_Content news_content){
        try {

            PreparedStatement statement=connection.prepareStatement("insert into " +
                    "news_content(id, title, content, post_date, category_id, language_id) " +
                    " value(null,?,?,now(),?,?,?)");

            News_Category news_category=getCategoryById(news_content.getCategory().getId());
            News_Language news_language=getLanguageById(news_content.getLanguage().getId());
           Users users=getUserById(news_content.getAuthor().getId());

//            statement.setLong(1,news_content.getId());
            statement.setString(1,news_content.getTitle());
            statement.setString(2,news_content.getContent());
//            statement.setTimestamp(4,news_content.getPost_date());
            statement.setLong(3,news_category.getId());
            statement.setLong(4,news_language.getId() );
            statement.setLong(5,users.getId());

            System.out.printf("New has been added!!!");

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUser(Users user){

        try {
            PreparedStatement statement=connection.prepareStatement("insert into " +
                    "users(id, email, password, full_name, role_id) " +
                    "value(null,?,?,?,?)");

            statement.setString(1, user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFull_name());
            statement.setInt(4,user.getRole_id());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addComment(Comments comment){

        try {

            PreparedStatement statement=connection.prepareStatement("insert into " +
                    " comments(id, comment, post_date, users_id, news_id)  " +
                    " value(null,?,now(),?,?)");

            statement.setString(1,comment.getComment());
            statement.setLong(2,comment.getAuthor().getId());
            statement.setLong(3,comment.getBlog().getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public Users getUserByEmail(String email){
        Users findUser=null;

        try {
            PreparedStatement statement= connection.prepareStatement("select " +
                    "* from users where email = ?");

            statement.setString(1, email);

            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                Long id=resultSet.getLong("id");
                String email1=resultSet.getString("email");
                String password=resultSet.getString("password");
                String fullName=resultSet.getString("full_name");
                int role_id=resultSet.getInt("role_id");
                findUser=new Users(id, email1, password, fullName, role_id);
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return findUser;
    }

    public Users getUserToken(String token){
        Users findUser=null;

        try {

            PreparedStatement statement=connection.prepareStatement("" +
                    "select * from users where SHA1(concat(email,password)) = ? ");

            statement.setString(1,token);

            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){

                Long id=resultSet.getLong("id");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String fullName=resultSet.getString("full_name");
                int role_id=resultSet.getInt("role_id");
                findUser=new Users(id, email,password,fullName,role_id);

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return findUser;

    }

    public News_Category getCategoryById(Long id){
        News_Category category=null;

        try {
            PreparedStatement statement=connection.prepareStatement("select * from news_category where id = ?");

            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                Long id1=resultSet.getLong("id");
                String name=resultSet.getString("name");

                category=new News_Category(id1,name);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }




    public News_Language getLanguageById(Long id){
        News_Language language=null;
        try {

            PreparedStatement statement=connection.prepareStatement("select * from news_language where id = ?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                Long id1=resultSet.getLong("id");
                String name=resultSet.getString("name");
                String code=resultSet.getString("code");
                language=new News_Language(id1,name,code);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return language;
    }

    public Users getUserById(Long id){
        Users foundById=null;
        try {
            PreparedStatement statement=connection.prepareStatement("select * from users where id =?");

            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                Long id1=resultSet.getLong("id");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String fullName=resultSet.getString("full_name");
                int role_id=resultSet.getInt("role_id");
                foundById=new Users(id1, email,password,fullName,role_id);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return foundById;
    }


    public ArrayList<News_Category> getAllCategories(){
        ArrayList<News_Category> allCategory=new ArrayList<>();

        try {

            PreparedStatement statement=connection.prepareStatement("select * from news_category");

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");

                allCategory.add(new News_Category(id,name));
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
            return allCategory;
    }

    public ArrayList<News_Language> getAllLanguage(){
        ArrayList<News_Language> allLanguages=new ArrayList<>();
        try {

            PreparedStatement statement=connection.prepareStatement("select * from news_language");
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name=resultSet.getString("name");
                String code=resultSet.getString("code");
                allLanguages.add(new News_Language(id,name,code));
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return allLanguages;
    }

    public void deleteNew(Long id){
        try {
            PreparedStatement statement=connection.prepareStatement("delete from news_content where id = ?");

            statement.setLong(1,id);

            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCommentByNewsId(Long id){
        try {
            PreparedStatement statement=connection.prepareStatement("delete from comments where users_id = ?");
            statement.setLong(1,id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateNews_Content(News_Content news_content){

        try {

            PreparedStatement statement=connection.prepareStatement("update news_content " +
                    "set title=?, content=?, post_date=NOW(), category_id=?, language_id=?, users_id = ? " +
                    "where id = ?");

            statement.setString(1,news_content.getTitle());
            statement.setString(2,news_content.getContent());
            statement.setLong(3,news_content.getCategory().getId());
            statement.setLong(4,news_content.getLanguage().getId());
            statement.setLong(5,news_content.getAuthor().getId());
            statement.setLong(6,news_content.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProfile(Users user){
        try {
            PreparedStatement statement=connection.prepareStatement("update  users " +
                    "set email=?, password=?, full_name =? where id = ?");

            statement.setString(1,user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3,user.getFull_name());
            statement.setLong(4,user.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }




}
