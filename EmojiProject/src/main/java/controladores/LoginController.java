package controladores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.User;
import tdas.ArrayList;

public class LoginController {

    @FXML
    private ImageView imgViewLogo;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;
    private User loggedInUser;

    public void initialize() {
        User.loadUsers();
    }

    @FXML
    private void login() throws IOException {
        String usuario = txtUserName.getText();
        String contraseña = txtPassword.getText();
        if (correctAcccount(usuario, contraseña)) {
            Stage s = (Stage) loginButton.getScene().getWindow();
            s.close();
            FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("principalPane.fxml"));
            Parent root = fxmLoader.load();
            PrincipalPaneController contrPrincipalPane = fxmLoader.getController();
            contrPrincipalPane.recoverUser(loggedInUser);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Usuario y/o contraseña incorecto(s)");
            alerta.show();
            txtUserName.clear();
            txtPassword.clear();
        }
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        Stage s = (Stage) signupButton.getScene().getWindow();
        s.close();
        FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = fxmLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private boolean correctAcccount(String usuario, String contraseña) {
        ArrayList<User> users = User.loadUsers();
        for (User user : users) {
            if ((user.getUser().equals(usuario)) && (user.getPassword().equals(contraseña))) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

}
