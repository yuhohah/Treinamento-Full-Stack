var contadorDeContatos = 2
var contadorDeCadastros = 0
var listaCadastros = [];

function send(){

    event.preventDefault()
    var cadastro = {};
    cadastro.name = document.getElementById('inputName').value;
    cadastro.email = document.getElementById('inputEmail').value;
    cadastro.cpf = document.getElementById('inputCpf').value;
    cadastro.endereco = document.getElementById('inputEndereco').value;
    cadastro.tel = document.getElementById('inputTel').value;


    cadastro.contatos = []
    for(let contador = 1; contador <= contadorDeContatos; contador++){
        let name1 = document.getElementById('inputName'+contador).value;
        let email1 = document.getElementById('inputEmail'+contador).value;
        let tel1 = document.getElementById('inputTel'+contador).value;

        if(!validarEmail(email1)){
            alert("Email do Contato " + contador + " inválido");
            return;
        }
        if(!validarTelefone(tel1)){
            alert("telefone do Contato " + contador + " invalido");
            return;
        }
        if(!validarInputs(name1)){
            alert("Nome do Contato "+ contador + " não pode ser em branco!");
            return;
        }

        cadastro.contatos.push({
            Nome: name1,
            Email: email1,
            Telefone: tel1
                        });
    }
 
    if(validarCadastro(cadastro)){
        listaCadastros.push(cadastro);
        console.log(listaCadastros);
        adicionarTabela(cadastro.name, cadastro.email, cadastro.cpf);
        removerContatos();
        limparInputs();
    }
}

document.addEventListener('click', function(event) {
    if (event.target.id === 'buttonSend'){
        send();
    }
    if(event.target.id === 'buttonAdd'){
        inserirContato();
    }
});

document.addEventListener('keydown', function(event) {
    if(event.key != 'Backspace' && event.key != 'Delete'){
        if(event.target.id === 'inputCpf'){
            document.getElementById('inputCpf').value = document.getElementById('inputCpf').value.replace(/[^0-9.-]/g,'');
            var tamanho = document.getElementById('inputCpf').value.length;
            if(tamanho === 3 || tamanho === 7){
                document.getElementById('inputCpf').value = document.getElementById('inputCpf').value + '.';
            }
            if(tamanho === 11){
                document.getElementById('inputCpf').value = document.getElementById('inputCpf').value + '-';
            }
        }

        if((event.target.id).includes('inputTel')){
            var tamanho = document.getElementById(event.target.id).value.length;
            document.getElementById(event.target.id).value = document.getElementById(event.target.id).value.replace(/[^0-9()-]/g,'');
            if(tamanho === 1){
                document.getElementById(event.target.id).value = '(' + document.getElementById(event.target.id).value;
            }
            if(tamanho === 3){
                document.getElementById(event.target.id).value = document.getElementById(event.target.id).value + ')';
            }
            if(tamanho === 9){
                document.getElementById(event.target.id).value = document.getElementById(event.target.id).value + '-';
            }
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
                        '<input type="tel" id="inputTel'+contadorDeContatos+'" class="captcha" required placeholder="(00) 00000-0000">'+
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

function validarCadastro(cadastro){
    if(!validarCpf(cadastro.cpf)){
        alert("CPF Inválido");
        return false;
    }
    else if(!validarEmail(cadastro.email)){
        alert("Email do cadastro Inválido");
        return false;
    }
    else if(!validarTelefone(cadastro.tel)){
        alert("Telefone do cadastro Inválido");
        return false;
    }
    else if(!validarInputs(cadastro.endereco)){
        alert("Endereço do cadastro não pode ser em branco");
        return false;
    }
    else if(!validarInputs(cadastro.name)){
        alert("Nome do cadastro não pode ser em branco");
        return false;
    }

    return true;
}

function validarCpf(strCPF) {
    var soma = 0;
    var resto;

    if (strCPF === "00000000000" || strCPF === "11111111111" || strCPF === "22222222222" 
    || strCPF === "33333333333" || strCPF === "44444444444" || strCPF === "555555555555" 
    || strCPF === "66666666666" || strCPF === "77777777777" || strCPF === "88888888888" 
    || strCPF === "99999999999" ) {
        return false;
    }

    strCPF = strCPF.replace(/[^0-9]/g,'');

    for (let contador=1; contador<=9; contador++) soma = soma + parseInt(strCPF.substring(contador-1, contador)) * (11 - contador);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))  resto = 0;
    if (resto !== parseInt(strCPF.substring(9, 10)) ) return false;

    for (let contador = 1; contador <= 10; contador++) soma = soma + parseInt(strCPF.substring(contador-1, contador)) * (12 - contador);
    resto = (soma * 10) % 11;

    if ((resto === 10) || (resto === 11))  resto = 0;

    return resto === parseInt(strCPF.substring(10, 11));
}

function validarEmail(email) {
    var padrao = /\S+@\S+\.\S+/;
    return padrao.test(email);
}

function validarTelefone(telefone){
    telefone = telefone.replace(/[^0-9]/g,'');
    if(telefone.length < 10 || telefone.length >= 12){
        return false;
    }
    return true;
}

function validarInputs(texto){
    if(texto.length < 3){
        return false;
    }
    return true;
}

function adicionarTabela(name, email, cpf){
    document.getElementById('tabela').style.visibility = "visible";
    $('#tabela').append(
        '<tr id="linha'+contadorDeCadastros+'">'+
        '<td type="text" id="nomeTabela'+contadorDeCadastros+'">'+name+'</td>'+
        '<td type="text" id="emailTabela'+contadorDeCadastros+'">'+email+'</td>'+
        '<td type="text" id="cpfTabela'+contadorDeCadastros+'">'+cpf+'</td>'+
        '<td><button class= "but" onclick="editTabela('+contadorDeCadastros+')">Editar</button></td>'+
        '<td><button class= "but" onclick="removerTabela('+contadorDeCadastros+')">Excluir</button></td>'+
        '</tr>');
        contadorDeCadastros++;
}

function removerTabela(numero){
    var linha = document.getElementById("linha"+numero);
    linha.parentNode.removeChild(linha);
    listaCadastros[numero] = null;
}

function editTabela(numero){
    document.getElementById('inputName').value = listaCadastros[numero].name;
    document.getElementById('inputEmail').value = listaCadastros[numero].email;
    document.getElementById('inputCpf').value = listaCadastros[numero].cpf;
    document.getElementById('inputEndereco').value = listaCadastros[numero].endereco;
    document.getElementById('inputTel').value = listaCadastros[numero].tel;

    let tamanho = listaCadastros[numero].contatos.length;
    for(let cont = 2; cont < tamanho; cont++){
        inserirContato();
    }
    let index = 0;
    for(let contador = 1; contador <= contadorDeContatos; contador++){
        document.getElementById('inputName'+contador).value = listaCadastros[numero].contatos[index].Nome;
        document.getElementById('inputEmail'+contador).value = listaCadastros[numero].contatos[index].Email;
        document.getElementById('inputTel'+contador).value = listaCadastros[numero].contatos[index].Telefone;
        index++;
    }
    listaCadastros[numero] = null;
    removerTabela(numero);
}