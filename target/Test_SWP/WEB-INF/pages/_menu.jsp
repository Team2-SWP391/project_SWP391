<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2022
  Time: 11:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="menu-container">

    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <a href="${pageContext.request.contextPath}/productList">
        Product List
    </a>
    |
    <a href="${pageContext.request.contextPath}/shoppingCart">
        My Cart
    </a>
    |
    <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/orderList">
            Order List
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_MANAGER')">
        <a href="${pageContext.request.contextPath}/product">
            Create Product
        </a>
        |
    </security:authorize>

</div>
