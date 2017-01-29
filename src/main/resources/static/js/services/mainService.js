'use strict';

Crawler.app.services.service('MainService',
    ['$http', 'Config',
        function ($http, Config) {
            this.getPages = function (url, callback) {
                $http({
                    method: 'POST',
                    url: Config.BASE_URL + '/rest',
                    data: url,
                    headers: {'Content-Type': 'application/json'}
                }).then(function (response) {
                    callback(response);
                }, function (response) {
                    callback(response);
                });
            };
        }
    ]
);
