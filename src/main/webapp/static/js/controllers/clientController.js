/**
 * Created by dijalma on 05/05/17.
 */

angular.module('main').controller('ClientController', ['$scope', 'locateUser', '$http', function ($scope, locateUser, $http) {

    var updateImage = false;

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
}]);