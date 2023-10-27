import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Tela {

    private ArrayList<Pessoa> listaCadastro = new ArrayList<Pessoa>();
    Scanner input = new Scanner(System.in);

    public void menu() {
        int opcao;

        System.out.println("\nCadastro de usuário\n"+
                "Digite o que deseja fazer\n"+
                "1. Cadastrar\n"+
                "2. Editar\n"+
                "3. Excluir\n"+
                "4. Visualizar\n"+
                "5. Sair\n");

        opcao = input.nextInt();

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
        Endereco endereco = new Endereco();

        cadastro.setNome(input.nextLine());
        System.out.print("Insira o nome:");
        cadastro.setNome(input.nextLine());
        System.out.print("Insira o CPF(Apenas numeros):");
        cadastro.setCpf(validarCpf());
        System.out.print("Insira o telefone:");
        cadastro.setTelefone(validarTelefone());
        System.out.print("Insira o email:");
        cadastro.setEmail(validarEmail());
        System.out.print("Insira o CEP:");
        endereco.setCep(input.nextLine());
        System.out.print("Insira o Bairro:");
        endereco.setBairro(input.nextLine());
        System.out.print("Insira o Rua:");
        endereco.setRua(input.nextLine());
        System.out.print("Insira o numero:");
        endereco.setNumero(input.nextLine());

        cadastro.setEndereco(endereco);

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

        for (int contador = 0; contador < quantidadeDeContatos; contador++) {
            System.out.print("Insira o nome:");
            String nome = input.nextLine();
            System.out.print("Insira o telefone:");
            String telefone = validarTelefone();
            System.out.print("Insira o email:");
            String email = validarEmail();

            cadastro.adicionarListaContatos(nome, telefone, email);
        }
        listaCadastro.add(cadastro);
        menu();
    }

    public void visualizar() {
        if(!listaCadastro.isEmpty()) {
            printCadastros();
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
        }
        menu();
    }

    public void editar(){
        if(!listaCadastro.isEmpty()) {
            printCadastros();

            System.out.println("\nDigite o nome do cadastro que deseja editar:");
            String nome = input.nextLine();
            int id = -1;
            for(Pessoa cadastro: listaCadastro){
                if(cadastro.getNome().equals(nome)){
                    id = listaCadastro.indexOf(cadastro);
                }
            }
            if(id == -1){
                System.out.println("Cadastro nao encontrado");
                editar();
            }
            else {
                System.out.println("\nDeseja editar o que?"+
                "\n1.Tudo"+
                "\n2.Nome"+
                "\n3.Email"+
                "\n4.Cpf");

                while (!input.hasNextInt()) {
                    System.out.println("Porfavor insira um numero.");
                    input.next();
                }
                int opcao = input.nextInt();
                while(opcao < 1 || opcao > 4){
                    opcao = input.nextInt();
                }

                switch (opcao) {
                    case 1:
                        System.out.print("Insira o nome:");
                        input.nextLine();
                        listaCadastro.get(id).setNome(input.nextLine());
                        System.out.print("Insira o CPF(Apenas numeros):");
                        listaCadastro.get(id).setCpf(validarCpf());
                        System.out.print("Insira o email:");
                        listaCadastro.get(id).setEmail(validarEmail());
                        break;

                    case 2:
                        System.out.print("Insira o nome:");
                        listaCadastro.get(id).setNome(input.nextLine());
                        listaCadastro.get(id).setNome(input.nextLine());
                        break;
                    case 3:
                        System.out.print("Insira o email:");
                        input.nextLine();
                        listaCadastro.get(id).setEmail(validarEmail());
                        break;
                    case 4:
                        System.out.print("Insira o CPF(Apenas numeros):");
                        listaCadastro.get(id).setCpf(validarCpf());
                        break;
                }
                menu();
            }
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
            menu();
        }
    }

    public void excluir(){
        if(!listaCadastro.isEmpty()) {
            printCadastros();
            System.out.println("\nDigite o nome do cadastro que deseja excluir:");
            String nome = input.nextLine();

            if(listaCadastro.removeIf(cadastro -> cadastro.getNome().equals(nome))){
                System.out.println("Cadastro removido com sucesso");
            }
            else {
                System.out.println("Cadastro nao encontrado!");
            }
            menu();
        }
        else{
            System.out.println("\nNão há usuários cadastrados!\n");
            menu();
        }

    }
    
    public void printCadastros(){
        for(Pessoa cadastro: listaCadastro){
            System.out.print("\n" + cadastro.toString() + cadastro.toStringContatos());
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
            String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
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
}