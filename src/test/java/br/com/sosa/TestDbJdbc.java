package br.com.sosa;

import br.com.sosa.dao.UserposjavaDAO;
import br.com.sosa.model.BeanUserFone;
import br.com.sosa.model.Telefone;
import br.com.sosa.model.Userposjava;

import java.sql.SQLException;
import java.util.List;

public class TestDbJdbc {
    public static void main(String[] args) {
        UserposjavaDAO dao = new UserposjavaDAO();
        dao.deleteFonesById(9L);
    }

    public void initInsertUserDb() {
        UserposjavaDAO dao = new UserposjavaDAO();
        Userposjava userposjav = new Userposjava();

        userposjav.setNome("Skull Knight");
        userposjav.setEmail("skullknight@berserk.com");

        dao.salvar(userposjav);
    }

    public void initList() {
        UserposjavaDAO dao = new UserposjavaDAO();
        try {
            List<Userposjava> listOfUsers = dao.listar();
            for (Userposjava user : listOfUsers) {
                System.out.println(user);
                System.out.println("----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initSearchUser() {
        UserposjavaDAO dao = new UserposjavaDAO();
        try {
            Userposjava userposjav = dao.buscar(5L);
            System.out.println(userposjav);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initUpdate() {
        try {
            UserposjavaDAO dao = new UserposjavaDAO();
            Userposjava userposjava = dao.buscar(2L);
            userposjava.setNome("atualizado");
            dao.atualizar(userposjava);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDelete() {
        try {
            UserposjavaDAO dao = new UserposjavaDAO();
            dao.deletar(7L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initInsertTelefoneDb() {
        Telefone telefone = new Telefone();
        telefone.setNumero("53991156644");
        telefone.setTipo("Celular");
        telefone.setUsuario(9L);

        UserposjavaDAO dao = new UserposjavaDAO();
        dao.salvarTelefone(telefone);
    }

    public void initFindByUserAndPhone(){
        UserposjavaDAO dao = new UserposjavaDAO();
        List<BeanUserFone> beanUserFones = dao.listUserFone(8L);
        for (BeanUserFone beanUserFone : beanUserFones) {
            System.out.println(beanUserFone);
            System.out.println("-----------------");
        }

    }
}
