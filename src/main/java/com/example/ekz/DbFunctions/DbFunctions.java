package com.example.ekz.DbFunctions;

import com.example.ekz.Models.Meditations;
import com.example.ekz.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DbFunctions {
    public Connection connect_to_db() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "qwe", "postgres", "123");
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public int check_login(String login) {
        try {
            String query = String.format("select * from users where login = '%s'", login);
            Statement statement = connect_to_db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() >= 1) {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }

    public int signIn(String login, String password) {
        try {
            String query = String.format("select * from users where login = '%s' and password = '%s'", login, password);
            Statement statement = connect_to_db().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return 0;
            }
//            Variables.STATUS_USER = resultSet.getString("status");
            Variables.ACTIVE_USER = resultSet.getString("login");
//            Variables.ROLE_USER = resultSet.getString("role");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 404;
        }
        return 201;
    }



    public ObservableList<User> getAllUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from users");
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString("Id"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        resultSet.getString("Role"),
                        resultSet.getString("Status")
                ));
            }
             return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }

    public ObservableList<Meditations> getAllMeditations() {
        ObservableList<Meditations> meditations = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connect_to_db().createStatement().executeQuery("select * from traningtable");
            while (resultSet.next()) {
                meditations.add(new Meditations(
                        resultSet.getString("id"),
                        resultSet.getString("sender"),
                        resultSet.getString("description"),
                        resultSet.getString("recipient")

                ));
            }
            return meditations;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return meditations;
        }
    }



    public void updateDataUser(String login, String password, String role, String id, String status) {
        try {
            String query = String.format("update users set login='%s', password='%s', role='%s', status='%s' where id='%s'", login, password, role, id, status);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("Data updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteDataUser(String id) {
        try {
            String query = String.format("delete from users where id='%s'",id);
            connect_to_db().createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(String login, String password) {
        try {
            String query = String.format("insert into users(login, password) values('%s','%s');",
                    login, password);
            Statement statement = connect_to_db().createStatement();
            statement.executeUpdate(query);
            System.out.println("User created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





}