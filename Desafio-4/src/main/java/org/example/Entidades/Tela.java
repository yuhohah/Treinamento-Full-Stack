package org.example.Entidades;

import org.example.DAOs.DAO;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Tela {

    private DAO dao = new DAO();

    private ArrayList<Pessoa> listaPessoas;

    Scanner input = new Scanner(System.in);

    public void menu() {
        listaPessoas = (ArrayList<Pessoa>) dao.findAll();

        System.out.println("\nCadastro de usuário\n"+
                "Digite o que deseja fazer\n"+
                "1. Cadastrar\n"+
                "2. Editar\n"+
                "3. Excluir\n"+
                "4. Visualizar\n"+
                "5. Sair\n");

        int opcao = 0;
        while((opcao = pegarEntradaInt()) > 5 || opcao < 1){
            System.out.println("Opção invalida");
        }

        switch(opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                input.nextLine();
                editar();
                break;
            case 3:
                input.nextLine();
                excluir();
                break;
            case 4:
                visualizar();
                break;
            case 5:
                dao.closeDAO();
                break;
        }
    }

    public void cadastrar() {
        Pessoa cadastro = new Pessoa();

        cadastro.setNome(input.nextLine());
        System.out.print("Insira o nome:");
        cadastro.setNome(validarString(input.nextLine()));
        System.out.print("Insira o CPF(Apenas numeros):");
        cadastro.setCpf(validarCpf());
        System.out.print("Insira o telefone:");
        cadastro.setTelefone(validarTelefone());
        System.out.print("Insira o email:");
        cadastro.setEmail(validarEmail());
        System.out.print("Insira o CEP:");
        cadastro.getEndereco().setCep(validarString(input.nextLine()));
        System.out.print("Insira o Bairro:");
        cadastro.getEndereco().setBairro(validarString(input.nextLine()));
        System.out.print("Insira o Rua:");
        cadastro.getEndereco().setRua(validarString(input.nextLine()));
        System.out.print("Insira o numero:");
        cadastro.getEndereco().setNumero(input.nextLine());

        System.out.print("Deseja Cadastrar quantos contatos? Minimo de 2: ");
        int quantidadeDeContatos = 0;
        while ((quantidadeDeContatos = pegarEntradaInt()) < 2) {
            System.out.print("Insira no minimo 2 contatos");
        }
        input.nextLine();

        for (int contador = 0; contador < quantidadeDeContatos; contador++){
            System.out.print("\nInsira o nome:");
            String nome = validarString(input.nextLine());
            System.out.print("Insira o telefone:");
            String telefone = validarTelefone();
            System.out.print("Insira o email:");
            String email = validarEmail();

            cadastro.criarContato(nome, telefone, email);
        }

        dao.insert(cadastro);
        menu();
    }

    public void visualizar() {
        if(!listaPessoas.isEmpty()) {
            printarPessoas();
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
        }
        menu();
    }

    public void editar(){

        if(!listaPessoas.isEmpty()) {
            printarPessoas();

            System.out.println("\nDigite o id do cadastro que deseja editar:");
            int id = pegarEntradaInt();

            Pessoa pessoa = dao.findById(id);

            while(pessoa == null) {
                System.out.print("Cadastro nao encontrado digite novamente o id: ");
                id = pegarEntradaInt();
                pessoa = dao.findById(id);
            }

            System.out.println("\nInira os Novos Dados\n");

            input.nextLine();
            System.out.print("Insira o nome:");
            pessoa.setNome(validarString(input.nextLine()));
            System.out.print("Insira o CPF(Apenas numeros):");
            pessoa.setCpf(validarCpf());
            System.out.print("Insira o telefone:");
            pessoa.setTelefone(validarTelefone());
            System.out.print("Insira o email:");
            pessoa.setEmail(validarEmail());
            System.out.print("Insira o CEP:");
            pessoa.getEndereco().setCep(validarString(input.nextLine()));
            System.out.print("Insira o Bairro:");
            pessoa.getEndereco().setBairro(validarString(input.nextLine()));
            System.out.print("Insira o Rua:");
            pessoa.getEndereco().setRua(validarString(input.nextLine()));
            System.out.print("Insira o numero:");
            pessoa.getEndereco().setNumero(input.nextLine());

            System.out.print("Deseja editar algum contato?(SIM)");
            if (input.nextLine().equals("SIM")) {
                for (Contato contato : pessoa.getListaContatos()) {
                    System.out.print("\nInsira novo para " + contato.getNome() + ":");
                    contato.setNome(validarString(input.nextLine()));
                    System.out.print("Insira o novo telefone:");
                    contato.setTelefone(validarTelefone());
                    System.out.print("Insira o novo email:");
                    contato.setEmail(validarEmail());
                }
            }

            System.out.print("Insira a quantidade de novos contatos que deseja adicionar:");
            int quantidadeDeContatos = pegarEntradaInt();

            for (int contador = 0; contador < quantidadeDeContatos; contador++) {
                System.out.print("\nInsira o nome:");
                String nome = validarString(input.nextLine());
                System.out.print("Insira o telefone:");
                String telefone = validarTelefone();
                System.out.print("Insira o email:");
                String email = validarEmail();
                pessoa.criarContato(nome, telefone, email);
            }

            dao.insert(pessoa);
        }
        else{
            System.out.println("\nNão há usuários cadastrados no banco de dados!\n");
        }
        menu();
    }

    public void excluir(){

        if(!listaPessoas.isEmpty()){
            printarPessoas();
            System.out.println("\nDigite o id do cadastro que deseja excluir:");
            int id = input.nextInt();

            Pessoa pessoa = dao.findById(id);
            if(pessoa == null){
                System.out.println("Cadastro nao encontrado!");
            } else {
                dao.remove(pessoa);
                System.out.println("Cadastro removido com SUCESSO!");
            }
        }
        else {
            System.out.println("\nNão há usuários cadastrados no banco de dados!\n");
        }

        menu();
    }

    public void printarPessoas(){
        for(Pessoa pessoa: listaPessoas){
            System.out.print(pessoa.toString() + "\n\nCONTATOS\n" + pessoa.toStringContatos() +
                    "\n-------------------------------------------");
        }
    }

    public int pegarEntradaInt(){

        while (!input.hasNextInt()) {
            System.out.println("Porfavor insira um numero.");
            input.next();
        }

        return input.nextInt();
    }

    public String validarCpf(){
        String cpf = input.nextLine();
        while(cpf.length() < 11){
            System.out.print("Cpf invalido insira novamente:");
            cpf = input.nextLine();
        }

        int[] vet = new int[11];
        int posicao = 0, digito1 = 0, digito2 = 0, posicaoString;

        for(posicaoString = 0; posicaoString < 11; posicaoString++){
            vet[posicao] = Character.getNumericValue(cpf.charAt(posicaoString));
            posicao++;
        }

        posicao = 0;
        for(int contador = 1; contador < 10; contador++){
            digito1 += vet[posicao] * contador;
            digito2 += vet[posicao+1] * contador;
            posicao++;
        }

        if((digito1 = digito1 % 11) == 10){
            digito1 = 0;
        }
        if((digito2 = digito2 % 11) == 10){
            digito2 = 0;
        }

        if(vet[9] == digito1 && vet[10] == digito2){
            return cpf;
        }

        System.out.print("Cpf invalido insira novamente:");
        return validarCpf();
    }

    public String validarEmail(){
        String email = input.nextLine();

        if(email != null && !email.isEmpty()){
            String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
                return email;
            }
        }
        System.out.print("Email invalido insira novamente:");
        return validarEmail();
    }

    public String validarTelefone(){
        String telefone = input.nextLine();

        if(telefone!= null && !telefone.isEmpty()){
            String expression = "^((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(telefone);
            if(matcher.matches()){
                return telefone;
            }
        }
        System.out.print("Telefone invalido insira novamente:");
        return validarTelefone();
    }

    public String validarString(String str){
        while(str.isEmpty()){
            System.out.println("Entrada invalida!");
            str = input.nextLine();
        }
        return str;
    }
}