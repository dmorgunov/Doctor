package com.company.gui;

import com.company.Appointment;

import com.company.XmlDoctor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Controller  {

    @FXML TableView<Appointment> tableViewAppointment;
    @FXML TableColumn<Appointment, Integer> tableColumnDay;
    @FXML TableColumn<Appointment, Integer> tableColumnVisitors;
    @FXML TableColumn<Appointment, String> tableColumnComment;
    @FXML Button sortByVis, sortByComLen;
    @FXML TextField docName, docExp;

    private XmlDoctor doc = new XmlDoctor();
    private ObservableList<Appointment> observableList;

    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        // Починаємо шукати з поточної теки:
        fileChooser.setInitialDirectory(new File("."));
        // Встановлюємо фільтри для пошуку файлів:
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML-файли (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Усі файли (*.*)", "*.*"));
        // Вказуемо заголовк вікна:
        fileChooser.setTitle(title);
        return fileChooser;
    }

    public static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }


    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void updateSourceData() {
        // Переписуємо дані в модель з observableList
        doc.clearAppointments();
        for (Appointment c : observableList) {
            doc.addAppointment(c);
        }
    }

    private void updateDay(TableColumn.CellEditEvent<Appointment, Integer> t) {
        Appointment c = (Appointment) t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setDay(t.getNewValue());
    }

    private void updateVisitors(TableColumn.CellEditEvent<Appointment, Integer> t) {
        Appointment c = (Appointment) t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setVisitots(t.getNewValue());
    }

    private void updateComment(TableColumn.CellEditEvent<Appointment, String> t) {
        Appointment c = (Appointment) t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setComment(t.getNewValue());
    }

    private void updateTable() {
        List<Appointment> list = new ArrayList<Appointment>();
        observableList = FXCollections.observableList(list);
        for (int i = 0; i < doc.appointmentsCount(); i++) {
            list.add(doc.getAppointment(i));
        }
        tableViewAppointment.setItems(observableList);

        tableColumnDay.setCellValueFactory(new PropertyValueFactory<>("Day"));
        tableColumnDay.setCellFactory(
                TextFieldTableCell.<Appointment, Integer>forTableColumn(new IntegerStringConverter()));
        tableColumnDay.setOnEditCommit(t -> updateDay(t));

        tableColumnVisitors.setCellValueFactory(new PropertyValueFactory<>("Visitirs"));
        tableColumnVisitors.setCellFactory(
                TextFieldTableCell.<Appointment, Integer>forTableColumn(new IntegerStringConverter()));
        tableColumnVisitors.setOnEditCommit(t -> updateVisitors(t));

        tableColumnComment.setCellValueFactory(new PropertyValueFactory<>("Comment"));
        tableColumnComment.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnComment.setOnEditCommit(t -> updateComment(t));
    }

    @FXML
    public void doNew(ActionEvent event) {
        observableList = null;
        docName.setText("");
        docExp.setText("");
        tableViewAppointment.setItems(null);
        tableViewAppointment.setPlaceholder(new Label(""));
    }

    @FXML
    public void doOpen(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Open File");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                doc.readFromFile(file.getCanonicalPath());
                // Заповнюємо текстові поля прочитаними даними:
                docName.setText(doc.getName());
                docExp.setText(doc.getExp() + "");
                tableViewAppointment.setItems(null);
                updateTable();
            }
            catch (IOException e) {
                showError("Файл не знайдено");
            }
        }
    }

    @FXML
    public void doSave(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Save file");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                updateSourceData(); // оновлюємо дані в моделі
                doc.writeToFile(file.getCanonicalPath());
                showMessage("Success");
            }
            catch (Exception e) {
                showError("Error");
            }
        }
    }

    @FXML
    public void doExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void doAbout(ActionEvent event) {
        showMessage("by Morgunov Dima");
    }

    @FXML
    public void doSortByComLen(ActionEvent event){
        updateSourceData();
        doc.sortByComLen();
        updateTable();
    }

    @FXML
    public void doSortByVis(ActionEvent event){
        updateSourceData();
        doc.sortByVis();
        updateTable();
    }

    @FXML
    public void addRow(ActionEvent event){
        if (observableList == null) {
            updateTable(); // створюємо нові дані
        }
        observableList.add(new Appointment(0, 0, ""));
    }

    @FXML
    public void deleteLastRow(ActionEvent event){
        if (observableList == null) {
            return;
        }
        if (observableList.size() > 0) {
            observableList.remove(observableList.size() - 1);
        }
        if (observableList.size() <= 0) {
            observableList = null;
        }
    }
}
