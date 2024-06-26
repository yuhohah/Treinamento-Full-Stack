var contadorDeContatos = 2

document.addEventListener('click', function(event) {
    if (event.target.id === 'buttonSend'){
        event.preventDefault();
        send().then();
    }
    if(event.target.id === 'buttonAdd'){
        inserirContato();
    }
    if(event.target.id === 'buttonList'){
        event.preventDefault();
        listAll().then();
    }

});

document.addEventListener('keydown', function(event) {
    if(event.key !== 'Backspace' && event.key !== 'Delete'){
        if(event.target.id === 'inputCpf'){
            mascara(event.target, mascaraCpf);
        }
        if((event.target.id).includes('inputTel')){
            mascara(event.target, mascaraTelefone);
        }
        if(event.target.id === 'inputCep'){
            mascara(event.target, mascaraCep);
        }
        if(event.target.id === 'inputNumero'){
            mascara(event.target, mascaraNumero);
        }
    }
});

function inserirContato(){ 
    contadorDeContatos++;
    $('#forms').append( '<form class="form" id="form'+contadorDeContatos+'">'+
                        '<label for="inputName'+contadorDeContatos+'" class="contact">Contato '+contadorDeContatos+':</label>'+
                        '<label for="inputName'+contadorDeContatos+'">Nome:</label><br>'+
                        '<input type="text" id="inputName'+contadorDeContatos+'" class="captcha" required placeholder="Joao Domingues"><br>'+
                        '<label for="inputTel'+contadorDeContatos+'">Telefone:</label><br>'+
                        '<input type="tel" id="inputTel'+contadorDeContatos+'" class="captcha" required placeholder="(00) 00000-0000" maxlength="15">'+
                        '<label for="inputEmail'+contadorDeContatos+'">Email:</label><br>'+
                        '<input type="email" id="inputEmail'+contadorDeContatos+'" class="captcha" required placeholder="Quinckk marcshal">'+
                        '</form>');
}

function removerContatos(){
    while(contadorDeContatos > 2){
        var contact = document.getElementById("form"+contadorDeContatos);
        contact.parentNode.removeChild(contact);
        contadorDeContatos--;
    }
}

function limparInputs(){
    let inputs = document.getElementsByClassName('captcha');
    for(let contador = 0; contador < inputs.length; contador++){
        inputs[contador].value = '';
    }
}

function validarCadastro(pessoa){
    if(validarInputs(pessoa.nome) || validarInputs(pessoa.cep) || validarInputs(pessoa.bairro)
        || validarInputs(pessoa.rua)){
        alert("Campos obrigatorios em branco");
        return false;
    }
    else if(!validarTelefone(pessoa.telefone)){
        alert("Telefone Inválido");
        return false;
    }
    else if(!validarEmail(pessoa.email)){
        alert("Email Inválido");
        return false;
    }
    else if(!validarCpf(pessoa.cpf)){
        alert("CPF Inválido");
        return false;
    }
    pessoa.contatos.forEach(contato => {
        if(validarInputs(contato.nome)){
            alert("Nome de Contato não pode ser em branco!");
            return false;
        }
        if(!validarEmail(contato.email)){
            alert("Email do Contato " + contato.nome + " inválido");
            return false;
        }
        if(!validarTelefone(contato.telefone)){
            alert("telefone do Contato " + contato.nome + " invalido");
            return false;
        }
    })
    return true;
}

function validarCpf(strCPF) {
    var soma = 0;
    var resto;
    strCPF = strCPF.replace(/[^0-9]/g,'');

    if (strCPF === "00000000000" || strCPF === "11111111111" || strCPF === "22222222222" 
    || strCPF === "33333333333" || strCPF === "44444444444" || strCPF === "555555555555" 
    || strCPF === "66666666666" || strCPF === "77777777777" || strCPF === "88888888888" 
    || strCPF === "99999999999" ) {
        return false;
    }

    for (let contador=1; contador<=9; contador++) soma = soma + parseInt(strCPF.substring(contador-1, contador)) * (11 - contador);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))  resto = 0;
    if (resto !== parseInt(strCPF.substring(9, 10)) ) return false;

    soma = 0;

    for (let contador = 1; contador <= 10; contador++) soma = soma + parseInt(strCPF.substring(contador-1, contador)) * (12 - contador);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))  resto = 0;

    if(resto != parseInt(strCPF.substring(10, 11))){
        return false;
    }

    return true;
}

function validarEmail(email) {
    var padrao = /\S+@\S+\.\S+/;
    return padrao.test(email);
}

function validarTelefone(telefone){
    telefone = telefone.replace(/[^0-9]/g,'');
    return !(telefone.length < 9 || telefone.length >= 12);
}

function validarInputs(texto){
    return texto === "";
}

function removerLinhaTabela(id){
    var linha = document.getElementById("linha"+id);
    linha.parentNode.removeChild(linha);
}

function removerLinhasDaTabela(){
    $("#itemsTabela").empty();
}
