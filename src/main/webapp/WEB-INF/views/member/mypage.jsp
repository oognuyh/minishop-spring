<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" v-cloak>
        <div class="container">
            <div class="row py-5 mt-4">

                <div class="col-md-12 col-lg-6 mb-4">
                    <form class="needs-validation" action="${pageContext.request.contextPath}/auth/change-password" method="post">
                        <div class="row">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                            <!-- Name -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">lock</i>
                                        </span>
                                </div>
                                <input id="existingPassword" type="password" name="existingPassword" placeholder="Password" class="form-control bg-white border-left-0 border-md" required />

                                <div class="col-lg-12 text-danger small mt-1" v-if="errors.password">
                                    {{ errors.password }}
                                </div>
                            </div>

                            <!-- Password -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">lock</i>
                                        </span>
                                </div>
                                <input id="newPassword" type="password" name="newPassword" v-model="newPassword" placeholder="New Password" class="form-control bg-white border-left-0 border-md" required />
                            </div>

                            <!-- Password Confirmation -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                        <span class="input-group-text bg-white border-md border-right-0">
                                            <i class="material-icons text-muted">lock</i>
                                        </span>
                                </div>
                                <input id="passwdConfirmation" type="password" name="passwdConfirmation" placeholder="Confirm Password"
                                       class="form-control bg-white border-left-0 border-md" v-model="passwordConfirmation" @input="checkPassword" required />
                            </div>

                            <!-- Submit Button -->
                            <div class="form-group col-lg-12 mx-auto mb-0">
                                <button class="btn btn-primary btn-block py-2" type="submit">
                                    <span class="font-weight-bold">Change your password</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Registeration Form -->
                <div class="col-md-12 col-lg-6">
                    <form class="needs-validation" action="${pageContext.request.contextPath}/auth/change-member-detail" method="post">
                        <div class="row">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <input type="hidden" name="id" :value="member.id" />
                            <!-- Name -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                            <span class="input-group-text bg-white border-md border-right-0">
                                                <i class="material-icons text-muted">person</i>
                                            </span>
                                </div>
                                <input id="name" type="text" name="name" :value="member.name" class="form-control bg-white border-left-0 border-md" required />

                                <div class="col-lg-12 text-danger small mt-1" v-if="errors.name">
                                    {{ errors.name }}
                                </div>
                            </div>

                            <!-- Email Address -->
                            <div class="input-group col-lg-12 mb-4">
                                <div class="input-group-prepend">
                                            <span class="input-group-text bg-white border-md border-right-0">
                                                <i class="material-icons text-muted">email</i>
                                            </span>
                                </div>
                                <input id="email" type="email" name="email" v-model="member.email" placeholder="Email Address"
                                       class="form-control bg-white border-left-0 border-md" @input="checkDuplicateEmail"  required />

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
                                <input id="phoneNumber" type="tel" name="phoneNumber" v-model="member.phoneNumber" @input="checkDuplicatePhoneNumber"
                                       class="form-control bg-white border-md border-left-0" placeholder="Phone" required />

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
                                <input id="zip" type="text" name="zip" v-model="member.zip" class="form-control col-3 bg-white border-md border-left-0 border-right-0" required />
                                <a class="text-decoration-none" @click="showPostCode" role="button">
                                        <span class="input-group-text bg-white border-md border-left-0 rounded-right" style="border-radius: 0">
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
                                <input id="address1" type="text" name="address1" placeholder="Address 1" v-model="member.address1" class="form-control bg-white border-md border-left-0" required />
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
                                <input id="address2" type="text" name="address2" placeholder="Address 2" v-model="member.address2" class="form-control bg-white border-md border-left-0" required />
                            </div>

                            <!-- Submit Button -->
                            <div class="form-group col-lg-12 mx-auto mb-0">
                                <button class="btn btn-primary btn-block py-2" type="submit">
                                    <span class="font-weight-bold">Update your account</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const member = JSON.parse('<%= request.getAttribute("member") %>');
        const errors = JSON.parse('<%= request.getAttribute("errors")%>');

        console.log(errors);

        const app = new Vue({
            el: "#app",
            data: {
                newPassword: "",
                passwordConfirmation: "",
                member: {
                    ...member
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
                            e.target.setCustomValidity(response.ok ? "" : "이미 존재하는 이메일입니다.");
                        });
                },
                checkDuplicatePhoneNumber: function (e) {
                    fetch("/is-duplicate?phoneNumber=" + this.member.phoneNumber)
                        .then(response => response.json())
                        .then(response => {
                            console.log(response);
                            e.target.setCustomValidity(response.ok ? "" : "이미 존재하는 전화번호입니다.");
                        });
                },
                checkPassword: function (e) {
                    this.newPassword && e.target.setCustomValidity(this.newPassword === this.passwordConfirmation ?
                        "" : "비밀번호가 올바르지 않습니다.");
                },
                showPostCode: function (e) {
                    new daum.Postcode({
                        oncomplete: (data) => {
                            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                            let addr = ''; // 주소 변수
                            let extraAddr = ''; // 참고항목 변수

                            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                addr = data.roadAddress;
                            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                addr = data.jibunAddress;
                            }

                            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                            if (data.userSelectedType === 'R') {
                                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                                    extraAddr += data.bname;
                                }
                                // 건물명이 있고, 공동주택일 경우 추가한다.
                                if (data.buildingName !== '' && data.apartment === 'Y') {
                                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                                }
                                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                                if (extraAddr !== '') {
                                    extraAddr = ' (' + extraAddr + ')';
                                }

                            } else {
                                extraAddr = '';
                            }

                            // 우편번호와 주소 정보를 해당 필드에 넣는다.
                            this.member.zip = data.zonecode;
                            this.member.address1 = addr;
                            this.member.address2 = extraAddr;
                            // 커서를 상세주소 필드로 이동한다.
                        }
                    }).open();
                }
            }
        });
    </script>

    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>
