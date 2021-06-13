angular.module('website', ['ngRoute']).
    config(function ($routeProvider) {
        $routeProvider.
            when('/home', {templateUrl: 'partials/home.html', controller: 'HomeCtrl'}).
            otherwise({redirectTo: '/login'});
    })
    .controller('HomeCtrl', function ($scope, StateService) {
        $scope.title = 'Welcome to Control Panel';
        $scope.body = 'Use the side Options to edit the contents of your website';

        $scope.message = StateService.getMessage();

        $scope.updateMessage = function (m) {
            StateService.setMessage(m);
        };
    });