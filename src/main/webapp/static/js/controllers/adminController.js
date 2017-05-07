/**
 * Created by dijalma on 06/05/17.
 */

angular.module('main').controller('AdminController', ['$http', 'locateUser', '$scope', function ($http, locateUser, $scope) {

    locateUser.findUser();

    $scope.providers = [];

    var searchProviders = function () {

        $http.get("/provider/inactive").then(function (response) {
            $scope.providers = response.data;
        });
    };

    searchProviders();

    $scope.activateProvider = function (idProvider) {

        $http.get('/admin/accept/' + idProvider).then(function (response) {
            console.log(response);
            showNotification('Prestador ativado com sucesso!');
            searchProviders();
        }, function (response) {
            console.log(response);
            showNotification("Não foi possível ativar!");
            searchProviders();
        });
    }
}]);