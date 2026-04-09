<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Audit Logs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <jsp:include page="../layout/header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <jsp:include page="../layout/menu.jsp"/>
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2"><i class="bi bi-journal-text"></i> Audit Logs</h1>
                </div>

                <!-- Filter form -->
                <div class="card shadow-sm mb-3">
                    <div class="card-body">
                        <form method="get" action="${pageContext.request.contextPath}/audit/logs" class="row g-3">
                            <div class="col-md-4">
                                <label class="form-label">Entity</label>
                                <input type="text" class="form-control" name="entity" value="${entity}" placeholder="e.g. Customer">
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">Action</label>
                                <input type="text" class="form-control" name="action" value="${action}" placeholder="e.g. CREATE">
                            </div>
                            <div class="col-md-4 d-flex align-items-end">
                                <button type="submit" class="btn btn-primary me-2">
                                    <i class="bi bi-search"></i> Filter
                                </button>
                                <a href="${pageContext.request.contextPath}/audit/logs" class="btn btn-secondary">
                                    <i class="bi bi-x-circle"></i> Clear
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="card shadow-sm">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover table-sm">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>User ID</th>
                                        <th>Action</th>
                                        <th>Entity</th>
                                        <th>Entity ID</th>
                                        <th>Timestamp</th>
                                        <th>IP Address</th>
                                        <th>Details</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="log" items="${auditLogs}">
                                        <tr>
                                            <td>${log.id}</td>
                                            <td>${log.userId}</td>
                                            <td>
                                                <span class="badge bg-${log.action == 'DELETE' ? 'danger' : log.action == 'CREATE' ? 'success' : 'warning'}">
                                                    ${log.action}
                                                </span>
                                            </td>
                                            <td>${log.entity}</td>
                                            <td>${log.entityId}</td>
                                            <td>${log.timestamp}</td>
                                            <td>${log.ipAddress}</td>
                                            <td>
                                                <c:if test="${not empty log.details}">
                                                    <button class="btn btn-xs btn-outline-secondary"
                                                            data-bs-toggle="tooltip"
                                                            title="${log.details}">
                                                        <i class="bi bi-info-circle"></i>
                                                    </button>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty auditLogs}">
                                        <tr><td colspan="8" class="text-center text-muted">No audit logs found</td></tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <jsp:include page="../layout/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script>
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.map(function(el) { return new bootstrap.Tooltip(el); });
    </script>
</body>
</html>
