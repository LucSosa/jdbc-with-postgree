package br.com.sosa.dao;

import br.com.sosa.conn.SingleConnection;
import br.com.sosa.model.BeanUserFone;
import br.com.sosa.model.Telefone;
import br.com.sosa.model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserposjavaDAO {
    private Connection connection;

    public UserposjavaDAO() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Userposjava userposjava) {
        try {
            String sql = "INSERT INTO userposjava (nome, email) values (?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
            insert.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();  //reverte operação
            } catch (SQLException ex) { //acho que tem como diminuir isso, mas em uma pesquisa rápida não encontrei
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void salvarTelefone(Telefone telefone) {
        try {
            String sql = "INSERT INTO public.telefoneuser (numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, telefone.getNumero());
            insert.setString(2, telefone.getTipo());
            insert.setLong(3, telefone.getUsuario());
            insert.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public List<Userposjava> listar() throws SQLException {
        List<Userposjava> listOfUsers = new ArrayList<Userposjava>();

        String sql = "SELECT * FROM userposjava";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Userposjava userposjava = new Userposjava();
            userposjava.setId(result.getLong("id"));
            userposjava.setNome(result.getString("nome"));
            userposjava.setEmail(result.getString("email"));
            listOfUsers.add(userposjava);
        }
        return listOfUsers;
    }

    public Userposjava buscar(Long id) throws SQLException {
        Userposjava findUser = new Userposjava();

        String sql = "SELECT * FROM userposjava WHERE id= " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            findUser.setId(result.getLong("id"));
            findUser.setNome(result.getString("nome"));
            findUser.setEmail(result.getString("email"));
        }
        return findUser;
    }

    public List<BeanUserFone> listUserFone(Long id) {
        List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
        String sql = "SELECT nome, numero, email FROM telefoneuser as fone " +
                "INNER JOIN userposjava AS userp " +
                "ON fone.usuariopessoa = userp.id " +
                "WHERE userp.id= " + id;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BeanUserFone userFone = new BeanUserFone();
                userFone.setEmail(resultSet.getString("email"));
                userFone.setNome(resultSet.getString("nome"));
                userFone.setEmail(resultSet.getString("email"));
                beanUserFones.add(userFone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return beanUserFones;
    }

    public void atualizar(Userposjava userposjava) {
        try {
            String sql = "UPDATE userposjava SET nome = ? WHERE id=" + userposjava.getId();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1, userposjava.getNome());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void deletar(Long id) {
        try {
            String sql = "DELETE FROM userposjava WHERE id=" + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
    }

    public void deleteFonesById(Long id) {
        try {
            String sqlFone = "DELETE FROM telefoneuser WHERE usuariopessoa=" + id;
            String sqlUser = "DELETE FROM userposjava WHERE id=" + id;
            PreparedStatement statement = connection.prepareStatement(sqlFone);
            statement.executeUpdate();
            connection.commit();
            connection.prepareStatement(sqlUser);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
