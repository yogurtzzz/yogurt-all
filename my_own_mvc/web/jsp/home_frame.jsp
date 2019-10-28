<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/11
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="../css/home_frame.css" />
    <script src="../js/jquery-3.3.1.js"></script>
</head>
<body>
<div id="flex-container">
<div id="wrap">
    <ul title="I Love you">
        <li><img src="../images/rotate/piggy1.jpg" /></li>
        <li><img src="../images/rotate/piggy2.jpg" /></li>
        <li><img src="../images/rotate/piggy3.jpg" /></li>
        <li><img src="../images/rotate/piggy4.jpg" /></li>
        <li><img src="../images/rotate/piggy5.jpg" /></li>
        <li><img src="../images/rotate/piggy6.jpg" /></li>
    </ul>
</div>
<div id="welcome">
    <ul class="normal mess">
        <li>W</li>
        <li>e</li>
        <li>l</li>
        <li>c</li>
        <li>o</li>
        <li>m</li>
        <li>e</li>
    </ul>
</div>
</div>
<script>
    $(function(){
        setTimeout(function () {
            $(".mess").removeClass("mess");
        },500);
    });
</script>
</body>
</html>
