package org.example;

import org.example.DAO.ControleDAO;
import org.example.Entidades.Contato;
import org.example.Entidades.Pessoa;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Tela {

    private ControleDAO controleDAO = new ControleDAO();

    private ArrayList<Pessoa> listaPessoas;

    Scanner input = new Scanner(System.in);

    public void menu() {
        listaPessoas = controleDAO.readAll();

        System.out.println("\nCadastro de usuário\n"+
                "Digite o que deseja fazer\n"+
                "1. Cadastrar\n"+
                "2. Editar\n"+
                "3. Excluir\n"+
                "4. Visualizar\n"+
                "5. Sair\n");

        while (!input.hasNextInt()) {
            System.out.println("Porfavor insira um numero.");
            input.next();
        }

        int opcao = input.nextInt();
        while(opcao > 5 || opcao < 0){
            System.out.println("Opção invalida");
            opcao = input.nextInt();
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
                break;
        }
    }

    public void cadastrar() {
        Pessoa cadastro = new Pessoa();

        cadastro.setNome(validarString(input.nextLine()));
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
        cadastro.getEndereco().setNumero(validarString(input.nextLine()));

        System.out.print("Deseja Cadastrar quantos contatos? Minimo de 2: ");
        while (!input.hasNextInt()) {
            System.out.println("Porfavor insira um numero.");
            input.next();
        }
        int quantidadeDeContatos = input.nextInt();
        while (quantidadeDeContatos < 2) {
            System.out.print("Insira no minimo 2 contatos");
            quantidadeDeContatos = input.nextInt();
        }
        input.nextLine();

        for (int contador = 0; contador < quantidadeDeContatos; contador++){
            System.out.print("\nInsira o nome:");
            String nome = input.nextLine();
            System.out.print("Insira o telefone:");
            String telefone = validarTelefone();
            System.out.print("Insira o email:");
            String email = validarEmail();

            cadastro.criarContato(nome, telefone, email);
        }

        controleDAO.insert(cadastro);
        listaPessoas.add(cadastro);
        menu();
    }

    public void visualizar() {
        if(listaPessoas != null && !listaPessoas.isEmpty() ) {
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
            int id = input.nextInt();
            int controle = -1;
            for(Pessoa pessoa: listaPessoas){
                if(pessoa.getId() == id){
                    id = listaPessoas.indexOf(pessoa);
                    controle = 1;
                    break;
                }
            }
            if(controle == -1){
                System.out.println("Cadastro nao encontrado");
                menu();
            }
            else {
                System.out.println("\nInira os Novos Dados\n");

                input.nextLine();
                System.out.print("Insira o nome:");
                listaPessoas.get(id).setNome(validarString(input.nextLine()));
                System.out.print("Insira o CPF(Apenas numeros):");
                listaPessoas.get(id).setCpf(validarCpf());
                System.out.print("Insira o telefone:");
                listaPessoas.get(id).setTelefone(validarTelefone());
                System.out.print("Insira o email:");
                listaPessoas.get(id).setEmail(validarEmail());
                System.out.print("Insira o CEP:");
                listaPessoas.get(id).getEndereco().setCep(validarString(input.nextLine()));
                System.out.print("Insira o Bairro:");
                listaPessoas.get(id).getEndereco().setBairro(validarString(input.nextLine()));
                System.out.print("Insira o Rua:");
                listaPessoas.get(id).getEndereco().setRua(validarString(input.nextLine()));
                System.out.print("Insira o numero:");
                listaPessoas.get(id).getEndereco().setNumero(validarString(input.nextLine()));

                System.out.print("Deseja editar algum contato?(SIM)");
                if(input.nextLine().equals("SIM")){
                    for(Contato contato: listaPessoas.get(id).getListaContatos()){
                        System.out.print("\nInsira novo para " + contato.getNome() + ":");
                        contato.setNome(validarString(input.nextLine()));
                        System.out.print("Insira o novo telefone:");
                        contato.setTelefone(validarTelefone());
                        System.out.print("Insira o novo email:");
                        contato.setEmail(validarEmail());
                    }
                }

                System.out.print("Insira a quantidade de novos contatos que deseja adicionar:");
                while (!input.hasNextInt()) {
                    System.out.println("Porfavor insira um numero.");
                    input.next();
                }
                int quantidadeDeContatos = input.nextInt();
                input.nextLine();

                for (int contador = 0; contador < quantidadeDeContatos; contador++) {
                    System.out.print("\nInsira o nome:");
                    String nome = input.nextLine();
                    System.out.print("Insira o telefone:");
                    String telefone = validarTelefone();
                    System.out.print("Insira o email:");
                    String email = validarEmail();
                    listaPessoas.get(id).criarContato(nome, telefone, email);

                    controleDAO.insertContato(listaPessoas.get(id).getListaContatos().get(listaPessoas.get(id).getListaContatos().size() - 1));
                }
                controleDAO.update(listaPessoas.get(id));
                menu();
            }
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
            menu();
        }
    }

    public void excluir(){
        if(!listaPessoas.isEmpty()) {
            printarPessoas();
            System.out.println("\nDigite o id do cadastro que deseja excluir:");
            int id = input.nextInt();

            for(Pessoa pessoa: listaPessoas){
                if(pessoa.getId() == id){
                    if(listaPessoas.remove(pessoa)){
                        controleDAO.remove(id);
                    }
                    else{
                        break;
                    }
                }
            }
            System.out.println("Cadastro nao encontrado!");
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
        }
        menu();
    }

    public void printarPessoas(){
        for(Pessoa pessoa: listaPessoas){
            System.out.print(pessoa.toString() + "\n\nCONTATOS\n" + pessoa.toStringContatos() +
                    "\n-------------------------------------------");
        }
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