angular.module("application").factory("cadastroFactory", function ($http){

    const _getCadastros = function (){
        return $http.get("http://localhost:8080/desafio/start/pessoa");
    };

    const _getCadastro = function (id){
        return $http.get("http://localhost:8080/desafio/start/pessoa/" + id);
    }

    const _saveCadastro = function (cadastro){
        return $http.post("http://localhost:8080/desafio/start/pessoa", cadastro);
    };

    const _editCadastro = function (cadastro){
        return $http.put("http://localhost:8080/desafio/start/pessoa/", cadastro);
    };

    const _removeCadastro = function (id){
        return $http.delete("http://localhost:8080/desafio/start/pessoa/" + id);
    };

    return{
        getCadastro: _getCadastro,
        getCadastros: _getCadastros,
        saveCadastro: _saveCadastro,
        removeCadastro: _removeCadastro,
        editCadastro: _editCadastro
    };
});