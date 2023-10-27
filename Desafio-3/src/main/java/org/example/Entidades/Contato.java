package org.example.Entidades;

public class Contato {

    private static int contadorId = 0;

    private int id;

    private int idPessoa;

    private String nome;

    private String telefone;

    private String email;

    public Contato(int id, String nome, String telefone, String email, int idPessoa) {
       if(contadorId < id){
           contadorId = id;
       }
        this.id = id;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato(String nome, String telefone, String email, int idPessoa) {
        contadorId++;
        this.id = contadorId;
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId(){
        return id;
    }

    public int getIdPessoa(){
       return idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return  "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nEmail: " + email + "\n";
    }
}