<jsp:include page="/WEB-INF/header.jsp" />

<link rel="stylesheet" href="css/style.css">

<h1>Register</h1>

<%
    String error = request.getParameter("error");
    if ("exists".equals(error)) {
%>
    <p style="color:red;">User already exists</p>
<%
    }
%>

<form action="auth" method="post">

    <input type="hidden" name="action" value="register">

    <label>Username:</label><br>
    <input type="text" name="username" required><br>

    <label>Password:</label><br>
    <input type="password" name="password" required><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br>

    <button type="submit">Register</button>
</form>

<p>
    Already have an account?
    <a href="login.jsp">Login</a>
</p>

<jsp:include page="/WEB-INF/footer.jsp" />