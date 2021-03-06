<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" class="container" v-cloak>
        <div class="row py-5 mt-4 align-items-center">
            <div class="col-md-5 pr-lg-5 mb-5 mb-md-0">
                <!-- <img src="https://res.cloudinary.com/mhmd/image/upload/v1569543678/form_d9sh6m.svg" alt="" class="img-fluid mb-3 d-none d-md-block"> -->
                <h1>Create an Account</h1>
                <p class="font-italic text-muted mb-0">It's free and hardly takes more than 30 seconds.</p>
            </div>

            <!-- Registeration Form -->
            <div class="col-md-7 col-lg-6 ml-auto">
                <form class="needs-validation" action="/signup" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >

                    <div class="row">
                        <!-- Name -->
                        <div class="input-group col-lg-12 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">person</i>
                                </span>
                            </div>
                            <input id="name" type="text" name="name" placeholder="Name" class="form-control bg-white border-left-0 border-md" :value="member.name" @input="member.name = $event.target.value" required>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.name">
                                {{ errors.name }}
                            </div>
                        </div>

                        <!-- Password -->
                        <div class="input-group col-lg-6 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">lock</i>
                                </span>
                            </div>
                            <input id="password" type="password" name="password" placeholder="Password"
                                   class="form-control bg-white border-left-0 border-md" v-model="member.password" required>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.password">
                                {{ errors.password }}
                            </div>
                        </div>

                        <!-- Password Confirmation -->
                        <div class="input-group col-lg-6 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">lock</i>
                                </span>
                            </div>
                            <input id="passwdConfirmation" type="password" name="passwdConfirmation" placeholder="Confirm Password"
                                   class="form-control bg-white border-left-0 border-md" v-model="member.passwordConfirmation" @input="checkPassword" required >
                        </div>

                        <!-- Email Address -->
                        <div class="input-group col-lg-12 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">email</i>
                                </span>
                            </div>
                            <input id="email" type="text" name="email" placeholder="Email Address"
                                   class="form-control bg-white border-left-0 border-md" v-model="member.email" @input="checkDuplicateEmail" required>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.email">
                                {{ errors.email }}
                            </div>
                        </div>

                        <!-- Phone Number -->
                        <div class="input-group col-lg-12 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">phone</i>
                                </span>
                            </div>
                            <input id="phoneNumber" type="tel" name="phoneNumber" placeholder="Phone"
                                   class="form-control bg-white border-md border-left-0" v-model="member.phoneNumber" @input="checkDuplicatePhoneNumber" maxlength="11"  required>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.phoneNumber">
                                {{ errors.phoneNumber }}
                            </div>
                        </div>

                        <!-- ZIP -->
                        <div class="input-group col-lg-12 mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text bg-white border-md border-right-0">
                                    <i class="material-icons text-muted">home</i>
                                </span>
                            </div>
                            <input id="zip" type="text" name="zip" placeholder="ZIP" class="form-control col-3 bg-white border-md border-left-0 border-right-0 pl-3" v-model="member.zip" required>

                            <a class="input-group-append text-decoration-none" @click="showPostCode" role="button">
                                <span class="input-group-text bg-white border-md border-left-0">
                                    <i class="material-icons text-muted">search</i>
                                </span>
                            </a>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.zip">
                                {{ errors.zip }}
                            </div>
                        </div>

                        <!-- Address 1 -->
                        <div class="input-group col-lg-6 mb-4">
                            <div class="input-group-prepend">
                                            <span class="input-group-text bg-white border-md">
                                                <i class="material-icons text-muted">home</i>
                                            </span>
                            </div>
                            <input id="address1" type="text" name="address1" placeholder="Address 1" class="form-control bg-white border-md border-left-0 pl-3" v-model="member.address1" required>

                            <div class="col-lg-12 text-danger small mt-1" v-if="errors.address1">
                                {{ errors.address1 }}
                            </div>
                        </div>

                        <!-- Address 2 -->
                        <div class="input-group col-lg-6 mb-4">
                            <div class="input-group-prepend">
                                            <span class="input-group-text bg-white border-md">
                                                <i class="material-icons text-muted">home</i>
                                            </span>
                            </div>
                            <input id="address2" type="text" name="address2" placeholder="Address 2" class="form-control bg-white border-md border-left-0 pl-3" v-model="member.address2" required>
                        </div>

                        <!-- Submit Button -->
                        <div class="form-group col-lg-12 mx-auto mb-0">
                            <button type="submit" class="btn btn-primary btn-block py-2" >
                                <span class="font-weight-bold">Create your account</span>
                            </button>
                        </div>

                        <!-- Divider Text -->
                        <div class="form-group col-lg-12 mx-auto d-flex align-items-center my-4">
                            <div class="border-bottom w-100 ml-5"></div>
                            <span class="px-2 small text-muted font-weight-bold text-muted">OR</span>
                            <div class="border-bottom w-100 mr-5"></div>
                        </div>

                        <!-- Already Registered -->
                        <div class="text-center w-100">
                            <p class="text-muted font-weight-bold">Already Registered?
                                <a href="${pageContext.request.contextPath}/signin" class="text-primary ml-2">Sign in</a>
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script type="module">
        const errors = JSON.parse('<%= request.getAttribute("errors") %>')

        const app = new Vue({
            el: "#app",
            data: {
                member: {
                    name: "",
                    password: "",
                    passwordConfirmation: "",
                    email: "",
                    phoneNumber: "",
                    zip: "",
                    address1: "",
                    address2: "",
                },
                errors: {
                    name: "",
                    password: "",
                    email: "",
                    phoneNumber: "",
                    zip: "",
                    address1: "",
                    ...errors
                }
            },
            methods: {
                checkDuplicateEmail: function (e) {
                    fetch("/is-duplicate?email=" + this.member.email)
                        .then(response => response.json())
                        .then(response => {
                            e.target.setCustomValidity(response.ok ? "" : "?????? ???????????? ??????????????????.");
                        });
                },
                checkDuplicatePhoneNumber: function (e) {
                    fetch("/is-duplicate?phoneNumber=" + this.member.phoneNumber)
                        .then(response => response.json())
                        .then(response => {
                            e.target.setCustomValidity(response.ok ? "" : "?????? ???????????? ?????????????????????.");
                        });
                },
                checkPassword: function (e) {
                    this.member.password && e.target.setCustomValidity(this.member.password === this.member.passwordConfirmation ?
                        "" : "??????????????? ???????????? ????????????.");
                },
                showPostCode: function (e) {
                    new daum.Postcode({
                        oncomplete: (data) => {
                            // ???????????? ???????????? ????????? ??????????????? ????????? ????????? ???????????? ??????.
                            // ??? ????????? ?????? ????????? ?????? ????????? ????????????.
                            // ???????????? ????????? ?????? ?????? ????????? ??????('')?????? ????????????, ?????? ???????????? ?????? ??????.
                            let addr = ''; // ?????? ??????
                            let extraAddr = ''; // ???????????? ??????

                            //???????????? ????????? ?????? ????????? ?????? ?????? ?????? ?????? ????????????.
                            if (data.userSelectedType === 'R') { // ???????????? ????????? ????????? ???????????? ??????
                                addr = data.roadAddress;
                            } else { // ???????????? ?????? ????????? ???????????? ??????(J)
                                addr = data.jibunAddress;
                            }

                            // ???????????? ????????? ????????? ????????? ???????????? ??????????????? ????????????.
                            if (data.userSelectedType === 'R') {
                                // ??????????????? ?????? ?????? ????????????. (???????????? ??????)
                                // ???????????? ?????? ????????? ????????? "???/???/???"??? ?????????.
                                if (data.bname !== '' && /[???|???|???]$/g.test(data.bname)) {
                                    extraAddr += data.bname;
                                }
                                // ???????????? ??????, ??????????????? ?????? ????????????.
                                if (data.buildingName !== '' && data.apartment === 'Y') {
                                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                }
                                // ????????? ??????????????? ?????? ??????, ???????????? ????????? ?????? ???????????? ?????????.
                                if (extraAddr !== '') {
                                    extraAddr = ' (' + extraAddr + ')';
                                }

                            } else {
                                extraAddr = '';
                            }

                            // ??????????????? ?????? ????????? ?????? ????????? ?????????.
                            this.member.zip = data.zonecode;
                            this.member.address1 = addr;
                            this.member.address2 = extraAddr;
                            // ????????? ???????????? ????????? ????????????.
                        }
                    }).open();
                }
            }
        })
    </script>

    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>