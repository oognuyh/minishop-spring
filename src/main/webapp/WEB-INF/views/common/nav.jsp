<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- brand -->
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">MiniShop</a>

    <!-- right fixed menus -->
    <div class="d-flex order-lg-1 ml-auto pr-2">
        <span class="navbar-text mx-2">
            <span>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal" var="account" />
                    Hello, ${account.username == "admin" ? account.username : account.member.name}
                </sec:authorize>
            </span>
        </span>
        <ul class="navbar-nav flex-row">
            <li class="nav-item mx-2 mx-lg-0">
                <sec:authorize access="isAnonymous()">
                    <a class="nav-link" href="${pageContext.request.contextPath}/signin"><i class="material-icons">login</i></a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <form action="${pageContext.request.contextPath}/signout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" class="btn nav-link"><i class="material-icons">logout</i></button>
                    </form>
                </sec:authorize>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/mypage"><i class="material-icons">account_circle</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/order/history"><i class="material-icons">receipt</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/cart/list"><i class="material-icons">shopping_cart</i></a>
            </li>
        </ul>
    </div>

    <!-- hamburger menu button -->
    <button class="navbar-toggler" style="border: none" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <i class="material-icons">menu</i>
    </button>

    <!-- left menus -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/list?categoryId=1">Outer</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/list?categoryId=2">Dress</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/list?categoryId=3">Top</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/list?categoryId=4">Bottom</a>
            </li>
        </ul>
    </div>
</nav>