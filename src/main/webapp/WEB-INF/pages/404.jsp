<%--
  Created by IntelliJ IDEA.
  User: HOANG ANH
  Date: 6/4/2022
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <title>Access Denied</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<style>
body {
background-color: #95c2de;
}

</style>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title">Access Denied!</div>

<h3 style="color:red;">Sorry, you can not access this page!</h3>


<jsp:include page="_footer.jsp" />
</body>
</html>
