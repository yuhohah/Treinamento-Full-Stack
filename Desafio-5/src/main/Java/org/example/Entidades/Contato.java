package org.example.Entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    private Pessoa pessoa;

    public Contato(String nome, String telefone, String email) throws ControlException {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public Contato(Integer id, String nome, String telefone, String email) throws ControlException {
        setId(id);
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public void setEmail(String email) throws ControlException{
        if(validarEmail(email)){
            this.email = email;
        }else{
            throw new ControlException("Campo email de Contato inválido!");
        }


    }

    public void setTelefone(String telefone) throws ControlException {
        if(validarTelefone(telefone)){
            this.telefone = telefone;
        }
        else {
            throw new ControlException("Campo telefone de Contato inválido!");
        }
    }

    public void setNome(String str) throws ControlException {
        if(validarString(str)){
            this.nome = str;
        }else {
            throw new ControlException("Campo nome de Contato inválido!");
        }
    }

    public boolean validarTelefone(String telefone){
        if(telefone != null && !telefone.isEmpty()){
            telefone = telefone.replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace(" ", "");
            telefone = telefone.replace("-", "");
            String expression = "^((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(telefone);
            return matcher.matches();
        }
        return false;
    }

    public boolean validarString(String str){
        return !str.isEmpty();
    }

    public boolean validarEmail(String email){
        if(email != null && !email.isEmpty()){
            String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();

        if(getId() != null){
            jsonObject.put("id", getId());
        }
        if(getNome() != null){
            jsonObject.put("nome", getNome());
        }
        if(getTelefone() != null){
            jsonObject.put("telefone", getTelefone());
        }
        if(getEmail() != null){
            jsonObject.put("email", getEmail());
        }
        return jsonObject;
    }
}