<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2022
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Account Info</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>


<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Account Info</div>

<div class="account-container">


    <ul>
        <li>User Name: ${pageContext.request.userPrincipal.name}</li>
        <li>Role:
            <ul>
                <c:forEach items="${userDetails.authorities}" var="auth">
                    <li>${auth.authority }</li>
                </c:forEach>
            </ul>
        </li>
    </ul>
</div>


<jsp:include page="_footer.jsp" />

</body>
</html>
