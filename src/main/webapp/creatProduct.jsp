<%--
  Created by IntelliJ IDEA.
  User: Admind
  Date: 31/12/2021
  Time: 10:13 SA
  To change this template use File | Settings | File Templates.
--%>
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
<form method="post">
    <div class="form-group">
        <label>nhap name</label>
        <input type="text" class="form-control" name="name" aria-describedby="emailHelp" placeholder="enter name">
    </div>
    <div class="form-group">
        <label>nhap price</label>
        <input type=number class="form-control" name="price" aria-describedby="emailHelp"
               placeholder="enter price">
    </div>
    <div class="form-group">
        <label>nhap quantily</label>
        <input type="number" class="form-control" name="quantily" placeholder="enter quantily">
    </div>
    <div class="form-group">
        <label>nhap color</label>
        <input type="text" class="form-control" name="color" aria-describedby="emailHelp"
               placeholder="enter color">
    </div>
    <div class="form-group">
        <label>nhap description</label>
        <input type="text" class="form-control" name="description" aria-describedby="emailHelp" placeholder="enter description">
    </div>

    <div class="form-group">
        <label>chon Dang</label>
        <select name="idCategory">
            <c:forEach items="${categories}" var="p">
                <option value="${p.idCategory }">
                    <p>${p.nameCategory}</p>
                </option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>

