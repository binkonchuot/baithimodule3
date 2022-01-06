
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<form method="post" action="">
    <div class="form-group">
        <label>nhap name</label>
        <input type="text" class="form-control" name="name" aria-describedby="emailHelp" placeholder="enter name">
    </div>
    <div class="form-group">
        <label>nhap giá</label>
        <input type=text class="form-control" name="price"
               placeholder="nhap giá">
    </div>
    <div class="form-group">
        <label>nhap soluong</label>
        <input type="text" class="form-control" name="soluong" placeholder="enter so luong">
    </div>
    <div class="form-group">
        <label>nhap mau sac</label>
        <input type="text" class="form-control" name="mausac"
               placeholder="enter mau sac">
    </div>
    <div class="form-group">
        <label>nhap depcription</label>
        <input type="text" class="form-control" name="depcription"  placeholder="enter depcription">
    </div>

    <div class="form-group">
        <label>chon hang</label>
        <select name="idCategory">
            <c:forEach items="${categorys}" var="p">
                <option value="${p.idcategory}">
                    <p>${p.namecategory}</p>
                </option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
