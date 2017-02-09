var TaskManagerApp = angular.module('TaskManagerApp', ['ngRoute', 'ngResource', 'ngStorage', 'ngDialog']);
TaskManagerApp.config(function ($routeProvider, $locationProvider) {
    $routeProvider


            .when('/tasks', {
                templateUrl: '/view/tasks.html',
                controller: 'tasksCtrl'
            })
            .when('/tasks/insert', {
                templateUrl: '/view/tasks.html',
                controller: 'tasksCtrl'
            })
            .when('/tasks/update',{
                templateUrl : '/view/tasks.html',
                controller: 'tasksCtrl'
            })
            .otherwise(
                    {redirectTo: '/'}
            );

            $locationProvider.html5Mode(true);
});
