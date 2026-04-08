<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        body { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }
        .login-card { max-width: 400px; margin: 100px auto; border-radius: 12px; box-shadow: 0 20px 60px rgba(0,0,0,0.3); }
        .login-header { background: linear-gradient(135deg, #667eea, #764ba2); color: white; border-radius: 12px 12px 0 0; padding: 30px; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <div class="card login-card">
            <div class="login-header">
                <h2><i class="bi bi-building"></i> CMS</h2>
                <p class="mb-0">Customer Management System</p>
            </div>
            <div class="card-body p-4">
                <c:if test="${not empty errorMsg}">
                    <div class="alert alert-danger" role="alert">
                        <i class="bi bi-exclamation-circle"></i> ${errorMsg}
                    </div>
                </c:if>
                <c:if test="${not empty logoutMsg}">
                    <div class="alert alert-success" role="alert">
                        <i class="bi bi-check-circle"></i> ${logoutMsg}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="mb-3">
                        <label for="username" class="form-label fw-bold">Username</label>
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="Enter username" required autofocus>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label fw-bold">Password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="Enter password" required>
                    </div>
                    <div class="d-grid mt-4">
                        <button type="submit" class="btn btn-primary btn-lg">
                            <i class="bi bi-box-arrow-in-right"></i> Sign In
                        </button>
                    </div>
                </form>
            </div>
            <div class="card-footer text-center text-muted py-3">
                <small>&copy; 2024 Customer Management System</small>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
