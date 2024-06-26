angular.module("application").controller("perfilPessoaController", function ($scope, $location, cadastroFactory, contatoFactory, $routeParams){

    let carregarCadastro = function () {
        cadastroFactory.getCadastro($routeParams.id).then(function (response){
            $scope.pessoa = response.data;
        });
    };

    let carregarTabelaContatos = function (){
        contatoFactory.getPessoaContatos($routeParams.id).then(function (response){
            $scope.contatos = response.data;
        });
    };

    carregarCadastro();
    carregarTabelaContatos();

    $scope.removerCadastro = function(){
        cadastroFactory.removeCadastro($routeParams.id).then(function (response){
            $location.path("/listaPessoas");
        });
    };

    $scope.criarContato = function (){
        $location.path("/cadastroContato/" + $routeParams.id);
    };

    $scope.cancelar = function () {
        $location.path("/listaPessoas");
    };

    $scope.enviarCadastro = function(cadastro){
        cadastroFactory.editCadastro(cadastro).then(function (response){
                $location.path("/listaPessoas");
        });
    };
});