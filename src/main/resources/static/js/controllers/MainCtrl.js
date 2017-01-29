'use strict';

/**
 * Main controller
 */
Crawler.app.controllers.controller('main',
    ['$scope', 'MainService', '$timeout',
        function ($scope, MainService, $timeout) {
            $scope.show = false;
            $scope.treeData = [];

            $scope.url = 'http://www.simplesite.com/';
            $scope.submit = function () {
                $scope.show = !$scope.show;
                $scope.treeData = [];
                // $scope.refresh();
                MainService.getPages($scope.url, function (response) {
                    console.info(response);
                    $scope.show = !$scope.show;
                    $scope.treeData = response.data;
                    $scope.refresh();
                });
            };

            $scope.ignoreChanges = false;
            var newId = 0;
            $scope.ignoreChanges = false;
            $scope.newNode = {};

            $scope.basicConfig = {
                core: {
                    multiple: false,
                    check_callback: true,
                    worker: true
                },
                types: {
                    folder: {
                        icon: 'glyphicon glyphicon-plus'
                    },
                    default: {
                        icon: 'glyphicon glyphicon-link'
                    }
                },
                plugins: ['types'],
                version: 1
            };

            $scope.refresh = function () {
                $scope.ignoreChanges = true;
                newId = 0;
                // $scope.treeData = getDefaultData();
                $scope.basicConfig.version++;
            };

            $scope.expand = function () {
                $scope.ignoreChanges = true;
                $scope.treeData.forEach(function (n) {
                    n.state.opened = true;
                });
                $scope.basicConfig.version++;
            };

            $scope.collapse = function () {
                $scope.ignoreChanges = true;
                $scope.treeData.forEach(function (n) {
                    n.state.opened = false;
                });
                $scope.basicConfig.version++;
            };

            $scope.readyCB = function () {
                $timeout(function () {
                    $scope.ignoreChanges = false;
                });
            };


            $scope.applyModelChanges = function () {
                return !$scope.ignoreChanges;
            };
        }
    ]
);