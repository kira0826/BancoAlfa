package com.main.alfabank.model.controllers;

import com.main.alfabank.AlfaBankMain;
import com.main.alfabank.model.domain.Expense;
import com.main.alfabank.model.domain.Income;
import com.main.alfabank.model.domain.MovementType;
import com.main.alfabank.model.domain.RegisterList;
import com.main.alfabank.model.exceptions.InvalidAmount;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class RegisterBankMovementController implements Initializable {


    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private Button goBackButton;

    @FXML
    private Button saveMovementButton;

    @FXML
    private ChoiceBox<MovementType> typeMovements;

    @FXML
    private TextField value;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeMovements.setItems(FXCollections.observableArrayList(MovementType.values()));
    }

    @FXML
    void registerMovement(ActionEvent event) {
        try{
            if (date.getValue() == null || description.getText() ==null
                    ||typeMovements.getValue() == null || value.getText() ==null){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: al menos un campoo vacio.");
                alert.setContentText("Por favor ingrese todos los datos solicitados");
                alert.showAndWait();
            }else {
                ZonedDateTime zonedDateTime = date.getValue().atStartOfDay(ZoneId.systemDefault());
                Date currentDate = Date.from(zonedDateTime.toInstant());
                boolean isRegistered = false;
                if (typeMovements.getValue().equals(MovementType.GASTO)){
                    if (Double.parseDouble(value.getText()) > 0) throw new InvalidAmount();
                    Expense expense = new Expense(Double.parseDouble(value.getText()),currentDate, description.getText() );
                    isRegistered = RegisterList.getInstance().addRegister(expense);
                } else if (typeMovements.getValue().equals(MovementType.INGRESO)){
                    if (Double.parseDouble(value.getText()) < 0) throw new InvalidAmount();
                    Income income = new Income(Double.parseDouble(value.getText()),currentDate, description.getText() );
                    isRegistered = RegisterList.getInstance().addRegister(income);
                }

                if (!isRegistered) throw new Exception();

                description.setText("");
                typeMovements.setValue(null);
                value.setText("");
                date.setValue(null);
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Al menos un dato ingresado no valido. Recuerda que el gasto debe ser no positivo y el ingreso no negativo.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void turnBack(ActionEvent event) {
        Stage stage = (Stage) date.getScene().getWindow();
        stage.close();
        AlfaBankMain.openWindow("registerTableDesign.fxml");
    }
}
