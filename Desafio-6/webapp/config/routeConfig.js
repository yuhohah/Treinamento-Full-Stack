angular.module("application").config(function ($routeProvider){
    $routeProvider
        .when("/cadastroPessoa", {
            templateUrl: "/Pessoa/html/perfilPessoa.html",
            controller:"cadastroPessoaController",
        })
        .when("/listaPessoas", {
            templateUrl: "/Pessoa/html/listaPessoas.html",
            controller:"listaPessoasController",
        })
        .when("/perfilPessoa/:id", {
            templateUrl: "/Pessoa/html/perfilPessoa.html",
            controller:"perfilPessoaController",
        })
        .when("/perfilContato/pessoa/:pessoaId/contato/:contatoId", {
            templateUrl: "/Contato/html/formContato.html",
            controller:"editContatoController",
        })
        .when("/cadastroContato/:id", {
            templateUrl: "/Contato/html/formContato.html",
            controller:"cadastroContatoController",

        })
        .otherwise({redirectTo: "/listaPessoa"});
});