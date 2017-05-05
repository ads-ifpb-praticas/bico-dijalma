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

    var keys = {
        8: "backspace",
        35: "end",
        36: "home",
        37: "left",
        39: "right",
        46: "delete"
    };

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

    $scope.isClient = function () {
        return $scope.typeUser === "Cliente";
    };

    $scope.registerNewUser = function () {
        if ($scope.typeUser === 'Cliente') {
            registerClient();
        } else if ($scope.typeUser === 'Fornecedor') {
            registerProvider();
        }
    };

    var registerClient = function () {
        $scope.client.name = $scope.person.name;
        $scope.client.lastName = $scope.person.lastName;
        $scope.client.cpf = $scope.person.cpf;
        $scope.client.telephone = $scope.person.telephone;
        $scope.client.email = $scope.person.email;
        $scope.client.user = $scope.user;

        $http.post("/client", $scope.client).then(function (response) {
            if (response.status === 200) {
                alert("Cadastrado com sucesso!");
                clearVariablesNotUser();
            } else {
                alert("Não foi possível cadastrar!");
            }
            console.log(response);
        });
    };

    var registerProvider = function () {
        $scope.provider.name = $scope.person.name;
        $scope.provider.lastName = $scope.person.lastName;
        $scope.provider.cpf = $scope.person.cpf;
        $scope.provider.telephone = $scope.person.telephone;
        $scope.provider.email = $scope.person.email;
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

    $scope.maskCPF = function (event) {

        var code = event.keyCode;
        if (keys[code] === undefined) {
            var cpf = $scope.person.cpf;
            if (cpf !== undefined) {
                var length = cpf.length;

                if (length === 3 || length === 7) {
                    cpf += ".";
                } else if (length === 11) {
                    cpf += "-";
                }

                $scope.person.cpf = cpf;
            }
        }
    };

    $scope.maskTel = function (event) {
        var code = event.keyCode;
        if (keys[code] === undefined) {

            var tel = $scope.person.telephone;
            if (tel !== undefined) {
                var length = tel.length;

                if (length === 1) {
                    tel = "(" + tel;
                } else if (length === 3) {
                    tel += ") ";
                } else if (length === 10) {
                    tel += "-";
                }

                $scope.person.telephone = tel;
            }
        }
    }
}]);