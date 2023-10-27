package org.example.DAO;

import org.example.Entidades.Endereco;
import org.example.Entidades.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnderecoDAO {

    private Connection connection;

    public EnderecoDAO(){
        connection = ConnectionFactory.getConnection();
    }

    public void createTable() throws SQLException    {
        String query = "CREATE TABLE IF NOT EXISTS enderecos (id INTEGER NOT NULL, idPessoa INTEGER NOT NULL, cep VARCHAR(255) NOT NULL, bairro VARCHAR(255) NOT NULL, rua VARCHAR(255) NOT NULL, numero VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.execute();
        }
    }

    public void readAll(ArrayList<Pessoa> pessoas) throws SQLException {
        String query = "SELECT * FROM enderecos";
        ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){

            ResultSet resultado = statement.executeQuery();

            while(resultado.next()){
                Endereco enderecoTemporario = new Endereco(resultado.getInt("id"), resultado.getInt("idPessoa"), resultado.getString("cep"),
                        resultado.getString("bairro"), resultado.getString("rua"),
                        resultado.getString("numero"));

                for(Pessoa pessoa: pessoas) {
                    if (pessoa.getId() == resultado.getInt("idPessoa")) {
                        pessoa.setEndereco(enderecoTemporario);
                    }
                }
            }
        }
    }

    public void insert(Endereco endereco) throws SQLException{
        String query = "INSERT INTO enderecos (numero, bairro, cep, rua, id, idPessoa) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.setString(1, endereco.getNumero());
            statement.setString(2, endereco.getBairro());
            statement.setString(3, endereco.getCep());
            statement.setString(4, endereco.getRua());
            statement.setInt(5, endereco.getId());
            statement.setInt(6, endereco.getIdPessoa());
            statement.execute();
        }
    }

    public void remove(int id) throws SQLException {
        String query = "DELETE FROM enderecos WHERE idPessoa = ?";
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.setInt(1, id);
            statement.execute();
        }

    }

    public void update (Endereco endereco) throws SQLException {
        String query = "UPDATE enderecos SET bairro = ?, cep = ?, rua = ?, numero = ? WHERE idPessoa = ?";
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement((query))){
            statement.setString(1, endereco.getBairro());
            statement.setString(2, endereco.getCep());
            statement.setString(3, endereco.getRua());
            statement.setString(4, endereco.getNumero());
            statement.setInt(5, endereco.getIdPessoa());
            statement.execute();
        }
    }
}