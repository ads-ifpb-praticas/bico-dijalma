/**
 * Created by dijalma on 08/05/17.
 */

angular.module('main').controller('ProviderController',
    ['$scope', 'locateUser', '$http', '$rootScope', 'providerService',
        function ($scope, locateUser, $http, $rootScope, providerService) {

            locateUser.findUser();
            var tab = 4;

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

            $scope.findJobsOpen = function () {

                if ($scope.typeOfService !== "") {
                    $http.get("/job/type/" + $scope.typeOfService).then(function (response) {
                        $scope.jobsOpenTypeService = response.data;
                        console.log(response.data);
                        if ($scope.lessEqualsThanZero(response.data)) {
                            showNotification("Não há serviços disponíves desse tipo!");
                        }
                    }, function (response) {
                        $scope.jobsOpenTypeService = [];
                    });
                } else {
                    showNotification("Selecione o tipo do serviço, por favor!");
                }
            };

            $scope.newBid = function (job) {
                job.bid = $scope.bid;
                $http.put("/job/" + job.id, job).then(function (response) {
                    console.log(response.data);
                }, function (response) {
                    console.log("Erro:" + response.data);
                });
            };

            $scope.setTab = function (value) {
                if (value === undefined || value === null) {
                    tab = 4;
                } else {
                    tab = value;
                }
            };

            $scope.getTabEquals = function (value) {
                return tab === value;
            };

            $scope.lessEqualsThanZero = function (value) {
                return value <= 0;
            };
        }]);