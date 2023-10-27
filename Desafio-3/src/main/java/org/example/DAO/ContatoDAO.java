package org.example.DAO;

import org.example.Entidades.Contato;
import org.example.Entidades.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatoDAO {

    private Connection connection;

    public void createTable() throws SQLException    {
        String query = "CREATE TABLE IF NOT EXISTS contatos (id INTEGER NOT NULL, idPessoa INTEGER NOT NULL, nome VARCHAR(255) NOT NULL, telefone VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, PRIMARY KEY (id))";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)) {
            statement.execute();
        }
    }
    public ContatoDAO(){
        connection = ConnectionFactory.getConnection();
    }

    public void readAll(ArrayList<Pessoa> pessoas) throws SQLException {

        String query = "SELECT * FROM contatos";
        ArrayList<Contato> contatos = new ArrayList<Contato>();
        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)) {
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Contato contatoTemporario = new Contato(resultado.getInt("id"), resultado.getString("nome"),
                        resultado.getString("telefone"), resultado.getString("email"),
                        resultado.getInt("idPessoa"));
                for (Pessoa pessoa : pessoas) {
                    if (pessoa.getId() == resultado.getInt("idPessoa")) {
                        pessoa.addContato(contatoTemporario);
                    }
                }
            }
        }
    }

    public void insert(Contato contato) throws SQLException{
        String query = "INSERT INTO contatos (id, idPessoa, nome, telefone, email) VALUES (?, ?, ?, ?, ?)";

        try( PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)) {
            statement.setInt(1, contato.getId());
            statement.setInt(2, contato.getIdPessoa());
            statement.setString(3, contato.getNome());
            statement.setString(4, contato.getTelefone());
            statement.setString(5, contato.getEmail());
            statement.execute();
        }
    }

    public void remove(int id) throws SQLException {
        String query = "DELETE FROM contatos WHERE idPessoa = ?";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query)){
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public void update(Contato contato) throws SQLException {
        String query = "UPDATE contatos SET nome = ?, email = ?, telefone = ? WHERE id = ?";

        try(PreparedStatement statement = (PreparedStatement) connection.prepareStatement((query))){
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setString(3, contato.getTelefone());
            statement.setInt(4, contato.getId());
            statement.execute();
        }
    }
}