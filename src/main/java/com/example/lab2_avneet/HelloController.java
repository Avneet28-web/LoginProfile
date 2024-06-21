package com.example.lab2_avneet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {
    public TableView<Authentication> authenticationTable;
    public TableColumn<Authentication,Integer> UserId;
    public TableColumn <Authentication,String> UserName;
    public TableColumn <Authentication,String> UserEmail;
    public TableColumn <Authentication,String> UserPassword;
    public TextField uid;
    public TextField uname;
    public TextField uemail;
    public TextField upassword;
    @FXML
    private Label welcomeText;

    ObservableList<Authentication> list = FXCollections.observableArrayList();

    @FXML
    protected void onHelloButtonClick() {
        fetchdata();
    }

    private void fetchdata() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/avneet_login";
        String dbAuthentication = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbAuthentication,
                dbPassword)) {
            String query = "SELECT * FROM authentication";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int UserId = resultSet.getInt("UserId");
                String UserName = resultSet.getString("UserName");
                String UserEmail = resultSet.getString("UserEmail");
                String UserPassword = resultSet.getString("UserPassword");
                authenticationTable.getItems().add(new Authentication(UserId, UserName, UserEmail, UserPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserId.setCellValueFactory(new PropertyValueFactory<Authentication,Integer>("UserId"));
        UserName.setCellValueFactory(new PropertyValueFactory<Authentication,String>("UserName"));
        UserEmail.setCellValueFactory(new PropertyValueFactory<Authentication,String>("UserEmail"));
        UserPassword.setCellValueFactory(new PropertyValueFactory<Authentication,String>("UserPassword"));
        authenticationTable.setItems(list);


    }

    public void InsertData(ActionEvent actionEvent) {



        String UserName = uname.getText();
        String UserEmail = uemail.getText();
        String UserPassword = upassword.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/avneet_login";
        String dbAuthentication = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbAuthentication,
                dbPassword)) {
            String query = "INSERT INTO `authentication`( `UserName`, `UserEmail`, `UserPassword`) VALUES ('"+UserName+"','"+UserEmail+"','"+UserPassword+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateData(ActionEvent actionEvent) {
        String UserId = uid.getText();
        String UserName = uname.getText();
        String UserEmail = uemail.getText();
        String UserPassword = upassword.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/avneet_login";
        String dbAuthentication = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbAuthentication,
                dbPassword)) {
            String query = "UPDATE `authentication` SET `UserName`='"+UserName+"',`UserEmail`='"+UserEmail+"',`UserPassword`='"+UserPassword+"' WHERE UserId='"+UserId+"' ";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {

        String UserId = uid.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/avneet_login";
        String dbAuthentication = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbAuthentication,
                dbPassword)) {
            String query = "DELETE FROM `authentication` WHERE UserId='"+UserId+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {

        String id = uid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/avneet_login";
        String dbAuthentication = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbAuthentication,
                dbPassword)) {
            String query = "SELECT * FROM authentication WHERE UserId='"+UserId+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String UserName = resultSet.getString("UserName");
                String UserEmail = resultSet.getString("UserEmail");
                String UserPassword = resultSet.getString("UserPassword");

                uname.setText(UserName);
                uemail.setText(UserEmail);
                upassword.setText(UserPassword);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}