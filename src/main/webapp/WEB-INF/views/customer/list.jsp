<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Customer List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/css/ui.jqgrid.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <jsp:include page="../layout/header.jsp"/>
    <div class="container-fluid">
        <div class="row">
            <jsp:include page="../layout/menu.jsp"/>
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2"><i class="bi bi-people"></i> Customer List</h1>
                    <div>
                        <sec:authorize access="hasAnyRole('ADMIN','OPERATOR')">
                        <a href="${pageContext.request.contextPath}/customer/form" class="btn btn-success btn-sm me-1">
                            <i class="bi bi-plus-circle"></i> Add Customer
                        </a>
                        <a href="${pageContext.request.contextPath}/customer/edit" class="btn btn-primary btn-sm">
                            <i class="bi bi-pencil-square"></i> Edit Mode
                        </a>
                        </sec:authorize>
                    </div>
                </div>

                <c:if test="${not empty successMsg}">
                    <div class="alert alert-success alert-dismissible fade show">
                        ${successMsg}<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>
                <c:if test="${not empty errorMsg}">
                    <div class="alert alert-danger alert-dismissible fade show">
                        ${errorMsg}<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <div class="card shadow-sm">
                    <div class="card-body">
                        <table id="customerGrid"></table>
                        <div id="customerPager"></div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <jsp:include page="../layout/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/jquery.jqGrid.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqgrid/4.6.0/js/i18n/grid.locale-en.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/assets/js/customer.js"></script>
    <script>
        var contextPath = '${pageContext.request.contextPath}';
        var readOnly = true;
        initCustomerGrid(contextPath, readOnly);
    </script>
</body>
</html>
