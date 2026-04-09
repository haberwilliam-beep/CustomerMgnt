<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS - Translation Management</title>
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
                    <h1 class="h2"><i class="bi bi-translate"></i> Translation Management</h1>
                    <button class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#addModal">
                        <i class="bi bi-plus-circle"></i> Add Translation
                    </button>
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
                        <div class="mb-3">
                            <input type="text" id="searchInput" class="form-control" placeholder="Search translations...">
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover" id="translationTable">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Key</th>
                                        <th>Language</th>
                                        <th>Value</th>
                                        <th>Updated</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="t" items="${translations}">
                                        <tr>
                                            <td>${t.id}</td>
                                            <td><code>${t.key}</code></td>
                                            <td><span class="badge bg-secondary">${t.lang}</span></td>
                                            <td>${t.value}</td>
                                            <td><fmt:formatDate value="${t.updatedDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                            <td>
                                                <button class="btn btn-sm btn-outline-primary"
                                                        onclick="editTranslation(${t.id}, '${t.key}', '${t.lang}', '${t.value}')">
                                                    <i class="bi bi-pencil"></i>
                                                </button>
                                                <form action="${pageContext.request.contextPath}/translation/delete/${t.id}"
                                                      method="post" style="display:inline;"
                                                      onsubmit="return confirm('Delete this translation?')">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <button type="submit" class="btn btn-sm btn-outline-danger">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <!-- Add/Edit Modal -->
    <div class="modal fade" id="addModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Add Translation</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/translation/save" method="post" id="translationForm">
                    <div class="modal-body">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="id" id="translationId"/>
                        <div class="mb-3">
                            <label class="form-label">Key</label>
                            <input type="text" class="form-control" name="key" id="translationKey" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Language</label>
                            <select class="form-select" name="lang" id="translationLang" required>
                                <option value="en">English</option>
                                <option value="ar">Arabic</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Value</label>
                            <textarea class="form-control" name="value" id="translationValue" rows="3" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="../layout/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/assets/js/translation.js"></script>
</body>
</html>
