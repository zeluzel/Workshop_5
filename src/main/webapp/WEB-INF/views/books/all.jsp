<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book list</title>
</head>
<body>

<table border="1">
    <tr>
        <th>id</th>
        <th>author</th>
        <th>title</th>
        <th>publisher</th>
        <th>isbn</th>
    </tr>

    <c:forEach items="${books.values()}" var="book">
        <tr>
            <td><c:out value="${book.id}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.publisher}"/></td>
            <td><c:out value="${book.isbn}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>