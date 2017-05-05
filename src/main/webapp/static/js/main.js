/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 02/03/17 - 18:05
 */

var app = angular.module('main', []);

app.factory('authenticationService', function ($window) {

    return {

        persist: function (user) {
            $window.localStorage.setItem('user', JSON.stringify(user));
        },

        delete: function () {
            $window.localStorage.removeItem('user');
        },

        find: function () {
            return $window.localStorage.getItem('user');
        }
    }
});

app.factory('locateUser', function (authenticationService, $http, $rootScope) {


    return {
        findUser: function () {

            var persist = JSON.parse(authenticationService.find());
            var personAuth = JSON.parse(persist);

            if (personAuth.type === "CLIENT") {
                $http.get("/client/" + personAuth.id).then(function (response) {
                    $rootScope.userAuth = response.data;
                });
            } else {
                $http.get("/provider/" + personAuth.id).then(function (response) {
                    $rootScope.userAuth = response.data;
                });
            }
        }
    }
});