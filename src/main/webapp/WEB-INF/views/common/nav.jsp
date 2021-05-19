<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<nav id="nav" class="navbar navbar-expand-lg navbar-dark bg-dark" v-cloak>
    <!-- brand -->
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">MiniShop</a>

    <!-- right fixed menus -->
    <div class="d-flex order-lg-1 ml-auto pr-2">
        <span  class="navbar-text">
            Hello, <span>{{ member.name }}</span>
        </span>
        <ul class="navbar-nav flex-row">
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" :href="icon === 'login' ? '/signin' : '/signout'"><i class="material-icons">{{ icon }}</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/mypage"><i class="material-icons">account_circle</i></a>
            </li>
            <li class="nav-item mx-2 mx-lg-0">
                <a class="nav-link" href="${pageContext.request.contextPath}/auth/order"><i class="material-icons">receipt</i></a>
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

<!-- Check -->
<script type="module">
    const member = JSON.parse('<%= new ObjectMapper().writeValueAsString(session.getAttribute("member")) %>');

    console.log(member);

    const nav = new Vue({
        el: "#nav",
        data: {
            member: member || { name: "Unknown" },
            icon: member ? "logout" : "login"
        }
    })
</script>