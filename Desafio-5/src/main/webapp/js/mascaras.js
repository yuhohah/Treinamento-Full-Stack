function mascara(objeto, mascara){
    obj = objeto;
    fun = mascara;
    setTimeout("functionMascaraExecutar()", 1);
}

function functionMascaraExecutar(){
    obj.value=fun(obj.value);
}

function mascaraCpf(cpf){
    cpf = cpf.replace(/\D/g,"");
    cpf = cpf.replace(/(\d{3})(\d)/,"$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/,"$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{2})$/,"$1-$2");
    return cpf;

}

function mascaraCep(cep){
    cep = cep.replace(/\D/g, "");
    cep = cep.replace(/(\d{5})(\d)/, "$1-$2");
    return cep
}

function mascaraNumero(numero){
    numero = numero.replace(/\D/g, "");
    return numero
}

function mascaraTelefone(telefone){
    telefone = telefone.replace(/\D/g,"");
    telefone = telefone.replace(/^(\d{2})(\d)/g,"($1) $2");
    telefone = telefone.replace(/(\d)(\d{4})$/,"$1-$2");
    return telefone;
}
