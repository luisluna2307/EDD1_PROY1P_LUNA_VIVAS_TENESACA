/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.User;
import tdas.ArrayList;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegisterController implements Initializable {

    private static ArrayList<User> listUsers;

    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPsw;
    @FXML
    private TextField txtConfirmPsw;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listUsers = User.loadUsers();

    }

    @FXML
    private void backToPrimary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Pane root = loader.load();
            LoginController controladorLogin = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("EmojiBuilder");
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @FXML
    private void save(ActionEvent event) {
        String username = txtUser.getText();
        String pass = txtPsw.getText();
        String confirmPass = txtConfirmPsw.getText();

        if (username.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            showAlert("Porfavor, llenar todos los campos");
            clear();
        } else if (!pass.equals(confirmPass)) {
            showAlert("No coinciden las contraseñas");
            clear();
        } else {
            if (User.verifyUser(listUsers, username, pass) == false) {
                try ( BufferedWriter bw = new BufferedWriter(new FileWriter(App.filePathUsers + "users.txt", true))) {
                    bw.write(username + "," + pass + "\n");
                    showAlert("Este usuario se ha registrado exitosamente. Inicie sesión");
                    clear();
                } catch (IOException e) {
                    showAlert("Este usuario no pudo ser registrado. Intente nuevamente");
                    clear();
                }

            } else {
                showAlert("Este usuario se ha registrado anteriormente. Inicie sesión");
                clear();

            }

        }
    }

    private void showAlert(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(m);
        alert.showAndWait();
    }

    private void clear() {
        txtUser.clear();
        txtPsw.clear();
        txtConfirmPsw.clear();
    }

}
