
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.Inventory;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML // fx:id="firstNameCol"
    private TableColumn<Inventory, String> firstNameCol;

    @FXML // fx:id="lastNameCol"
    private TableColumn<Inventory, String> lastNameCol;

    @FXML // fx:id="netIDCol"
    private TableColumn<Inventory, String> netIDCol;

    @FXML // fx:id="ageCol"
    private TableColumn<Inventory, Number> ageCol;

    @FXML // fx:id="gpaCol"
    private TableColumn<Inventory, Number> gpaCol;

    @FXML // fx:id="majorCol"
    private TableColumn<Inventory, String> majorCol;

    @FXML // fx:id="uinCol"
    private TableColumn<Inventory, String> uinCol;

    @FXML // fx:id="genderCol"
    private TableColumn<Inventory, String> genderCol;

    @FXML // fx:id="firstNameField"
    private TextField firstNameField;

    @FXML // fx:id="lastNameField"
    private TextField lastNameField;

    @FXML // fx:id="netIDField"
    private TextField netIDField;

    @FXML // fx:id="ageField"
    private TextField ageField;

    @FXML // fx:id="gpaField"
    private TextField gpaField;

    @FXML // fx:id="majorField"
    private TextField majorField;

    @FXML // fx:id="uinField"
    private TextField uinField;

    @FXML
    private TextField filterInput;

    @FXML // fx:id="genderBox"
    private ComboBox<String> genderBox;
    ObservableList<String> genderBoxData = FXCollections.observableArrayList();

    @FXML
    private TableView<Inventory> studentTable;

    @FXML // fx:id="addBtn"
    private Button addBtn;

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn;

    @FXML
    private MenuBar fileMenu;

    ObservableList<Inventory> observableInventoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add Listener to filterField
        filterInput.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filterStudentList((String) oldValue, (String) newValue);

            }
        });
        //initialize editable attributes
        studentTable.setEditable(true);
        firstNameCol.setOnEditCommit(e -> firstNameCol_OnEditCommit(e));
        lastNameCol.setOnEditCommit(e -> lastNameCol_OnEditCommit(e));
        uinCol.setOnEditCommit(e -> uinCol_OnEditCommit(e));






        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        uinCol.setCellFactory(TextFieldTableCell.forTableColumn());




        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().serialnumberProperty());



        //initialize gender ComboBox
        genderBoxData.add(new String("Export as HTML"));
        genderBoxData.add(new String("Export as tsv"));

        genderBox.setItems(genderBoxData);


        addBtn.setDisable(true);
        deleteBtn.setDisable(true);
        studentTable.setItems(observableInventoryList);
        studentTable.setEditable(true);
        studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentTable.setPlaceholder(new Label("Your Table is Empty"));

        firstNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (firstNameField.isFocused()) {
                    addBtn.setDisable(false);
                }
            }
        });
        studentTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (studentTable.isFocused()) {
                    deleteBtn.setDisable(false);
                }
            }
        });
    }//end initialize

    /*
    ----------------------------------------------Control handlers---------------------------------------------
     */
    public void handleAddButtonClick(ActionEvent event) {
        /*
        Get input from user and add to Table
         */
        if (observableInventoryList.size() < 10) {
            if (isValidInput(event)) {
                if (genderBox.getValue().equals("Export as html")) {
                    Inventory inventory = new Inventory();
                    inventory.setValue(firstNameField.getText());
                    inventory.setSerialnumber(lastNameField.getText());
                    inventory.setExportproperty(Integer.parseInt(ageField.getText()));
                    inventory.setInventorynumber(majorField.getText());
                    inventory.setNetID(netIDField.getText());
                    inventory.setUin(uinField.getText());
                    inventory.setGradepoint(Double.parseDouble(gpaField.getText()));
                    inventory.setExportfile(genderBox.getValue());
                    observableInventoryList.add(inventory);
                    System.out.println(inventory.toString());
                    firstNameField.clear();
                    lastNameField.clear();
                    uinField.clear();
                    netIDField.clear();
                    majorField.clear();
                    ageField.clear();
                    gpaField.clear();
                    genderBox.setValue("Exported as html");
                }
                if (genderBox.getValue().equals("Export as tsv")) {
                    Inventory inventory = new Inventory();
                    inventory.setValue(firstNameField.getText());
                    inventory.setSerialnumber(lastNameField.getText());
                    inventory.setExportproperty(Integer.parseInt(ageField.getText()));
                    inventory.setInventorynumber(majorField.getText());
                    inventory.setNetID(netIDField.getText());
                    inventory.setUin(uinField.getText());
                    inventory.setGradepoint(Double.parseDouble(gpaField.getText()));
                    inventory.setExportfile(genderBox.getValue());
                    observableInventoryList.add(inventory);
                    System.out.println(inventory.toString());
                    firstNameField.clear();
                    lastNameField.clear();
                    uinField.clear();
                    netIDField.clear();
                    majorField.clear();
                    ageField.clear();
                    gpaField.clear();
                    genderBox.setValue("Exported as tsv");
                }
            }
        } else {
            Alert sizeAlert = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            sizeAlert.setContentText("You may only hold 10 Students at this time");
            sizeAlert.initModality(Modality.APPLICATION_MODAL);
            sizeAlert.initOwner(owner);
            sizeAlert.showAndWait();
            if (sizeAlert.getResult() == ButtonType.OK) {
                sizeAlert.close();
                firstNameField.clear();
                lastNameField.clear();
                uinField.clear();
                netIDField.clear();
                majorField.clear();
                ageField.clear();
                gpaField.clear();
                genderBox.setValue("Gender");
            }
        }
    }
    /*
    In case of empty fields. Gives alert for respective empty field and requests focus on that field.
     */
    private boolean isValidInput(ActionEvent event) {

        Boolean validInput = true;

        if(firstNameField == null || firstNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("Value is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                firstNameField.requestFocus();
            }
        }
        if(lastNameField == null || lastNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyLastName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("Serial number is EMPTY");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if (emptyLastName.getResult() == ButtonType.OK) {
                emptyLastName.close();
                lastNameField.requestFocus();
            }
        }
        if(uinField == null || uinField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyUIN = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyUIN.setContentText("ID is EMPTY");
            emptyUIN.initModality(Modality.APPLICATION_MODAL);
            emptyUIN.initOwner(owner);
            emptyUIN.showAndWait();
            if (emptyUIN.getResult() == ButtonType.OK) {
                emptyUIN.close();
                uinField.requestFocus();
            }
        }
        if(netIDField == null || netIDField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyNetID = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNetID.setContentText("inventory Id is empty");
            emptyNetID.initModality(Modality.APPLICATION_MODAL);
            emptyNetID.initOwner(owner);
            emptyNetID.showAndWait();
            if (emptyNetID.getResult() == ButtonType.OK) {
                emptyNetID.close();
                netIDField.requestFocus();
            }
        }
        if(majorField == null || majorField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyMajor = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyMajor.setContentText("Major is EMPTY");
            emptyMajor.initModality(Modality.APPLICATION_MODAL);
            emptyMajor.initOwner(owner);
            emptyMajor.showAndWait();
            if (emptyMajor.getResult() == ButtonType.OK) {
                emptyMajor.close();
                majorField.requestFocus();
            }
        }
        if(ageField == null || ageField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyAge = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyAge.setContentText("Age is EMPTY");
            emptyAge.initModality(Modality.APPLICATION_MODAL);
            emptyAge.initOwner(owner);
            emptyAge.showAndWait();
            if (emptyAge.getResult() == ButtonType.OK) {
                emptyAge.close();
                ageField.requestFocus();
            }
        }
        if(gpaField == null || gpaField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyGPA = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGPA.setContentText("GPA is EMPTY");
            emptyGPA.initModality(Modality.APPLICATION_MODAL);
            emptyGPA.initOwner(owner);
            emptyGPA.showAndWait();
            if (emptyGPA.getResult() == ButtonType.OK) {
                emptyGPA.close();
                gpaField.requestFocus();
            }
        }
        if(genderBox == null || genderBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Gender is EMPTY");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK) {
                emptyGender.close();
                genderBox.requestFocus();
            }
        }
        return validInput;
    }
    /*
    handle column edits
     */
    public void firstNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setValue(cellEditEvent.getNewValue());
    }
    public void lastNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setSerialnumber(cellEditEvent.getNewValue());
    }
    public void uinCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setUin(cellEditEvent.getNewValue());
    }
    public void netIDCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setNetID(cellEditEvent.getNewValue());
    }
    public void majorCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setInventorynumber(cellEditEvent.getNewValue());
    }
    public void ageCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, Integer>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setExportproperty(cellEditEvent.getNewValue());
    }
    public void gpaCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, Double> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, Double>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setGradepoint(cellEditEvent.getNewValue());
    }
    public void genderCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Inventory, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Inventory, String>) e;
        Inventory inventory = cellEditEvent.getRowValue();
        inventory.setExportfile(cellEditEvent.getNewValue());
    }
    public void handleDeleteButtonClick(ActionEvent event) {
        if(!observableInventoryList.isEmpty()) {
            System.out.println("Delete button clicked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                observableInventoryList.removeAll(studentTable.getSelectionModel().getSelectedItems());
                studentTable.getSelectionModel().clearSelection();
            }
            else {
                deleteAlert.close();
            }
        }
    }
    public void handleClearButtonClick(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        uinField.clear();
        netIDField.clear();
        majorField.clear();
        ageField.clear();
        gpaField.clear();
        genderBox.setValue("Gender");
    }
    //filter table by first or last name
    public void filterStudentList(String oldValue, String newValue) {
        ObservableList<Inventory> filteredList = FXCollections.observableArrayList();
        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            studentTable.setItems(observableInventoryList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Inventory students : studentTable.getItems()) {
                String filterFirstName = students.getValue();
                String filterLastName = students.getSerialnumber();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            studentTable.setItems(filteredList);
        }
    }
    public void handleSave(ActionEvent event) {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Student Table");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableInventoryList.isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null) {
                saveFile(studentTable.getItems(), file);
            }
        }
    }
    public void saveFile(ObservableList<Inventory> observableInventoryList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(Inventory students : observableInventoryList) {
                outWriter.write(students.toString());
                outWriter.newLine();
            }
            System.out.println(observableInventoryList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
    public void closeApp(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }
}







