/**
 * Created by dijalma on 06/05/17.
 */

angular.module('main').controller('AdminController', ['$http', 'locateUser', '$scope', function ($http, locateUser, $scope) {

    locateUser.findUser();
}]);