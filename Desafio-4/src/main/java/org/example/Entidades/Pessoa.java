package org.example.Entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Contato> listaContatos = new ArrayList<Contato>();

    public Pessoa(){
        this.endereco = new Endereco();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void criarContato(String nome, String telefone, String email) {
        Contato contato = new Contato(nome, telefone, email);
        contato.setPessoa(this);
        this.listaContatos.add(contato);
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