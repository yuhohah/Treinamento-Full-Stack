angular.module("application").config(function ($httpProvider) {
    $httpProvider.interceptors.push("errorInterceptor");
    $httpProvider.interceptors.push("loadingInterceptor");
});