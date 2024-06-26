import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String cpf;
    private String email;
    private ArrayList<Contato> listaContatos = new ArrayList<Contato>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Contato> getListaContatos() {
        return listaContatos;
    }

    public int getTamanhoListaContatos(){
        return listaContatos.size();
    }

    public void adicionarListaContatos(String nome, String telefone, String email) {
        Contato contato = new Contato(nome, telefone, email);
        this.listaContatos.add(contato);

    }

    public String toString(){
        return "\nNome: " + nome + "\nEmail: " + email + "\nCpf: " + cpf;
    }

    public String toStringContatos(){
        StringBuilder retorno = new StringBuilder();
        for(Contato contato : listaContatos){
            retorno.append(contato.toString());
        }
        return retorno.toString();
    }
}
