<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" class="container" v-cloak>
        <div class="row mt-4">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Your cart</span>
                    <span class="badge badge-secondary badge-pill">{{ order.orderDetails.length }}</span>
                </h4>

                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed" v-for="orderDetail in order.orderDetails">
                        <div>
                            <h6 class="my-0">{{ orderDetail.product.name }}</h6>
                            <small class="text-muted">Size - {{ orderDetail.productSize }} / Color - {{ orderDetail.productColor }} / Quantity - {{ orderDetail.productQuantity }}</small>
                        </div>
                        <span class="text-muted">&#8361; {{ orderDetail.totalPrice }}</span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (KRW)</span>
                        <strong>&#8361; {{ order.totalPrice }}</strong>
                    </li>
                </ul>
            </div>

            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Billing address</h4>

                <div class="mb-3">
                    <label for="billingFirstName">Full name</label>
                    <input type="text" class="form-control" id="billingFirstName" v-model="order.billingName" required>
                </div>

                <div class="mb-3">
                    <label for="billingPhone">Phone</label>
                    <input type="tel" class="form-control" id="billingPhone" v-model="order.billingPhoneNumber" required>
                </div>

                <div class="mb-3">
                    <label for="billingPost">Zip</label>
                    <div class="row col-5">
                        <input type="text" class="form-control col-7 mr-1" id="billingPost" v-model="order.billingZip" required>
                        <button class="btn btn-primary col-4" @click="findAddress(true)">
                            <i class="material-icons">search</i>
                        </button>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="billingAddr1">Address</label>
                    <input type="text" class="form-control" id="billingAddr1" v-model="order.billingAddress1" required>
                </div>

                <div class="mb-3">
                    <label for="billingAddr2">Address 2</label>
                    <input type="text" class="form-control" id="billingAddr2" v-model="order.billingAddress2" required>
                </div>

                <hr class="mb-4">

                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="same-address" v-model="isSameClicked" @change="copyBilling">
                    <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
                </div>

                <hr class="mb-4">

                <form class="needs-validation" @submit="moveToOrder">
                    <h4 class="mb-3">Shipping address</h4>

                    <div class="mb-3">
                        <label for="orderName">Full name</label>
                        <input type="text" class="form-control" id="orderName" v-model="order.shippingName" required>
                    </div>

                    <div class="mb-3">
                        <label for="phone">Phone</label>
                        <input type="tel" class="form-control" id="phone" v-model="order.shippingPhoneNumber" required>
                    </div>

                    <div class="mb-3">
                        <label for="post">Zip</label>
                        <div class="row col-5">
                            <input type="text" class="form-control col-7 mr-1" id="post" v-model="order.shippingZip" required>
                            <button class="btn btn-primary col-4" @click="findAddress(false)">
                                <i class="material-icons">search</i>
                            </button>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="addr1">Address</label>
                        <input type="text" class="form-control" id="addr1" v-model="order.shippingAddress1" required>
                    </div>

                    <div class="mb-3">
                        <label for="addr2">Address 2</label>
                        <input type="text" class="form-control" id="addr2" v-model="order.shippingAddress2" required>
                    </div>

                    <hr class="mb-4">

                    <h4 class="mb-3">Payment</h4>

                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="credit" name="payMethod" type="radio" class="custom-control-input" v-model="order.paymentMethod" value="credit" required>
                            <label class="custom-control-label" for="credit">Credit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="debit" name="payMethod" type="radio" class="custom-control-input" v-model="order.paymentMethod" value="debit" required>
                            <label class="custom-control-label" for="debit">Debit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="paypal" name="payMethod" type="radio" class="custom-control-input" v-model="order.paymentMethod" value="paypal" required>
                            <label class="custom-control-label" for="paypal">PayPal</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-name">Name on card</label>
                            <input type="text" class="form-control" id="cc-name" placeholder="" required>
                            <small class="text-muted">Full name as displayed on card</small>
                            <div class="invalid-feedback">
                                Name on card is required
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cc-number">Credit card number</label>
                            <input type="text" class="form-control" id="cc-number" placeholder="" required>
                            <div class="invalid-feedback">
                                Credit card number is required
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-3 mb-3">
                            <label for="cc-expiration">Expiration</label>
                            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cc-cvv">CVV</label>
                            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                            <div class="invalid-feedback">
                                Security code required
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
                </form>
            </div>
        </div>
    </div>

    <script type="module">
        const order = JSON.parse('<%= request.getAttribute("order") %>');

        console.log(order)

        const app = new Vue({
            el: "#app",
            data: {
                order: order,
                isSameClicked: false,
            },
            methods: {
                findAddress: function (isBilling) {
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

                            if (isBilling) {
                                this.order.billingZip = data.zonecode;
                                this.order.billingAddress1 = addr;
                                this.order.billingAddress2 = extraAddr;
                            } else {
                                this.order.shippingZip = data.zonecode;
                                this.order.shippingAddress1 = addr;
                                this.order.shippingAddress2 = extraAddr;
                            }
                        }
                    }).open();
                },
                copyBilling: function () {
                    this.order.shippingName = "";
                    this.order.shippingPhoneNumber = "";
                    this.order.shippingZip = "";
                    this.order.shippingAddress1 = "";
                    this.order.shippingAddress2 = "";

                    if (this.isSameClicked) {
                        this.order.shippingName = this.order.billingName;
                        this.order.shippingPhoneNumber = this.order.billingPhoneNumber;
                        this.order.shippingZip = this.order.billingZip;
                        this.order.shippingAddress1 = this.order.billingAddress1;
                        this.order.shippingAddress2 = this.order.billingAddress2;
                    }
                },
                moveToOrder: function (e) {
                    e.preventDefault();

                    fetch("/auth/order", {
                        headers: {
                            "Content-Type": "application/json; charset=utf-8",
                            "${_csrf.headerName}": "${_csrf.token}",
                            "X-Requested-With": "XMLHttpRequest"
                        },
                        method: "post",
                        body: JSON.stringify(this.order)
                    })
                    .then(response => response.json())
                    .then(response => {
                        if (response.ok)
                            swal("Success", response.data, "success")
                            .then(() => location.href = "/auth/order/history");
                        else
                            swal("Failure", Object.values(response.data).join("\n"), "error");
                    })
                }
            }
        })
    </script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>
