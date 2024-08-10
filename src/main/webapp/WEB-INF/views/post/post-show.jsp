<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            display: inline-block;
            padding: 10px 15px;
            margin-top: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Post List</h1>

<form action="/post/v1/search" method="get">
    <input type="text" name="query" placeholder="검색어를 입력하세요">
    <input type="submit" value="검색">
</form>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Content</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${postList}">
        <tr>
            <td>${post.id}</td>
            <td>${post.title}</td>
            <td>${post.content}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/post/v1/new">새글 작성하기</a>
</body>
</html>
