var pessoa = {};

const requestOptions = {
    method: 'GET',
    headers: {
        "Content-Type": "application/json",
    }
}

const deleteOptions = {
    method: 'DELETE',
    headers: {
        "Content-Type": "application/json",
    }
}

var rootSQLURL = "http://localhost:8080/desafio/start/pessoa"

async function send(){
    if(pessoa.hasOwnProperty("id")){
        await editSend();
    }
    else{
        await create();
    }
}

async function listAll(){
    const response = await fetch(rootSQLURL, requestOptions);
    removerLinhasDaTabela();

    const data = await response.json();
    Object.keys(data).forEach((key) => {
        $('#tabela').append(
            '<tr id="linha'+data[key].id+'" class="linhas">'+
            '<td id="nomeTabela'+data[key].id+'">'+data[key].nome+'</td>'+
            '<td id="emailTabela'+data[key].id+'">'+data[key].email+'</td>'+
            '<td id="cpfTabela'+data[key].id+'">'+mascaraCpf(data[key].cpf)+'</td>'+
            '<td><button class= "but" onclick="editTabela('+data[key].id+')">Editar</button></td>'+
            '<td><button class= "but" onclick="deletePessoa('+data[key].id+')">Excluir</button></td>'+
            '</tr>')
    });
    document.getElementById('tabela').style.visibility = "visible";
}

async function create(){
    pessoa.nome = document.getElementById('inputName').value;
    pessoa.email = document.getElementById('inputEmail').value;
    pessoa.cpf = document.getElementById('inputCpf').value;
    pessoa.telefone = document.getElementById('inputTel').value;
    pessoa.endereco = {}
    pessoa.endereco.cep = document.getElementById('inputCep').value;
    pessoa.endereco.numero = document.getElementById('inputNumero').value;
    pessoa.endereco.bairro = document.getElementById('inputBairro').value;
    pessoa.endereco.rua = document.getElementById('inputRua').value;

    pessoa.contatos = []
    for(let contador = 1; contador <= contadorDeContatos; contador++){
        let nome = document.getElementById('inputName'+contador).value;
        let email = document.getElementById('inputEmail'+contador).value;
        let telefone = document.getElementById('inputTel'+contador).value;

        pessoa.contatos.push({
            nome: nome,
            email: email,
            telefone: telefone
        });
    }

    if(validarCadastro(pessoa)){
            const response = await fetch(rootSQLURL,{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: (JSON.stringify(pessoa)),
            });

            const code = response.status;
            if(code === 406){
                const message = await response.text();
                alert(message);
            }
            else{
                const data = await response.json();
                removerLinhasDaTabela();
                listAll();
                removerContatos();
                limparInputs();
                pessoa = {};
            }
    }
}

async function editTabela(id){
    if(pessoa.id != null || pessoa.id !== undefined){
        return alert("Termine de editar a pessoa atual!");
    }
    const response = await fetch(rootSQLURL + "/" + id, requestOptions);
    pessoa = await response.json();

    document.getElementById('inputName').value = pessoa.nome;
    document.getElementById('inputEmail').value = pessoa.email;
    document.getElementById('inputCpf').value = mascaraCpf(pessoa.cpf);
    document.getElementById('inputTel').value = mascaraTelefone(pessoa.telefone);
    document.getElementById('inputCep').value = mascaraCep(pessoa.endereco.cep);
    document.getElementById('inputBairro').value = pessoa.endereco.bairro;
    document.getElementById('inputRua').value = pessoa.endereco.rua;
    document.getElementById('inputNumero').value = pessoa.endereco.numero;

    removerContatos();

    let quantidadeDeContatos = pessoa.contatos.length;
    for(let cont = 2; cont < quantidadeDeContatos; cont++){
        inserirContato();
    }

    for(let contador = 1; contador <= quantidadeDeContatos; contador++){
        document.getElementById('inputName'+contador).value = pessoa.contatos[contador-1].nome;
        document.getElementById('inputEmail'+contador).value = pessoa.contatos[contador-1].email;
        document.getElementById('inputTel'+contador).value = mascaraTelefone(pessoa.contatos[contador-1].telefone);
    }

    removerLinhaTabela(id);
}

async function editSend(){
    pessoa.nome = document.getElementById('inputName').value;
    pessoa.email = document.getElementById('inputEmail').value;
    pessoa.cpf = document.getElementById('inputCpf').value;
    pessoa.telefone = document.getElementById('inputTel').value;
    pessoa.endereco.cep = document.getElementById('inputCep').value;
    pessoa.endereco.numero = document.getElementById('inputNumero').value;
    pessoa.endereco.bairro = document.getElementById('inputBairro').value;
    pessoa.endereco.rua = document.getElementById('inputRua').value;

    var contador = 1;
    pessoa.contatos.forEach(contato => {
        contato.nome = document.getElementById('inputName'+contador).value;
        contato.email = document.getElementById('inputEmail'+contador).value;
        contato.telefone = document.getElementById('inputTel'+contador).value;

        contador++;
    })
    while(contadorDeContatos >= contador){
        let nome = document.getElementById('inputName'+contador).value;
        let email = document.getElementById('inputEmail'+contador).value;
        let telefone = document.getElementById('inputTel'+contador).value;

        pessoa.contatos.push({
            id: undefined,
            nome: nome,
            email: email,
            telefone: telefone
        });
        contador++;
    }

    if(validarCadastro(pessoa)) {
        const response = await fetch(rootSQLURL, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: (JSON.stringify(pessoa)),
        });

        const code = response.code;
        if(code === 406){
            const message = await response.text();
            alert(message);
        }
        else{
            const data = await response.json();
            removerLinhasDaTabela();
            await listAll();
            removerContatos();
            limparInputs();
            pessoa = {};
        }
    }
}

async function deletePessoa(id){
    const response = await fetch(rootSQLURL + "/" + id, deleteOptions);
    const code = response.status;
    if(code === 200){
        removerLinhaTabela(id);
        console.log("Deletado com sucesso");
    }
    else{
        console.log("Erro ao deletar");
    }
}
