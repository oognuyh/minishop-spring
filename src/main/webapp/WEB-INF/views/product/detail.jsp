<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" v-cloak>
        <div class="container">
            <div class="row p-4">
                <div class="col-md-6 mb-4 mb-md-0">
                    <div class="row product-gallery mx-1">
                        <div class="col-12">
                            <img :src=`/images/goods/\${product.image}.gif`
                                 class="img-fluid z-depth-1 w-100">
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <h6>{{ product.name }}</h6>
                    <p class="mb-2 text-muted text-uppercase small">{{ product.category }}</p>
                    <p><span class="mr-1"><strong>&#8361; {{ product.price }}</strong></span></p>
                    <p class="pt-1">{{ product.description }}</p>
                    <div class="table-responsive">
                        <table class="table table-sm table-borderless mb-0">
                            <tbody>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Code</strong></th>
                                <td>{{ product.id }}</td>
                            </tr>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Color</strong></th>
                                <td>Black / Navy / Ivory / White / Gray</td>
                            </tr>
                            <tr>
                                <th class="pl-0 w-25" scope="row"><strong>Delivery</strong></th>
                                <td>South korea Only</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <hr>
                    <div class="col-12 mb-2">
                        <table class="table table-sm table-borderless">
                            <tbody>
                            <tr>
                                <td class="pl-0 pb-0">Quantity</td>
                                <td class="pb-0">Select Color</td>
                                <td class="pb-0">Select size</td>
                            </tr>
                            <tr>
                                <td class="pl-0">
                                    <div class="col-8 def-number-input number-input safari_only mb-0 text-center">
                                        <button class="btn btn-light btn-sm" @click="increaseQuantity">
                                            <i class="material-icons">arrow_drop_up</i>
                                        </button>
                                        <input style="width: 45px; padding-left: 4px" min="1" name="quantity" :value="cart.productQuantity">
                                        <button class="btn btn-light btn-sm" @click="decreaseQuantity">
                                            <i class="material-icons">arrow_drop_down</i>
                                        </button>
                                    </div>
                                </td>
                                <td>
                                    <div class="mt-1">
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="black" name="color" v-model="cart.productColor" value="black">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="black">black</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="navy" name="color" v-model="cart.productColor" value="navy">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="navy">navy</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="ivory" name="color" v-model="cart.productColor" value="ivory">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="ivory">ivory</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="white" name="color" v-model="cart.productColor" value="white">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="white">white</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="gray" name="color" v-model="cart.productColor" value="gray">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="gray">gray</label>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="mt-1">
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="small" name="size" value="S"
                                                   v-model="cart.productSize">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="small">Small</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="medium" name="size" value="M"
                                                   v-model="cart.productSize">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="medium">Medium</label>
                                        </div>
                                        <div class="form-check form-check-inline pl-0">
                                            <input type="radio" class="form-check-input" id="large" name="size" value="L"
                                                   v-model="cart.productSize">
                                            <label class="form-check-label small text-uppercase card-link-secondary"
                                                   for="large">Large</label>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <button type="button" class="btn btn-primary btn-md mr-1 mb-2" @click="buy">
                        <i class="material-icons pr-2" style="vertical-align: middle; padding-bottom: 3px">paid</i>Buy
                    </button>
                    <button type="button" class="btn btn-light btn-md mr-1 mb-2" @click="addToCart">
                        <i class="material-icons pr-2" style="vertical-align: middle; padding-bottom: 3px">add_shopping_cart</i>Add to cart
                    </button>
                </div>
            </div>

        </div>
    </div>

    <script type="module">
        const product = JSON.parse('<%= request.getAttribute("product") %>');

        const app = new Vue({
            el: "#app",
            data: {
                product: product,
                cart: {
                    productQuantity: 1,
                    productColor: "black",
                    productSize: "S",
                    productId: product.id,
                    product: product
                }
            },
            methods: {
                increaseQuantity: function () {
                    this.cart.productQuantity++;
                },
                decreaseQuantity: function () {
                    this.cart.productQuantity > 1 && this.cart.productQuantity--;
                },
                addToCart: function () {
                    fetch("/cart", {
                        method: "post",
                        body: JSON.stringify(this.cart)
                    })
                    .then(response => response.text())
                    .then(stauts => alert(stauts));
                },
                buy: function () {
                    fetch("/checkout", {
                        method: "post",
                        body: JSON.stringify([this.cart])
                    })
                    .then(response => response.text())
                    .then(status => {
                        if (status === "signin")
                            location.href = "/signin";
                        else if (status === "success")
                            location.href = "/checkout";
                        else
                            alert(status);
                    });
                }
            }
        })
    </script>
</body>
</html>
