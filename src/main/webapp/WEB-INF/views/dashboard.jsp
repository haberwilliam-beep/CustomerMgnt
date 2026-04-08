<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <jsp:include page="layout/header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <jsp:include page="layout/menu.jsp"/>
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2"><i class="bi bi-speedometer2"></i> Dashboard</h1>
                </div>

                <c:if test="${not empty successMsg}">
                    <div class="alert alert-success alert-dismissible fade show">
                        ${successMsg}<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <div class="row g-4 mt-2">
                    <div class="col-md-4">
                        <div class="card text-white bg-primary shadow">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5 class="card-title">Total Customers</h5>
                                        <h2 class="display-6">${totalCustomers}</h2>
                                    </div>
                                    <i class="bi bi-people-fill" style="font-size: 3rem; opacity: 0.7;"></i>
                                </div>
                            </div>
                            <div class="card-footer">
                                <a href="${pageContext.request.contextPath}/customer/list" class="text-white text-decoration-none">
                                    <small>View all customers &rarr;</small>
                                </a>
                            </div>
                        </div>
                    </div>
                    <sec:authorize access="hasRole('ADMIN')">
                    <div class="col-md-4">
                        <div class="card text-white bg-success shadow">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5 class="card-title">Total Users</h5>
                                        <h2 class="display-6">${totalUsers}</h2>
                                    </div>
                                    <i class="bi bi-person-badge-fill" style="font-size: 3rem; opacity: 0.7;"></i>
                                </div>
                            </div>
                            <div class="card-footer">
                                <small class="text-white">System users</small>
                            </div>
                        </div>
                    </div>
                    </sec:authorize>
                    <div class="col-md-4">
                        <div class="card text-white bg-info shadow">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5 class="card-title">Welcome</h5>
                                        <h2 class="h4">${username}</h2>
                                    </div>
                                    <i class="bi bi-person-circle" style="font-size: 3rem; opacity: 0.7;"></i>
                                </div>
                            </div>
                            <div class="card-footer">
                                <small class="text-white">Logged in as ${username}</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-12">
                        <div class="card shadow-sm">
                            <div class="card-header">
                                <h5 class="mb-0"><i class="bi bi-activity"></i> Quick Actions</h5>
                            </div>
                            <div class="card-body">
                                <a href="${pageContext.request.contextPath}/customer/list" class="btn btn-outline-primary me-2">
                                    <i class="bi bi-people"></i> View Customers
                                </a>
                                <sec:authorize access="hasAnyRole('ADMIN','OPERATOR')">
                                <a href="${pageContext.request.contextPath}/customer/form" class="btn btn-outline-success me-2">
                                    <i class="bi bi-person-plus"></i> Add Customer
                                </a>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ADMIN')">
                                <a href="${pageContext.request.contextPath}/audit/logs" class="btn btn-outline-warning me-2">
                                    <i class="bi bi-journal-text"></i> Audit Logs
                                </a>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>
</body>
</html>
