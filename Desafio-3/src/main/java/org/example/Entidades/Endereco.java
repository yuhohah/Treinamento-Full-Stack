package org.example.Entidades;

public class Endereco {

    private static int contadorId = 0;
    private int id;
    private int idPessoa;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;


    public Endereco(){
        contadorId++;
        this.id = contadorId;
    }

    public Endereco(int id, int idPessoa, String cep, String bairro, String rua, String numero){
        if(contadorId < id){
            contadorId = id;
        }
        this.id = id;
        this.idPessoa = idPessoa;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Endereco(String cep, String bairro, String rua, String numero){
        contadorId++;
        this.id = contadorId;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public int getId(){
        return id;
    }

    public int getIdPessoa(){
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa){
        this.idPessoa = idPessoa;
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

    public void setEndereco(Endereco endereco){
        this.id = endereco.getId();
        this.idPessoa = endereco.getIdPessoa();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.cep = endereco.getCep();
    }

    public String toString(){
        return ("\nCEP:" + cep +
                "\nBairro:" + bairro +
                "\nRua:" + rua +
                "\nNumero:" + numero);
    }
}
