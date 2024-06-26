package org.example.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = true)
    private String numero;

    @OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setId (int id){
        this.id = id;
    }

    public Endereco(){
    }

    public Endereco(String cep, String bairro, String rua, String numero){
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public int getId(){
        return id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;

    }

    public String toString(){
        return ("\nCEP:" + cep +
                "\nBairro:" + bairro +
                "\nRua:" + rua +
                "\nNumero:" + numero);
    }
}