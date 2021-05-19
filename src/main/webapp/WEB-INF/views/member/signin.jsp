<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body class="vh-100">
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" class="container h-75">
        <div class="row h-100 align-items-center">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">Sign In</h5>

                        <form class="form-signin" @submit="submit">
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">person</i>
                                        </span>
                                </div>
                                <input id="id" type="text" name="id" placeholder="ID" class="form-control bg-white border-left-0 border-md" v-model="id" required>
                            </div>

                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">lock</i>
                                        </span>
                                </div>
                                <input id="password" type="password" name="password" placeholder="Password" class="form-control bg-white border-left-0 border-md" v-model="password" required>
                            </div>

                            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>

                            <!-- Divider Text -->
                            <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                                <div class="border-bottom w-100 ml-5"></div>
                                <span class="px-2 small text-muted font-weight-bold text-muted">OR</span>
                                <div class="border-bottom w-100 mr-5"></div>
                            </div>

                            <!-- Already Registered -->
                            <div class="text-center w-100">
                                <p class="text-muted font-weight-bold">Dont't have a account?
                                    <a href="${pageContext.request.contextPath}/signup" class="text-primary ml-2 text-decoration-none text-uppercase">Register here</a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script  type="module">
        new Vue({
            el: "#app",
            data: {
                id: "",
                password: ""
            },
            methods: {
                submit: function (e) {
                    e.preventDefault();

                    fetch("/signin", {
                        headers: {
                            "Content-Type": "application/json"
                        },
                        method: "post",
                        body: JSON.stringify({
                            id: this.id,
                            password: this.password
                        })
                    })
                    .then(response => response.text())
                    .then(status => {
                        if (status === "success") {
                            location.href = "/";
                        } else {
                            swal("Failure", "Failed to sign in. Try again!", "error");
                        }
                    });
                }
            }
        })
    </script>
</body>
</html>