/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 22/04/17 - 20:43
 */

angular.module('main').controller('IndexController', ['$scope', '$http', 'authenticationService', function ($scope, $http, authenticationService) {

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
        } else if ($scope.typeUser === 'Prestador de serviços') {
            registerProvider();
        } else {
            showNotification("Por favor selecione um tipo de usuário!");
        }
    };

    $scope.entry = function () {

        $http.post("/login", $scope.user).then(function success(response) {

                var personAuth = {};
                personAuth.id = response.data.id;
                personAuth.type = response.data.type;
                authenticationService.persist(JSON.stringify(personAuth));

                if (response.data.type === "CLIENT") {
                    $('#clientLogin').trigger('click');
                } else {
                    $('#providerLogin').trigger('click');
                }


            }, function error(response) {
                showNotification("Usuário ou senha inválidos!");
            }
        );
    }
    ;

    var registerClient = function () {
        $scope.client.name = $scope.person.name;
        $scope.client.lastName = $scope.person.lastName;
        $scope.client.cpf = $scope.person.cpf;
        $scope.client.telephone = $scope.person.telephone;
        $scope.client.email = $scope.person.email;
        $scope.client.user = $scope.user;

        $http.post("/client", $scope.client).then(function (response) {
            showNotification("Cliente cadastrado com sucesso!");
            clearVariablesNotUser();
        }, function (response) {
            showNotification("Erro ao cadastrar! \n Verifique se todos os dados foram preenchidos corretamente!", 4000);
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
            showNotification("Prestador cadastrado com sucesso! \n Você receberá em breve um email informando que " +
                "seu cadastro foi aceito! \n Obrigado", 6000);
            clearVariablesNotUser();
        }, function (response) {
            showNotification("Erro ao cadastrar! \n Verifique se todos os dados foram preenchidos corretamente!", 4000);
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
}])
;