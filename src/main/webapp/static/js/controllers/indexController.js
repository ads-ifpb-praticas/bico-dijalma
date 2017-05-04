/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 22/04/17 - 20:43
 */

angular.module('main').controller('IndexController', ['$scope', '$http', function ($scope, $http) {

    $scope.login = true;
    $scope.person = {};
    $scope.client = {};
    $scope.provider = {};
    $scope.user = {};
    $scope.typeUser = "";

    var clearVariablesNotUser = function () {
        $scope.login = true;
        $scope.person = {};
        $scope.client = {};
        $scope.provider = {};
        $scope.typeUser = "";
    };

    $scope.isLogin = function () {
        return $scope.login;
    };

    $scope.isRegister = function () {
        return $scope.login === false;
    };

    $scope.setLogin = function (newValue) {
        if (newValue === null) {
            $scope.login = !$scope.login;
        } else {
            $scope.login = newValue;
        }
    };

    $scope.registerNewUser = function () {
        if ($scope.typeUser === 'Cliente') {
            registerClient();
        } else if ($scope.typeUser === 'Fornecedor') {
            registerProvider();
        }
    };

    var registerClient = function () {
        $scope.client.person = $scope.person;
        $scope.client.user = $scope.user;

        $http.post("/client", $scope.client).then(function (response) {
            if (response.status === 200) {
                console.log("Cadastrado com sucesso!");
                clearVariablesNotUser();
            } else {
                console.log("Não foi possível cadastrar!");
            }
            console.log(response);
        });
    };

    var registerProvider = function () {
        $scope.provider.person = $scope.person;
        $scope.provider.user = $scope.user;

        $http.post("/provider", $scope.provider).then(function (response) {
            if (response.status === 200) {
                console.log("Cadastrado com sucesso!");
                clearVariablesNotUser();
            } else {
                console.log("Não foi possível cadastrar!");
            }
            console.log(response);
        });
    };

}]);