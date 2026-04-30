<jsp:include page="/WEB-INF/header.jsp" />

<link rel="stylesheet" href="css/style.css">

<h1>Login</h1>

<%
    String error = request.getParameter("error");
    if ("1".equals(error)) {
%>
    <p style="color:red;">Invalid username or password</p>
<%
    }
%>

<form action="auth" method="post">

    <input type="hidden" name="action" value="login">

    <label>Username:</label><br>
    <input type="text" name="username" required><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br>

    <button type="submit">Login</button>
</form>

<p>
    Don’t have an account?
    <a href="register.jsp">Register</a>
</p>

<jsp:include page="/WEB-INF/footer.jsp" />