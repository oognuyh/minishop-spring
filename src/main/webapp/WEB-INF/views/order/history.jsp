<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" class="container" v-cloak>
        <div class="mb-3">
            <div class="pt-4 wish-list">
                <h5 class="mb-4">
                    Order History (<span>{{ orders.length }}</span> items)
                </h5>

                <div class="row mb-4" v-for="order in orders">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header bg-dark">
                                <strong class="text-white"># {{ order.id }}</strong>
                            </div>
                            <div class="card-body">
                                <div class="row" v-for="orderDetail in order.orderDetails">
                                    <div class="col-md-5 col-lg-3 col-xl-3">
                                        <div class="mb-md-0 p-3">
                                            <img class="img-fluid"
                                                 :src=`/images/goods/\${orderDetail.product.image}.gif` alt="Sample">
                                        </div>
                                    </div>

                                    <div class="d-flex flex-column">
                                        <h5 class="mb-2">{{ orderDetail.product.name }}</h5>
                                        <p class="mb-2 text-muted text-uppercase small">Color: {{ orderDetail.productColor }}</p>
                                        <p class="mb-2 text-muted text-uppercase small">Size: {{ orderDetail.productSize }}</p>
                                        <p class="mb-2 text-muted text-uppercase small">Quantity: {{ orderDetail.productQuantity }}</p>
                                        <p class="mb-2 text-muted text-uppercase small">Total Price: {{ orderDetail.totalPrice }}</p>
                                    </div>
                                </div>

                                <hr class="col-10" />

                                <table class="col-12">
                                    <tbody class="table-borderless">
                                    <tr>
                                        <td>배송지 이름</td>
                                        <td class="text-muted">{{ order.shippingName }}</td>
                                    </tr>
                                    <tr>
                                        <td>배송지 전화번호</td>
                                        <td class="text-muted">{{ order.shippingPhoneNumber }}</td>
                                    </tr>
                                    <tr>
                                        <td>배송지 주소</td>
                                        <td class="text-muted">{{ order.shippingAddress1 + ", " + order.shippingAddress2 }}</td>
                                    </tr>
                                    <tr>
                                        <td>결제수단</td>
                                        <td class="text-muted">{{ order.paymentMethod }}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card-footer d-flex justify-content-end bg-dark">
                                <span class="text-white">&#8361; {{ order.totalPrice }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        const orders = JSON.parse('<%= request.getAttribute("orders") %>');

        new Vue({
            el: "#app",
            data: {
                orders: orders
            }
        });
    </script>
</body>
</html>
