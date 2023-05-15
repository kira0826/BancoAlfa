package com.main.alfabank.model.controllers;

import com.main.alfabank.AlfaBankMain;
import com.main.alfabank.model.domain.RegisterList;
import com.main.alfabank.model.domain.Registrable;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class RegisterTableController implements Initializable {


    @FXML
    private TableColumn<Registrable, Double> amountColumn;

    @FXML
    private TableColumn<Registrable, Date> dateColumn;

    @FXML
    private TableColumn<Registrable, String> descriptionColumn;
    @FXML
    private Button addRegisterbutton;
    @FXML
    private Button balanceButton;

    @FXML
    private Button expenseButton;

    @FXML
    private Button incomesButton;

    @FXML
    private TableView<Registrable> tableMain;

    @FXML
    private Label totalLabelMessage;

    @FXML
    private Label totalLabelSumatory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableMain.setItems(RegisterList.getInstance().getListOfRegisters());
        totalLabelSumatory.setText(String.valueOf(RegisterList.getInstance().totalBalance()));

        totalLabelBackgroundColor(Double.parseDouble(totalLabelSumatory.getText()));
        balanceButton.setStyle("-fx-background-color: #d3d3d3");
        RegisterList.getInstance().organizeRegisters();
    }

    public void  totalLabelBackgroundColor(double amount){
        if (amount>0){
            totalLabelSumatory.setStyle("-fx-background-color: #66BB6A");
        } else if (amount<0) {

            totalLabelSumatory.setStyle("-fx-background-color: #a63c3c");
        }else totalLabelSumatory.setStyle("-fx-background-color: #d3d3d3");
    }
    @FXML
    void addRegisterAction(ActionEvent event) {
        Stage stage = (Stage) balanceButton.getScene().getWindow();
        stage.close();
        AlfaBankMain.openWindow("registerBankMovement.fxml");

    }

    @FXML
    void balancePressed(ActionEvent event) {
        tableMain.setItems(RegisterList.getInstance().getListOfRegisters());
        totalLabelSumatory.setText(String.valueOf(RegisterList.getInstance().totalBalance()));
        totalLabelBackgroundColor(Double.parseDouble(totalLabelSumatory.getText()));
        expenseButton.setStyle(null);
        incomesButton.setStyle(null);
        balanceButton.setStyle("-fx-background-color: #d3d3d3");
    }

    @FXML
    void expensePress(ActionEvent event) {

        tableMain.setItems(RegisterList.getInstance().expenseFilter());
        totalLabelSumatory.setText(String.valueOf(RegisterList.getInstance().totalExpense()));
        totalLabelBackgroundColor(Double.parseDouble(totalLabelSumatory.getText()));

        balanceButton.setStyle(null);
        incomesButton.setStyle(null);
        expenseButton.setStyle("-fx-background-color: #d3d3d3");
    }

    @FXML
    void income(ActionEvent event) {

        tableMain.setItems(RegisterList.getInstance().incomeFilter());
        totalLabelSumatory.setText(String.valueOf(RegisterList.getInstance().totalIncomes()));
        totalLabelBackgroundColor(Double.parseDouble(totalLabelSumatory.getText()));

        expenseButton.setStyle(null);
        balanceButton.setStyle(null);
        incomesButton.setStyle("-fx-background-color: #d3d3d3");
    }


}
