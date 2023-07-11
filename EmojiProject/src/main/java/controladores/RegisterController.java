/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.User;
import tdas.ArrayList;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class RegisterController implements Initializable {

    private static ArrayList<User> listUsers = User.loadUsers();

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
    }

    @FXML
    private void backToPrimary(ActionEvent event) {
    }

    @FXML
    private void save(ActionEvent event) {
        String username = txtUser.getText();
        String pass = txtPsw.getText();
        String confirmPass = txtConfirmPsw.getText();

        if (username.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            showAlert("Porfavor, llenar todos los campos");
        } else if (!pass.equals(confirmPass)) {
            showAlert("No coinciden las contraseñas");
        } else {
            showAlert("Usuario ha sido registrado con exito");
            clear();

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
