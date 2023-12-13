package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;
    @FXML
    private ImageView mainButton;

    @FXML
    private Label registerLabel;

    @FXML
    private Button loginButton;

    private RegistrationController registrationController;

    public void setRegistrationController(RegistrationController registrationController) {
        this.registrationController = registrationController;
    }

    @FXML
    void initialize() {
        loginButton.setDisable(true);
        loginField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());

        mainButton.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage stage = (Stage) mainButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setFullScreen(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        registerLabel.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                Stage stage = (Stage) registerLabel.getScene().getWindow();
                stage.setScene(scene);
                stage.setFullScreen(true);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void validateFields() {
        String password = passwordField.getText();
        String login = loginField.getText();

        String registeredLogin = registrationController.user.getLogin();
        String registeredPassword = registrationController.user.getPassword();

        boolean conditionsMet = registeredLogin.equals(login) && registeredPassword.equals(password);
        loginButton.setDisable(!conditionsMet);
    }

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loged.fxml"));
            Parent root = loader.load();
            LogedController logedController = loader.getController();
            logedController.setLoginName(login);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
