<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="container h-100">
    <div class="row h-100 justify-content-center" style="margin-top: 7rem">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Password</th>
                <th scope="col">
                    <form method="get" action="user">
                     <input class="btn btn-primary" type="submit" value="Create">
                    </form>
                </th>
                <th scope="col"></th>
            </tr>
            <tbody>

                <c:forEach var="user" items="${requestScope.users}">
                    <tr scope="row">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td>
                            <form method="get" action="user">
                                <input type="hidden" name="id" value="${user.id}">
                                <input class="btn btn-success" type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="user">
                                <input type="hidden" name="action" value="DELETE">
                                <input type="hidden" name="id" value="${user.id}">
                                <input class="btn btn-danger" type="submit" value="DELETE">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            </thead>
        </table>
    </div>


</div>
</body>
</html>

