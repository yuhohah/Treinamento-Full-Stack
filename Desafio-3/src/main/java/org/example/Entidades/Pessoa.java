package org.example.Entidades;

import java.util.ArrayList;

public class Pessoa {

    private static int contadorId = 0;

    private int id;

    private int idEndereco;

    private String nome;

    private Endereco endereco;

    private String telefone;

    private String cpf;

    private String email;

    private ArrayList<Contato> listaContatos = new ArrayList<Contato>();

    public Pessoa(){
        contadorId++;
        this.id = contadorId;
        this.endereco = new Endereco();
        this.idEndereco = this.endereco.getId();
        this.endereco.setIdPessoa(this.id);
    }

    public Pessoa(int id, int idEndereco, String nome, String email, String telefone, String cpf){
        if(contadorId < id){
            contadorId = id;
        }
        this.id = id;
        this.idEndereco = idEndereco;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public int getId(){
        return id;
    }

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

    public void criarContato(String nome, String telefone, String email) {
        Contato contato = new Contato(nome, telefone, email, this.id);
        this.listaContatos.add(contato);
    }

    public void addContato(Contato contato){
        listaContatos.add(contato);
    }

    public String toString(){
        return  "\n\nID: " + id +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nEmail: " + email +
                "\nCpf: " + cpf +
                endereco.toString();
    }

    public String toStringContatos(){
        StringBuilder retorno = new StringBuilder();
        for(Contato contato : listaContatos){
            retorno.append(contato.toString());
        }
        return retorno.toString();
    }
}

