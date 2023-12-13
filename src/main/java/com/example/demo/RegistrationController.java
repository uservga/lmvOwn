package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private ImageView mainButton;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField repeatPasswordField;

    @FXML
    private CheckBox agreeCheckBox;

    @FXML
    private Button registerButton;

    public User user = null;

    @FXML
    void initialize() {
        registerButton.setDisable(true);
        loginField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        repeatPasswordField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        agreeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> validateFields());

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

        loginLabel.setOnMouseClicked(event -> {
            openLogin();
        });
    }

    private void validateFields() {
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();
        String login = loginField.getText();
        boolean agreed = agreeCheckBox.isSelected();

        boolean conditionsMet = !login.isEmpty() && agreed && password.equals(repeatPassword);
        registerButton.setDisable(!conditionsMet);
    }

    private void openLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            LoginController loginController = loader.getController();
            loginController.setRegistrationController(this);

            Stage stage = (Stage) loginLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleRegister() {
        user = new User(loginField.getText(), passwordField.getText());
        openLogin();
    }
}
