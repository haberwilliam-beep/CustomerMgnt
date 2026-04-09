<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top shadow">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/dashboard">
            <i class="bi bi-building"></i> CMS
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <span class="nav-link text-light">
                        <i class="bi bi-person-circle"></i>
                        <sec:authentication property="name"/>
                        (<sec:authentication property="authorities"/>)
                    </span>
                </li>
                <!-- Language switcher -->
                <li class="nav-item">
                    <a class="nav-link" href="?lang=en"><span class="flag-icon">EN</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?lang=ar"><span>AR</span></a>
                </li>
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/logout" method="post" class="d-inline">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-link nav-link text-light">
                            <i class="bi bi-box-arrow-right"></i> Logout
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div style="margin-top: 56px;"></div>
