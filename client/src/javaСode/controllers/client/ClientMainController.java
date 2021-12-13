package javaСode.controllers.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javaСode.app.Main;
import javaСode.app.util.PatternChecker;
import javaСode.entity.DocumentForView;
import javaСode.entity.PersonForView;
import javaСode.entity.Template;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static javaСode.app.Main.sendData;

public class ClientMainController {
    
    PersonForView personForView;
    DocumentForView selectedDocumentForView;
    
    @FXML
    private TableView<DocumentForView> documentsTable;
    
    @FXML
    private TableColumn<DocumentForView, String> name;
    
    @FXML
    private TableColumn<DocumentForView, String> template;
    
    @FXML
    private TableColumn<DocumentForView, String> compiler;
    
    @FXML
    private TableColumn<DocumentForView, Integer> count;
    
    @FXML
    private TableColumn<DocumentForView, Float> amount;
    
    @FXML
    private TableColumn<DocumentForView, Float> term;
    
    @FXML
    private TextArea nameAdd;
    
    @FXML
    private ComboBox<String> templateAdd;
    
    @FXML
    private TextField countAdd;
    
    @FXML
    private TextField compilerAdd;
    
    @FXML
    private TextField amountAdd;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private TextArea nameEdit;
    
    @FXML
    private ComboBox<String> templateEdit;
    
    @FXML
    private TextField countEdit;
    
    @FXML
    private TextField compilerEdit;
    
    @FXML
    private TextField amountEdit;
    
    @FXML
    void exit()
        throws IOException {
        exitButton.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/other/authorization.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ЖЦ документов");
        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
        stage.show();
    }
    
    @FXML
    void selectDocument() {
        if (documentsTable.getSelectionModel().getSelectedItem() != null) {
            selectedDocumentForView = documentsTable.getSelectionModel().getSelectedItem();
            nameEdit.setText(selectedDocumentForView.getName());
            templateEdit.getSelectionModel().select(selectedDocumentForView.getTemplate().getName());
            compilerEdit.setText(selectedDocumentForView.getCompiler());
            countEdit.setText(String.valueOf(selectedDocumentForView.getCount()));
            amountEdit.setText(String.valueOf(selectedDocumentForView.getPrice()));
        }
    }
    
    @FXML
    void addNewDocument()
        throws IOException, ClassNotFoundException {
        DocumentForView document = new DocumentForView();
        document.setName(nameAdd.getText().toUpperCase());
        document.setTemplateNameCast(templateAdd.getValue());
        document.setCompiler(compilerAdd.getText().toUpperCase());
        document.setClient(personForView.getId());
        try {
            document.setCount(Integer.parseInt(countAdd.getText()));
        } catch (NumberFormatException e) {
            document.setCount(0);
        }
        try {
            document.setPrice(Float.parseFloat(amountAdd.getText()));
        } catch (NumberFormatException e) {
            document.setPrice(0);
        }
        String checkResult = PatternChecker.documentCheck(document);
        if (!checkResult.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Проверьте заполненость полей: " + checkResult);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        } else {
            sendData("Add document");
            sendData(new Gson().toJson(document));
            initialize();
        }
    }
    
    @FXML
    void editDocument()
        throws IOException, ClassNotFoundException {
        if (selectedDocumentForView != null) {
            selectedDocumentForView.setName(nameEdit.getText().toUpperCase());
            selectedDocumentForView.setTemplateNameCast(templateEdit.getValue());
            selectedDocumentForView.setCompiler(compilerEdit.getText().toUpperCase());
            try {
                selectedDocumentForView.setCount(Integer.parseInt(countEdit.getText()));
            } catch (NumberFormatException e) {
                selectedDocumentForView.setCount(0);
            }
            try {
                selectedDocumentForView.setPrice(Float.parseFloat(amountEdit.getText()));
            } catch (NumberFormatException e) {
                selectedDocumentForView.setPrice(0);
            }
            String checkResult = PatternChecker.documentCheck(selectedDocumentForView);
            if (!checkResult.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте заполненость полей: " + checkResult);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            } else {
                sendData("Edit document");
                sendData(new Gson().toJson(selectedDocumentForView));
                initialize();
            }
        }
    }
    
    @FXML
    void deleteDocument()
        throws IOException, ClassNotFoundException {
        if (documentsTable.getSelectionModel().getSelectedItem() != null) {
            sendData("Delete document");
            sendData(new Gson().toJson(documentsTable.getSelectionModel().getSelectedItem().castToDocument()));
            initialize();
        }
    }
    
    @FXML
    void clearAdd() {
        nameAdd.setText("");
        amountAdd.setText("");
        compilerAdd.setText("");
        countAdd.setText("");
    }
    
    @FXML
    void clearEdit() {
        nameEdit.setText("");
        amountEdit.setText("");
        compilerEdit.setText("");
        countEdit.setText("");
        selectedDocumentForView = null;
    }
    
    @FXML
    void initialize()
        throws IOException, ClassNotFoundException {
        clearAdd();
        clearEdit();
        
        if (personForView == null) {
            String personInfo = (String) Main.input.readObject();
            personForView = new Gson().fromJson(personInfo, PersonForView.class);
        }
        
        sendData("Мои документы");
        sendData(new Gson().toJson(personForView));
        
        String documentsInfo = (String) Main.input.readObject();
        Type type = new TypeToken<ArrayList<DocumentForView>>() {}.getType();
        ArrayList<DocumentForView> documents = new Gson().fromJson(documentsInfo, type);
        
        ObservableList<DocumentForView> documentObservableList = FXCollections.observableArrayList(documents);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        template.setCellValueFactory(new PropertyValueFactory<>("templateName"));
        compiler.setCellValueFactory(new PropertyValueFactory<>("compiler"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        amount.setCellValueFactory(new PropertyValueFactory<>("price"));
        term.setCellValueFactory(new PropertyValueFactory<>("term"));
        documentsTable.setItems(documentObservableList);
        
        sendData("Все шаблоны");
        
        String templatesInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<Template>>() {}.getType();
        ArrayList<Template> templates = new Gson().fromJson(templatesInfo, type);
        ArrayList<String> templatesName = new ArrayList<>();
        for (Template value : templates) {
            templatesName.add(value.getName());
        }
        ObservableList<String> templateObservableList = FXCollections.observableArrayList(templatesName);
        templateAdd.setItems(templateObservableList);
        templateEdit.setItems(templateObservableList);
    }
    
}
