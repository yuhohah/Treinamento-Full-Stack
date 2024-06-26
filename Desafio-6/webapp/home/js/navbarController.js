angular.module("application").controller("navbarController", function($scope, $location) {

    $scope.buscarPessoaId = function (id) {
        $location.path("/perfilPessoa/" + id);
    }
});