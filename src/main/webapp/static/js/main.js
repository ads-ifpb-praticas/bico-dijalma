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
            } else if (personAuth.type === "PROVIDER") {
                $http.get("/provider/" + personAuth.id).then(function (response) {
                    $rootScope.userAuth = response.data;
                });
            } else {
                $http.get("/admin/" + personAuth.id).then(function (response) {
                    $rootScope.userAuth = response.data;
                });
            }
        }
    }
});

app.factory('providerService', function ($http, $rootScope, $q) {

    return {

        getDiary: function (idProvider) {
            $http.get("/provider/diary/{id}").then(function (response) {

            }, function (response) {

            });
        },

        getJobsOpenTypeService: function (typeOfService) {
            var deferred = $q.defer();

        },

        getYourJobsClose: function (idProvider) {

            $http.get("/job/provider/close/" + idProvider).then(function (response) {
                return response;
            }, function (response) {

            });
        }
    }
});

app.filter('filterToday', function () {

    return function (items) {
        var arrayReturn = [];
        var today = createDateToString(new Date());

        for (var i = 0; i < items.length; i++) {
            var date = items[i].dealDate;
            if (date === today) {
                arrayReturn.push(items[i]);
            }
        }

        return arrayReturn;
    }
});

app.filter('filterTomorrow', function () {

    return function (items) {
        var arrayReturn = [];
        var today = new Date();
        var date = new Date();
        date.setDate(today.getDate() + 1);
        var tomorrow = createDateToString(date);

        for (var i = 0; i < items.length; i++) {
            var date = items[i].dealDate;
            if (date === tomorrow) {
                arrayReturn.push(items[i]);
            }
        }

        return arrayReturn;
    }
});

function createDateToString(date) {
    var day = date.getDate();
    var month = (date.getMonth() + 1) > 9 ? '' : '0' + (date.getMonth() + 1);
    var year = date.getFullYear();
    date = year + "-" + month + "-" + day;
    return date;
}