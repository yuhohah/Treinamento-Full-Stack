package org.example.Entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "numero", nullable = true)
    private String numero;

    @Getter
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Endereco(String cep, String bairro, String rua, String numero) throws ControlException {
        setCep(cep);
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
    }

    public Endereco(int id, String cep, String bairro, String rua, String numero) throws ControlException {
        setCep(cep);
        setId(id);
        setRua(rua);
        setNumero(numero);
        setBairro(bairro);
    }

    public void setCep(String str) throws ControlException {
        if(validarString(str)){
            str = str.replace("-", "");
            this.cep = str;
        }else {
            throw new ControlException("Campo cep em branco!");
        }
    }

    public void setRua(String str) throws ControlException {
        if(validarString(str)){
            this.rua = str;
        }else {
            throw new ControlException("Campo rua em branco!");
        }
    }

    public void setBairro(String str) throws ControlException {
        if(validarString(str)){
            this.bairro = str;
        }else {
            throw new ControlException("Campo bairro em branco!");
        }
    }

    public void setNumero(String str) throws ControlException {
        if(validarString(str)){
            this.numero = str;
        }else {
            throw new ControlException("Campo numero em branco!");
        }
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean validarString(String str){
        return !str.isEmpty();
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();

        if(getId() != null){
            jsonObject.put("id", getId());
        }
        if(getCep() != null){
            jsonObject.put("cep", getCep());
        }
        if(getBairro() != null){
            jsonObject.put("bairro", getBairro());
        }
        if(getNumero() != null){
            jsonObject.put("numero", getNumero());
        }
        if(getRua() != null){
            jsonObject.put("rua", getRua());
        }
        return jsonObject;
    }
}