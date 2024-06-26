package org.example.Entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Endereco endereco;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Contato> listaContatos;

    @OneToMany(mappedBy = "pessoa")
    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public Pessoa(String data) throws ControlException {
        JSONObject jsonObject = new JSONObject(data);

        if(jsonObject.has("id")){
            setId(jsonObject.getInt("id"));
        }


        setTelefone(jsonObject.getString("telefone"));
        setCpf(jsonObject.getString("cpf"));
        setEmail(jsonObject.getString("email"));
        setNome(jsonObject.getString("nome"));

        if(jsonObject.has("endereco")){
            JSONObject jsonEndereco = jsonObject.getJSONObject("endereco");

            setEndereco(new Endereco());

            if(jsonEndereco.has("id")){
                getEndereco().setId(jsonEndereco.getInt("id"));
            }

            getEndereco().setPessoa(this);
            getEndereco().setBairro(jsonEndereco.getString("bairro"));
            getEndereco().setCep(jsonEndereco.getString("cep"));
            getEndereco().setRua(jsonEndereco.getString("rua"));
            getEndereco().setNumero(jsonEndereco.getString("numero"));
        }

        if(jsonObject.has("contatos")){
            setListaContatos(new ArrayList<Contato>());
            JSONArray jsonArray = jsonObject.getJSONArray("contatos");
            for(Object object: jsonArray){
                JSONObject jsonObj = (JSONObject) object;

                if(jsonObj.has("id")){
                    Contato contato = new Contato(jsonObj.getInt("id"), jsonObj.getString("nome"), jsonObj.getString("telefone"), jsonObj.getString("email"));
                    contato.setPessoa(this);
                    this.listaContatos.add(contato);
                }
                else{
                    Contato contato = new Contato(jsonObj.getString("nome"), jsonObj.getString("telefone"), jsonObj.getString("email"));
                    contato.setPessoa(this);
                    this.listaContatos.add(contato);
                }
            }
        }
    }


    public void setCpf(String cpf) throws ControlException {
        if(validarCPF(cpf)){
            this.cpf = cpf;
        }else{
            throw new ControlException("Campo cpf inválido!");
        }

    }

    public void setEmail(String email) throws ControlException{
        if(validarEmail(email)){
            this.email = email;
        }else{
            throw new ControlException("Campo email inválido!");
        }


    }

    public void setTelefone(String telefone) throws ControlException {
        if(validarTelefone(telefone)){
            this.telefone = telefone;
        }
        else {
            throw new ControlException("Campo telefone inválido!");
        }
    }

    public void setNome(String str) throws ControlException {
       if(validarString(str)){
           this.nome = str;
       }else {
           throw new ControlException("Campo nome inválido!");
       }
    }

    public boolean validarCPF(String cpf){
        cpf = cpf.replaceAll("\\.", "");
        cpf = cpf.replace("-", "");
        if(cpf.length() != 11){
            return false;
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
        return vet[9] == digito1 && vet[10] == digito2;
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
        if(getEmail() != null){
            jsonObject.put("email", getEmail());
        }
        if(getCpf() != null){
            jsonObject.put("cpf", getCpf());
        }
        if(getTelefone() != null){
            jsonObject.put("telefone", getTelefone());
        }
        if(getEndereco() != null){
            jsonObject.put("endereco", getEndereco().toJSON());
        }

        if(listaContatos != null){
            JSONArray jsonArray = new JSONArray();
            for(Contato contato: listaContatos){
                jsonArray.put(contato.toJSON());
            }
            jsonObject.put("contatos", jsonArray);
        }
        return jsonObject;
    }
}