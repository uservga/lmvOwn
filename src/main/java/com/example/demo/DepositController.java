package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DepositController {
    private LogedController logedController;
    @FXML
    public Button withdrawButton100;
    @FXML
    public Button withdrawButton250;
    @FXML
    public Button withdrawButton500;

    public void setLogedController(LogedController logedController) {
        this.logedController = logedController;
    }

    public void checkBalance() {
        if (logedController.getBalance() >= 500) {
            withdrawButton500.setDisable(false);
            withdrawButton250.setDisable(false);
            withdrawButton100.setDisable(false);
        } else if (logedController.getBalance() >= 250) {
            withdrawButton500.setDisable(true);
            withdrawButton250.setDisable(false);
            withdrawButton100.setDisable(false);
        } else if (logedController.getBalance() >= 100) {
            withdrawButton500.setDisable(true);
            withdrawButton250.setDisable(true);
            withdrawButton100.setDisable(false);
        }else {
            withdrawButton500.setDisable(true);
            withdrawButton250.setDisable(true);
            withdrawButton100.setDisable(true);
        }
    }

    @FXML
    void handleDeposit100() {
        double amount = 100.00;
        logedController.updateBalancePlus(amount);
        checkBalance();
    }

    @FXML
    void handleDeposit250() {
        double amount = 250.00;
        logedController.updateBalancePlus(amount);
        checkBalance();
    }

    @FXML
    void handleDeposit500() {
        double amount = 500.00;
        logedController.updateBalancePlus(amount);
        checkBalance();
    }

    @FXML
    void handleWithdraw100() {
        double amount = 100.00;
        logedController.updateBalanceMinus(amount);
        checkBalance();
    }

    @FXML
    void handleWithdraw250() {
        double amount = 250.00;
        logedController.updateBalanceMinus(amount);
        checkBalance();
    }

    @FXML
    void handleWithdraw500() {
        double amount = 500.00;
        logedController.updateBalanceMinus(amount);
        checkBalance();
    }
}
