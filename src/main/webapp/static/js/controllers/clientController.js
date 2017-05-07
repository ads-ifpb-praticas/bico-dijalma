/**
 * Created by dijalma on 05/05/17.
 */

angular.module('main').controller('ClientController', ['$scope', 'locateUser', '$http', function ($scope, locateUser, $http) {

    var updateImage = false;
    var tab = 4;

    $scope.job = {};

    locateUser.findUser();

    $scope.showSetImage = function () {
        return updateImage;
    };

    $scope.setUpdateImage = function (value) {

        if (value === undefined || value === null) {
            updateImage = !updateImage;
        } else {
            updateImage = value;
        }
    };

    $scope.setTab = function (value) {
        if (value === undefined || value === null) {
            tab = 1;
        } else {
            tab = value;
        }
    };

    $scope.getTabEquals = function (value) {
        console.log(value, tab);
        return tab === value;
    };

    $scope.newJobRequested = function () {

        $http.post("/job").then(function (response) {
            showNotification("Serviço criado e disponível para prestadores!", 4000);
        }, function (response) {
            showNotification("Não foi possível criar serviço!");
        });
    };
}]);