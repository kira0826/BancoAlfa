package com.main.alfabank.model.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RegisterList {

    /**
     * Para el manejo de un patron single Tone, lo que hacemos trabajar con un
     * atributo estatico, un constructor privado, y un atributo instance que nos
     * permitará saber si la lista a guardar ya fue creada o no.
     */
    private ObservableList<Registrable> listOfRegisters = FXCollections.observableArrayList();
    private static RegisterList instance = null;


    private RegisterList(){}

    public static RegisterList getInstance(){
        if(instance == null) {
            instance = new RegisterList();
        }
        return instance;
    }

    public void organizeRegisters(){
        Collections.sort(getInstance().getListOfRegisters(), Comparator.comparing(a -> ((Register) a)));
    }

    public boolean addRegister(Registrable registrable){
        getInstance().listOfRegisters.add(registrable);
        return true;
    }
    public ObservableList<Registrable>  incomeFilter(){
        ArrayList<Income> incomeArrayList = new ArrayList<>();
        for (Registrable r : instance.getListOfRegisters()) {
            if (r instanceof Income){
                incomeArrayList.add((Income) r);
            }
        }

        ObservableList<Registrable> incomes = FXCollections.observableArrayList();
        incomes.addAll(incomeArrayList);
        return incomes;
    }

    public ObservableList<Registrable> expenseFilter(){
        ArrayList<Expense> out = new ArrayList<>();
        for (Registrable r : instance.getListOfRegisters()) {
            if (r instanceof Expense){
                out.add((Expense) r);
            }
        }

        ObservableList<Registrable> expenses = FXCollections.observableArrayList();
        expenses.addAll(out);
        return  expenses;
    }

    public Double totalBalance(){
        double sum = 0;
        for (Registrable register: getInstance().listOfRegisters
             ) {
            sum += ((Register)register).getAmount();
        }
        return Double.valueOf(sum);
    }
    public Double totalIncomes(){
        double sum = 0;
        for (Registrable register: getInstance().listOfRegisters
        ) {
            if (register instanceof Income) sum += ((Register)register).getAmount();
        }
        return Double.valueOf(sum);
    }
    public Double totalExpense(){
        double sum = 0;
        for (Registrable register: getInstance().listOfRegisters
        ) {
            if (register instanceof Expense) sum += ((Register)register).getAmount();
        }
        return Double.valueOf(sum);
    }


    public ObservableList<Registrable> getListOfRegisters() {
        return listOfRegisters;
    }

    public void setListOfRegisters(ObservableList<Registrable> listOfRegisters) {
        this.listOfRegisters = listOfRegisters;
    }

    public static void setInstance(RegisterList instance) {
        RegisterList.instance = instance;
    }
}
