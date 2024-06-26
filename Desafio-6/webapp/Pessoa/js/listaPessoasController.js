angular.module("application").controller("listaPessoasController", function ($scope, cadastroFactory, $location){

    let carregarTabelaCadastros = function (){
        cadastroFactory.getCadastros().then(function (response){
            $scope.cadastros = response.data;
        });
    };

    $scope.carregarPerfilPessoa = function (cadastro){
      $location.path("/perfilPessoa/" + cadastro.id);
    };

    $scope.removerCadastros = function(cadastros){
        cadastros.forEach(cadastro => {
            if(cadastro.selecionado === true){
                cadastroFactory.removeCadastro(cadastro.id).then(function (response){
                    carregarTabelaCadastros();
                });
            }
        });
    };

    carregarTabelaCadastros();
});