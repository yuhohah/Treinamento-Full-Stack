angular.module("application").controller("editContatoController", function(contatoFactory, $scope, $routeParams, $location) {

    let carregarContato = function (){
        contatoFactory.getContato($routeParams.contatoId).then(function (response) {
            $scope.contato = response.data;
        }
    )};

    carregarContato();

    $scope.removeContato = function (id){
        contatoFactory.removeContato(id).then(function (response){
                ok();
        });
    };

    $scope.editContato = function (contato){
        contato.pessoa = {};
        contato.pessoa.id = $routeParams.pessoaId;
        contatoFactory.editContato(contato).then( function (response){
            ok();
        });
    };

    let ok = function (){
        $location.path("/perfilPessoa/" + $routeParams.pessoaId);
    };
});