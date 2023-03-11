angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';



    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.filter();
            });
    }

    $scope.changePrice = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_price',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.filter();
        });
    }



    $scope.filter = function () {
        $http({
            url: contextPath + '/products',
            method: 'get',
            params: {
                min: $scope.filter.min,
                max: $scope.filter.max,
                page: $scope.filter.page
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    }


    $scope.filter();
});