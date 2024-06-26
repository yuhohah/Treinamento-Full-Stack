angular.module("application").controller("cadastroPessoaController", function ($scope, cadastroFactory, $location){

    $scope.enviarCadastro = function(cadastro){
        cadastroFactory.saveCadastro(cadastro).then(function (response){
            $location.path("/perfilPessoa/" + response.data);
        });
    };
});