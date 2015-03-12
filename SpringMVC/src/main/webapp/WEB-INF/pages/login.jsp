<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>

<%
    String logout = request.getParameter("logout");
    if (logout != null) {
        session.removeAttribute("auth");
        //session.invalidate();
        //session.getId();
    }

    String login = request.getParameter("login");
    String password = request.getParameter("password");

/*    if (login != null && password != null) {
        LoginForm loginForm = new LoginForm();
        if (loginForm.checkPassword(login, password)) {
            session.setAttribute("auth", "OK");
        } else {
            session.removeAttribute("auth");
        }
    } */
%>

<%
    Object auth = session.getAttribute("auth");
    if (auth == null || !auth.equals("OK")) { %>

<form action="/login" method="post">
    <div>
        <label for="login">
            Логин:
            <input id="login" type="text" name="login" value="${param["login"]}"/>
        </label>
    </div>

    <div>
        <label for="password">
            Пароль:
            <input id="password" type="password" name="password"/>
        </label>
    </div>

    <input type="submit" name="submit"/>
</form>
<%
} else {
%>

Вы вошли на сайт!

<a href="index.jsp?logout">Выйти :)</a>

<% } %>


</body>
</html>
