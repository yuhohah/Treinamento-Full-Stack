package org.example.DAO;

import org.example.Entidades.Contato;
import org.example.Entidades.Pessoa;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControleDAO {

    EnderecoDAO enderecoDAO = new EnderecoDAO();

    ContatoDAO contatoDAO = new ContatoDAO();

    PessoaDAO pessoaDAO = new PessoaDAO();

    public ArrayList<Pessoa> readAll(){
        ArrayList<Pessoa> pessoas = null;
        try{
            pessoas = pessoaDAO.findAll();
            contatoDAO.readAll(pessoas);
            enderecoDAO.readAll(pessoas);
        }catch(SQLException exception){
            exception.getMessage();
            exception.getStackTrace();
        }
        return pessoas;
    }

    public void remove(int id){
        try{
            enderecoDAO.remove(id);
            contatoDAO.remove(id);
            pessoaDAO.remove(id);
        }catch(SQLException exception){
            exception.getStackTrace();
        }
    }

    public void insert(Pessoa cadastro){
        try{
            pessoaDAO.insert(cadastro);
            enderecoDAO.insert(cadastro.getEndereco());
            ArrayList<Contato> listaContatoTemporario = cadastro.getListaContatos();
            for(Contato contato: listaContatoTemporario){
                contatoDAO.insert(contato);
            }
            System.out.println("Salvo com sucesso no banco de dados!");
        } catch (SQLException exception){
            exception.getMessage();
            exception.printStackTrace();
            System.out.println("Erro no salvamento no banco de dados!");
        }
    }

    public void insertContato(Contato contato){
        try{
            contatoDAO.insert(contato);
        }catch (SQLException exception){
            exception.getMessage();
        }
    }

    public void update(Pessoa cadastro){
        try{
            pessoaDAO.update(cadastro);
            enderecoDAO.update(cadastro.getEndereco());
            for(Contato contato: cadastro.getListaContatos()){
                contatoDAO.update(contato);
            }
        } catch (SQLException exception){
            exception.getMessage();
        }
    }
}
