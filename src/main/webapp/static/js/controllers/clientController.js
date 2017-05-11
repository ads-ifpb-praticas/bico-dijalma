/**
 * Created by dijalma on 05/05/17.
 */

angular.module('main').controller('ClientController', ['$scope', 'locateUser', '$http', '$rootScope', function ($scope, locateUser, $http, $rootScope) {

    var updateImage = false;
    var tab = 4;
    var viewBids = false;

    $scope.services = {
        'ELECTRIC': 'Elétrica',
        'HYDRAULIC': 'Hidráulico',
        'PAINT': 'Pintura',
        'MASONRY': 'Alvenaria',
        'REPAIR': 'Reparo'
    };

    $scope.job = {};
    $scope.jobsClosed = [];
    $scope.jobsOpen = [];
    $scope.jobsFinish = [];
    $scope.bids = [];

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
        } else if (value === 2) {
            $scope.findJobsClosed();
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
            $scope.jobsOpen = response.data;
            for (var i = 0; i < $scope.jobsOpen.length; i++) {
                $scope.getBidFromJob($scope.jobsOpen[i].id);
            }
        }, function (response) {
            $scope.jobsOpen = [];
        });
    };

    $scope.getBidFromJob = function (idJob) {
        $http.get("/bid/job/" + idJob).then(function (response) {
            $scope.bids[idJob] = response.data;
        }, function (response) {
            console.log(response);
        })
    };

    $scope.showBidsOfJob = function (bids) {
        $scope.bidsJob = bids;
        viewBids = true;
    };

    $scope.showBidsFromJob = function () {
        return viewBids;
    };

    $scope.closeBidsOfJob = function () {
        $scope.bidsJob = [];
        viewBids = false;
    };

    $scope.confirmBid = function (bid) {
        console.log(bid);
        $http.put("/bid/accept/" + bid.id, bid).then(function (response) {
            console.log(response.data);
            showNotification("Proposta aceitada!");
            $scope.closeBidsOfJob();
            $scope.getJobsOpen();
        }, function (response) {
            showNotification("Não foi possível aceitar essa proposta!");
        })
    };

    $scope.findJobsClosed = function () {

        $http.get("/job/close/client/" + $rootScope.userAuth.id).then(function (response) {
            $scope.jobsClosed = response.data;
        }, function (response) {
            $scope.jobsClosed = [];
            showNotification("Não foi possível buscar os serviços fechados!");
        });
    };
}]);