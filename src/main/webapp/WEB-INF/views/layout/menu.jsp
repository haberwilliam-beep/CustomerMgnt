<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
                    <i class="bi bi-speedometer2"></i> Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/customer/list">
                    <i class="bi bi-people"></i> Customers
                </a>
            </li>
            <sec:authorize access="hasAnyRole('ADMIN','OPERATOR')">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/customer/edit">
                    <i class="bi bi-pencil-square"></i> Edit Customers
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/translation/manage">
                    <i class="bi bi-translate"></i> Translations
                </a>
            </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/audit/logs">
                    <i class="bi bi-journal-text"></i> Audit Logs
                </a>
            </li>
            </sec:authorize>
        </ul>
    </div>
</nav>
