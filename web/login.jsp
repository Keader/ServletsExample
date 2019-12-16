<%--
  Created by IntelliJ IDEA.
  User: Keader
  Date: 12/12/2019
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="cadastro" method="POST">
    <label>
        Email: <input type="text" name="email" />
    </label><br />
    <label>
        Senha: <input type="text" name="senha" />
    </label><br />

    <input type="submit" value="Enviar" />
    <input type="hidden" name="ehCadastro" value="false"/>
</form>
</body>
</html>
