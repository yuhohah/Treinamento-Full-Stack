angular.module("application").factory("contatoFactory", function($http){

    const _getContatos = function (){
        return $http.get("http://localhost:8080/desafio/start/contato");
    };

    const _getContato = function (id){
        return $http.get("http://localhost:8080/desafio/start/contato/" + id);
    }

    const _getPessoaContatos = function (id){
      return $http.get("http://localhost:8080/desafio/start/contatos/" + id);
    };

    const _saveContato = function (contato){
        return $http.post("http://localhost:8080/desafio/start/contato/", contato);
    };

    const _editContato = function (contato){
        return $http.put("http://localhost:8080/desafio/start/contato/", contato);
    };

    const _removeContato = function (id){
        return $http.delete("http://localhost:8080/desafio/start/contato/" + id);
    };

    return{
        getContato: _getContato,
        getPessoaContatos: _getPessoaContatos,
        getContatos: _getContatos,
        saveContato: _saveContato,
        removeContato: _removeContato,
        editContato: _editContato
    };
});