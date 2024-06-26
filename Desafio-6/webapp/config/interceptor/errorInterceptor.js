angular.module("application").factory("errorInterceptor", function($q, $location) {
    return{
        responseError: function(rejection){
            if(rejection.status !== 200){
                if(rejection.status === 404){
                    alert("Não foi encontrado a pagina ou o cadastro!");
                }
                if(rejection.status === 500){
                    alert("Erro no servidor verificar o log!");
                }
                return $location.path("/listaPessoas");
            }
        }
    };
});