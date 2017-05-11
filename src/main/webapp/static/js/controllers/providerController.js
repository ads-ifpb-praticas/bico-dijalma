/**
 * Created by dijalma on 08/05/17.
 */

angular.module('main').controller('ProviderController',
    ['$scope', 'locateUser', '$http', '$rootScope', 'providerService',
        function ($scope, locateUser, $http, $rootScope, providerService) {

            locateUser.findUser();
            var tab = 4;
            var diaryTab = 4;
            var showNewBid = false;
            $scope.bid = {};
            $scope.bids = [];
            $scope.jobs = [];
            $scope.bidsAll = [];
            $scope.bidsToday = [];
            $scope.bidsTomorrow = [];
            $scope.jobsFinish = [];
            $scope.jobsOpenTypeService = [];
            $scope.typeOfService = "";

            $scope.services = {
                'ELECTRIC': 'Elétrica',
                'HYDRAULIC': 'Hidráulico',
                'PAINT': 'Pintura',
                'MASONRY': 'Alvenaria',
                'REPAIR': 'Reparo'
            };


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
                    if (value === 2) {
                        $scope.getYourBids();
                    } else if (value === 4) {
                        $scope.getJobsClose();
                        createFiltersDate();
                    } else if (value === 3) {
                        $scope.getJobsFinish();
                    }

                    tab = value;
                }
            };

            $scope.setTabDiary = function (value) {
                if (value === undefined || value === null) {
                    diaryTab = 1;
                } else {
                    diaryTab = value;
                }
            };

            $scope.getTabEquals = function (value) {
                return tab === value;
            };

            $scope.diaryTabEquals = function (value) {
                return diaryTab === value;
            };

            $scope.lessEqualsThanZero = function (value) {
                return value <= 0;
            };

            $scope.getYourBids = function () {
                $http.get("/bid//provider/" + $rootScope.userAuth.id).then(function (response) {
                    $scope.bids = response.data;
                }, function (response) {
                    $scope.bids = [];
                    showNotification("Não foi possível buscar suas propostas!");
                });
            };

            $scope.getJobsClose = function () {
                $http.get("/job/close/provider/" + $rootScope.userAuth.id).then(function (response) {
                    $scope.jobs = response.data;
                }, function (response) {
                    $scope.jobs = [];
                    console.log(response.data);
                    showNotification("Não foi possível atualizar a agenda!!");
                });
            };

            $scope.getJobsFinish = function () {

                $http.get("/job/finish/provider/" + $rootScope.userAuth.id).then(function (response) {
                    $scope.jobsFinish = response.data;
                }, function (response) {
                    $scope.jobsFinish = [];
                    showNotification("Não foi possível buscar os serviços finalizados!");
                });
            };

            var createFiltersDate = function () {
                $scope.date = new Date();
                var tomorrow = new Date();
                $scope.tomorrow = tomorrow.setDate(tomorrow.getDate() + 1);
            }
        }]);