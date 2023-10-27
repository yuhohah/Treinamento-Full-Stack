package org.example.DAO;

import org.example.Entidades.Pessoa;
import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO {

    private Connection connection;


    public PessoaDAO(){
        connection = ConnectionFactory.getConnection();
    }

    public void createTable() throws SQLException    {
        String query = "CREATE TABLE IF NOT EXISTS pessoas (id INTEGER NOT NULL, idEndereco INTEGER NOT NULL, nome VARCHAR(255) NOT NULL, cpf VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, telefone VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.execute();
        }
    }

    public ArrayList<Pessoa> findAll() throws SQLException {
        String query = "SELECT * FROM pessoas";
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        try( PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            ResultSet resultado = statement.executeQuery();

            while(resultado.next()){
                pessoas.add(new Pessoa(resultado.getInt("id"), resultado.getInt("idEndereco"),
                        resultado.getString("nome"), resultado.getString("email"),
                        resultado.getString("telefone"), resultado.getString("cpf")));
            }
        }
        return pessoas;
    }

    public void insert(Pessoa pessoa) throws SQLException{
        String query = "INSERT INTO pessoas (id, idEndereco, nome, cpf, email, telefone) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.setInt(1, pessoa.getId());
            statement.setInt(2, pessoa.getEndereco().getId());
            statement.setString(3, pessoa.getNome());
            statement.setString(4, pessoa.getCpf());
            statement.setString(5, pessoa.getEmail());
            statement.setString(6, pessoa.getTelefone());
            statement.execute();
        }
    }

    public void remove(int id) throws SQLException {
        String query = "DELETE FROM pessoas WHERE id = ?";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public void update (Pessoa pessoa) throws SQLException {
        String query = "UPDATE pessoas SET nome = ?, telefone = ?, email = ?, cpf = ? WHERE id = ?";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement((query))){
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getTelefone());
            statement.setString(3, pessoa.getEmail());
            statement.setString(4, pessoa.getCpf());
            statement.setInt(5, pessoa.getId());
            statement.execute();
        }
    }
}