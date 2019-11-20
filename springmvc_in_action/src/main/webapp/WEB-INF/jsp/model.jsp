<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vergi
  Date: 2019/11/20
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${seller.shop_name}</title>
</head>
<p>Address : ${seller.address}</p>
<p>Star : ${seller.star_level} ※</p>
<c:if test="${add != null}">
    <p>you can see this when additional param is not null : ${add}</p>
</c:if>
<body>

</body>
</html>
