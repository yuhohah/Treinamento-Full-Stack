angular.module("application").factory("loadingInterceptor", function($q, $rootScope, $timeout) {
    return{
        request: function (config){
            $rootScope.loading = true;
            return config;
        },
        requestError: function (rejection){
            $rootScope.loading = false;
            return $q.reject(rejection);
        },
        response: function (response){
            $timeout(function () {
                $rootScope.loading = false;
            }, 800);
            return response;
        },
        responseError:function (rejection){
            $rootScope.loading = false;
            return $q.reject(rejection);
        }
    };
});

