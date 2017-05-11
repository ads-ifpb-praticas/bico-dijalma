/**
 * Created by dijalma on 08/05/17.
 */

angular.module('main').controller('ProviderController',
    ['$scope', 'locateUser', '$http', '$rootScope', 'providerService',
        function ($scope, locateUser, $http, $rootScope, providerService) {

            locateUser.findUser();
            var tab = 4;
            var showNewBid = false;
            $scope.bid = {};
            $scope.bids = [];

            $scope.services = {
                'ELECTRIC': 'Elétrica',
                'HYDRAULIC': 'Hidráulico',
                'PAINT': 'Pintura',
                'MASONRY': 'Alvenaria',
                'REPAIR': 'Reparo'
            };

            $scope.diary = [];
            $scope.jobsOpenTypeService = [];
            $scope.typeOfService = "";

            // var findDiary = providerService.getDiary($rootScope.userAuth.id);
            //
            // findDiary.then(function (response) {
            //     $scope.diary = response;
            // }, function (response) {
            //     $scope.diary = [];
            // });

            $scope.findJobsOpen = function (showNotif) {

                if ($scope.typeOfService !== "") {
                    $http.get("/job//provider/" + $rootScope.userAuth.id + "/" + $scope.typeOfService).then(function (response) {
                        $scope.jobsOpenTypeService = response.data;
                        if ($scope.lessEqualsThanZero(response.data)) {
                            if (showNotif) {
                                showNotification("Não há serviços disponíves desse tipo!");
                            }
                        }
                    }, function (response) {
                        $scope.jobsOpenTypeService = [];
                    });
                } else {
                    showNotification("Selecione o tipo do serviço, por favor!");
                }
            };

            $scope.newBid = function () {
                console.log($scope.bid);
                $http.post("/bid/", $scope.bid).then(function (response) {
                    $scope.closeShowNewBid();
                    $scope.findJobsOpen(false);
                    showNotification("Proposta realizada com sucesso!");
                }, function (response) {
                    showNotification("Não foi possível fazer a proposta!");
                    console.log("Erro:" + response.data);
                });
            };

            $scope.setShowNewBid = function (job) {
                $scope.bid.job = job;
                $scope.bid.value = job.willingToPay;
                $http.get("" + $rootScope.userAuth.id).then(function (response) {
                    $scope.bid.provider = response.data;
                    showNewBid = true;
                }, function (response) {
                    showNewBid = false;
                    showNotification("Erro");
                })
            };

            $scope.closeShowNewBid = function () {
                $scope.bid = {};
                showNewBid = false;
            };

            $scope.getShowNewBid = function () {
                return showNewBid;
            };

            $scope.setTab = function (value) {
                if (value === undefined || value === null) {
                    tab = 4;
                } else {
                    if (value === 5) {
                        $scope.getYOurBids();
                    }

                    tab = value;
                }
            };

            $scope.getTabEquals = function (value) {
                return tab === value;
            };

            $scope.lessEqualsThanZero = function (value) {
                return value <= 0;
            };

            $scope.getYOurBids = function () {
                $http.get("/bid//provider/" + $rootScope.userAuth.id).then(function (response) {
                    $scope.bids = response.data;
                }, function (response) {
                    $scope.bids = [];
                    showNotification("Não foi possível buscar suas propostas!");
                });
            }
        }]);