<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <!-- Common Head -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />

<body>
    <jsp:include page="/WEB-INF/views/common/nav.jsp" />

    <div id="app" v-cloak>
        <div class="container mt-3">
            <div class="row">
                <div class="col-md-4 mb-4"  v-for="product in products">
                    <div class="card border-0">
                        <div class="p-3">
                            <img class="img-fluid w-100"
                                 :src="'/images/goods/' + product.image + '.gif'" alt="Product Image">
                        </div>
                        <div class="text-center pt-4">
                            <h6>{{ product.name }}</h6>
                            <p class="mb-2 text-muted text-uppercase small">{{ product.category.name }}</p>
                            <hr>
                            <h6 class="mb-3">&#8361; {{ product.price }}</h6>
                            <button type="button" @click="moveToDetail(product)" class="btn btn-primary btn-sm mr-1 mb-2">
                                <i class="material-icons pr-2" style="font-size: medium; vertical-align: middle; padding-bottom: 3px">info</i>
                                See Details
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="module">
        const products = JSON.parse('<%= request.getAttribute("products") %>')

        const app = new Vue({
            el: "#app",
            data: {
                products: products
            },
            methods: {
                moveToDetail: function (product) {
                    location.href = `/product/detail?productId=\${product.id}`;
                }
            }
        });
    </script>
</body>
</html>
