<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Access Denied</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">
                <div class="display-1 text-danger fw-bold">403</div>
                <h2 class="mb-3">Access Denied</h2>
                <p class="text-muted mb-4">You don't have permission to access this resource.</p>
                <a href="javascript:history.back()" class="btn btn-secondary me-2">
                    <i class="bi bi-arrow-left"></i> Go Back
                </a>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">
                    <i class="bi bi-house"></i> Dashboard
                </a>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
