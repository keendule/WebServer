<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="container h-100">
    <div class="row h-100 justify-content-center" style="margin-top: 7rem">
        <form method="post" action="/user">
            <input type="hidden" name="action" value="${user == null ? "CREATE" : "UPDATE"}">
            <input type="hidden" name="id" value="${user == null ? "" : user.id}">
            <div class="form-group">
                <label for="formGroupExampleInput">Name</label>
                <input type="text" class="form-control" id="formGroupExampleInput"
                       placeholder="Enter name" value="${user == null ?"":user.name}" name="name">
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput2">Login</label>
                <input type="text" class="form-control" id="formGroupExampleInput2"
                       placeholder="Enter" value="${user == null ?"":user.login}" name="login">
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput3">Password</label>
                <input type="text" class="form-control" id="formGroupExampleInput3"
                       placeholder="password" value="${user == null ?"":user.password}" name="password">
            </div>
            <button type="submit" class="btn btn-primary">${user == null ? "CREATE" : "UPDATE"}</button>
        </form>
    </div>


</div>
</body>
</html>
