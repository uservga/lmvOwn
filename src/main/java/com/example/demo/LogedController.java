package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LogedController {
    private double balance = 0.0;

    @FXML
    private Button logOutButton;
    @FXML
    private Label balanceLabel;

    DepositController depositController = new DepositController();

    @FXML
    private Label loginName;

    public void setLoginName(String name) {
        loginName.setText(name);
    }

    public void updateBalancePlus(double amount) {
        balance += amount;
        balanceLabel.setText(String.format("%.2f $", balance));
    }

    public void updateBalanceMinus(double amount) {
        if(balance >= 100){
            balance -= amount;
            balanceLabel.setText(String.format("%.2f $", balance));
        }
    }

    @FXML
    void handleLogOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) logOutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBalance(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit.fxml"));
            Parent root = loader.load();

            DepositController depositController = loader.getController();
            depositController.setLogedController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(mainStage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
