<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo List</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/main-page.css">
</head>
<body>
<div class="page-wrapper">
    <div class="container">
        <div class="header-container">
            <div class="header__text">
                <h2>ToDo List</h2>
            </div>
            <div class="header__statistics">
                <span>${numberOfActiveRecords} more to do, ${numberOfDoneRecords} done</span>
            </div>
        </div>
        <div class="filter-container">
        <form action="${pageContext.request.contextPath}/home" method="get" class="filter-form">
            <form class="filter-form">
                <div class="filter-form__input">
                    <input type="radio" id="filter-form__status_all" name="filter" value="all" ${empty param.filter or (fn:toLowerCase(param.filter) != 'done' and fn:toLowerCase(param.filter) != 'active') ? 'checked' : ''}>
                    <label for="filter-form__status_all">All</label>
                </div>
                <div class="filter-form__input">
                    <input type="radio" id="filter-form__status_active" name="filter" value="active" ${fn:toLowerCase(param.filter) == 'active' ? 'checked' : ''}>
                    <label for="filter-form__status_active">Active</label>
                </div>
                <div class="filter-form__input">
                    <input type="radio" id="filter-form__status_done" name="filter" value="done" ${fn:toLowerCase(param.filter) == 'done' ? 'checked' : ''}>
                    <label for="filter-form__status_done">Done</label>
                </div>
                <button type="submit">Apply</button>
            </form>
        </div>
        <div class="records-container">
            <c:choose>
                <c:when test="${not empty records}">
                    <c:forEach items="${records}" var="record">
                        <div class="record">
                            <div class="record__title"><span class="${record.status == 'DONE' ? 'record__title_strikethrough' : ''}">${record.title}</span></div>
                            <div class="record__controls">
                                <c:if test="${record.status == 'ACTIVE'}">
                                    <form action="${pageContext.request.contextPath}/make-record-done" method="post" class="record__controls-form">
                                        <input type="hidden" name="id" value="${record.id}">
                                        <input type="hidden" name="filter" value="${fn:toLowerCase(param.filter)}">
                                        <button type="submit" class="button_type_approve">
                                            <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <g clip-path="url(#clip0_258_5036)">
                                                    <path d="M8.5 0C3.808 0 0 3.808 0 8.5C0 13.192 3.808 17 8.5 17C13.192 17 17 13.192 17 8.5C17 3.808 13.192 0 8.5 0ZM7.65 12.257L3.4 8.007L4.5985 6.8085L7.65 9.8515L12.4015 5.1L13.6 6.307L7.65 12.257Z" fill="#fff"></path>
                                                </g>
                                                <defs>
                                                    <clipPath id="clip0_258_5036">
                                                        <rect width="17" height="17" fill="white"></rect>
                                                    </clipPath>
                                                </defs>
                                            </svg>
                                        </button>
                                    </form>
                                </c:if>
                                <form action="${pageContext.request.contextPath}/delete-record" method="post" class="record__controls-form">
                                    <input type="hidden" name="id" value="${record.id}">
                                    <input type="hidden" name="filter" value="${fn:toLowerCase(param.filter)}">
                                    <button type="submit" class="button_type_close">
                                        <svg width="24" height="24" viewBox="0 0 24 24">
                                            <path d="M12.071 13.485l-2.828 2.829-1.415-1.415 2.829-2.828-2.829-2.828 1.415-1.415 2.828 2.829L14.9 7.828l.707.708.707.707-2.829 2.828 2.829 2.829-1.415 1.414-2.828-2.829z" fill="#000"></path>
                                        </svg>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${fn:toLowerCase(param.filter) == 'active'}">
                            <div class="hint">
                                <span>There are no active tasks!</span>
                            </div>
                        </c:when>
                        <c:when test="${fn:toLowerCase(param.filter) == 'done'}">
                            <div class="hint">
                                <span>There are no done tasks!</span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="hint">
                                <span>There are no tasks at all, try to add new one!</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="management-container">
            <form action="${pageContext.request.contextPath}/add-record" method="post" class="management-form">
                <input type="text" name="title" placeholder="What needs to be done..." class="management-form__input">
                <button type="submit" class="management-form__button">Add Record</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>