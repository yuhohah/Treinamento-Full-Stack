angular.module("application").controller("cadastroContatoController", function(contatoFactory, $scope, $routeParams, $location){

    $scope.salvarContato = function (contato){
        contato.pessoa = {};
        contato.pessoa.id = $routeParams.id;
        contatoFactory.saveContato(contato, $routeParams.id).then( function (response){
            $location.path("/perfilPessoa/" + $routeParams.id);
        });
    };

    $scope.cancelar = function (){
        $location.path("/perfilPessoa/" + $routeParams.id);
    };

});