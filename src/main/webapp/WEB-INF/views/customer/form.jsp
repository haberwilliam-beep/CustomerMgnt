<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Customer Form</title>
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
                    <h1 class="h2">
                        <i class="bi bi-person-fill"></i>
                        <c:choose>
                            <c:when test="${customer.id != null}">Edit Customer</c:when>
                            <c:otherwise>Add Customer</c:otherwise>
                        </c:choose>
                    </h1>
                    <a href="${pageContext.request.contextPath}/customer/list" class="btn btn-secondary btn-sm">
                        <i class="bi bi-arrow-left"></i> Back
                    </a>
                </div>

                <div class="card shadow-sm">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/customer/save" method="post" id="customerForm">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="id" value="${customer.id}"/>

                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label for="nameEn" class="form-label fw-bold">Name (English) <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="nameEn" name="nameEn"
                                           value="${customer.nameEn}" required maxlength="255">
                                </div>
                                <div class="col-md-6">
                                    <label for="nameAr" class="form-label fw-bold">Name (Arabic)</label>
                                    <input type="text" class="form-control" id="nameAr" name="nameAr"
                                           value="${customer.nameAr}" maxlength="255" dir="rtl">
                                </div>
                                <div class="col-md-6">
                                    <label for="phone" class="form-label fw-bold">Phone</label>
                                    <input type="tel" class="form-control" id="phone" name="phone"
                                           value="${customer.phone}" maxlength="50">
                                </div>
                                <div class="col-md-6">
                                    <label for="email" class="form-label fw-bold">Email</label>
                                    <input type="email" class="form-control" id="email" name="email"
                                           value="${customer.email}" maxlength="255">
                                </div>
                                <div class="col-md-12">
                                    <label for="address" class="form-label fw-bold">Address</label>
                                    <textarea class="form-control" id="address" name="address" rows="3">${customer.address}</textarea>
                                </div>
                                <div class="col-md-6">
                                    <label for="status" class="form-label fw-bold">Status</label>
                                    <select class="form-select" id="status" name="status">
                                        <option value="ACTIVE" <c:if test="${customer.status == 'ACTIVE'}">selected</c:if>>Active</option>
                                        <option value="INACTIVE" <c:if test="${customer.status == 'INACTIVE'}">selected</c:if>>Inactive</option>
                                    </select>
                                </div>
                            </div>

                            <div class="mt-4 d-flex gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-save"></i> Save
                                </button>
                                <a href="${pageContext.request.contextPath}/customer/list" class="btn btn-secondary">
                                    <i class="bi bi-x-circle"></i> Cancel
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <jsp:include page="../layout/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>
</html>
