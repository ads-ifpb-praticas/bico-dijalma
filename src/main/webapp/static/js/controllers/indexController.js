/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 22/04/17 - 20:43
 */

angular.module('main').controller('IndexController', ['$scope', function ($scope) {

    $scope.login = true;

    $scope.isLogin = function () {
        return $scope.login;
    };

    $scope.isRegister = function () {
        return $scope.login == false;
    };

    $scope.setLogin = function (newValue) {
        if (newValue == null) {
            $scope.login = !$scope.login;
        } else {
            $scope.login = newValue;
        }
    }
}]);