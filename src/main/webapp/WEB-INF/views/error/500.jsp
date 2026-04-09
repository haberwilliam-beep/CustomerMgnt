<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Server Error</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">
                <div class="display-1 text-warning fw-bold">500</div>
                <h2 class="mb-3">Internal Server Error</h2>
                <p class="text-muted mb-2">An unexpected error occurred. Please try again later.</p>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger text-start">
                        <strong>Error:</strong> ${errorMessage}
                    </div>
                </c:if>
                <a href="javascript:history.back()" class="btn btn-secondary me-2">
                    <i class="bi bi-arrow-left"></i> Go Back
                </a>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">
                    <i class="bi bi-house"></i> Dashboard
                </a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
