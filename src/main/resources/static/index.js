angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };


    $scope.addToCart = function (productId) {
        $http.get(contextPath + '/products/add/' + productId)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.loadCart = function () {
        $http.get(contextPath + '/products/cart')
            .then(function (response) {
                $scope.CartPage = response.data;
            });
    }

    $scope.loadProducts();
});