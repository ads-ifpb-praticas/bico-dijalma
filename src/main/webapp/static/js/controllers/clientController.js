/**
 * Created by dijalma on 05/05/17.
 */

angular.module('main').controller('ClientController', ['$scope', 'locateUser', '$http', '$rootScope', function ($scope, locateUser, $http, $rootScope) {

    var updateImage = false;
    var tab = 4;

    $scope.services = {
        'ELECTRIC': 'Elétrica',
        'HYDRAULIC': 'Hidráulico',
        'PAINT': 'Pintura',
        'MASONRY': 'Alvenaria',
        'REPAIR': 'Reparo'
    };

    $scope.job = {};
    $scope.jobsOpen = [];
    $scope.jobsClose = [];
    $scope.jobsFinish = [];

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
        if (value === 1) {
            $scope.getJobsOpen();
        }
        if (value === undefined || value === null) {
            tab = 4;
        } else {
            tab = value;
        }
    };

    $scope.getTabEquals = function (value) {
        return tab === value;
    };

    $scope.newJobRequested = function () {

        $http.get("/client/" + $rootScope.userAuth.id).then(function (response) {

            $scope.job.client = response.data;
            $http.post("/job", $scope.job).then(function (response) {
                showNotification("Serviço criado e disponível para prestadores!", 4000);
                $scope.job = {};
            }, function (response) {
                showNotification("Não foi possível criar serviço!");
            });
        });
    };

    $scope.lessEqualsThanZero = function (value) {
        return value <= 0;
    };

    $scope.getJobsOpen = function () {
        $http.get("/job/open/client/" + $rootScope.userAuth.id).then(function (response) {
            console.log(response.data);
            $scope.jobsOpen = response.data;
        }, function (response) {
            $scope.jobsOpen = [];
        });
    };
}]);