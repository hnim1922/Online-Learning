<%-- 
    Document   : post-list
    Created on : Jul 11, 2021, 5:25:34 PM
    Author     : acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post List</title>
        <link rel="stylesheet" href="post-list.css">
    </head>
    <body>
        <div class="open-btn"  onclick="document.getElementById('slider-add-form').style.display = 'block'">Add Post</div>
        <form action="SearchBlog?index=1" method="post">
            <div class="search-bar">
                <input name="searching" type="text" placeholder="Search..">
            </div>
        </form>
        <div id="slider-add-form" class="modal">
            <form action="AddPost" method="POST" class="modal-content animate" >
                <table>
                    <tr>
                    <h1 class="information">Add Post Form</h1>
                    </tr>
                    <tr>
                        <td> User ID </td>
                        <td> 
                            <select name="userID" id="userID">
                                <c:forEach var="user" items="${userList}">
                                    <option value="${user.userID}">${user.userID}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Category ID</td>
                        <td>
                            <select name="catID" id="catID">
                                <c:forEach var="cat" items="${catList}">
                                    <option value="${cat.categoryID}">${cat.categoryID}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td> Thumbnail</td>
                        <td><input type="text" name="thumbnail"/>
                    </tr>
                    <tr>
                        <td> Title</td>
                        <td><input type="text" name="title"/>
                    </tr>
                    <tr>
                        <td> Detail</td>
                        <td><textarea name="detail"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td> <input type="date" class="text-field" name="date" id="date"></td>
                    </tr>
                    <tr>

                        <td> Flag</td>
                        <td>
                            <select name="flag" id="flag">
                                <option value="True">On</option>
                                <option value="False">OFF</option>

                            </select>
                        </td>
                    </tr>
                    <tr>

                        <td> Status:</td>
                        <td>
                            <select name="status" id="status">
                                <option value="True">Publish</option>
                                <option value="False">Draft</option>

                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('slider-add-form').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form> 
        </div>
        <div class="course-list-container ">
            <div class="course-list ">
                <c:forEach items="${sessionScope.blogList}" var="blog">
                    <div class="course-card not-study ">
                        <a href="BlogDetails?blogID=${blog.blogID}"style="text-decoration: none; color: black;">
                            <img src="${blog.thumbnail}" alt="${blog.thumbnail}" class="course-thumbnail ">
                            <div class="course-information ">
                                <h3><b>${blog.title}</b></h3>
                                <p>${blog.blogDetail}</p>
                            </div>
                            <p style="padding-left: 5%; ">${blog.updatedDate} <br> <strong>${blog.fullname}</strong></p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            
        </div>
    </body>
</html>
