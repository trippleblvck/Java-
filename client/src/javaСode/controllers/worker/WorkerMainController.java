package javaСode.controllers.worker;

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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javaСode.app.Main;
import javaСode.app.util.PatternChecker;
import javaСode.entity.DocumentForView;
import javaСode.entity.PersonForView;
import javaСode.entity.StateBody;
import javaСode.entity.Template;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static javaСode.app.Main.sendData;

public class WorkerMainController {
    
    public DocumentForView selectedDocument;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private TableView<PersonForView> clientsTable;
    
    @FXML
    private TableColumn<PersonForView, String> clientName;
    
    @FXML
    private TableColumn<PersonForView, String> clientPassport;
    
    @FXML
    private TableColumn<PersonForView, String> clientCommunication;
    
    @FXML
    private TableColumn<PersonForView, String> clientAddress;
    
    @FXML
    private ListView<StateBody> unselectBodies;
    
    @FXML
    private ListView<StateBody> selectBodies;
    
    @FXML
    private TableView<PersonForView> clientsTable1;
    
    @FXML
    private TableColumn<PersonForView, String> clientName1;
    
    @FXML
    private TableColumn<PersonForView, String> clientPassport1;
    
    @FXML
    private TableColumn<PersonForView, String> clientCommunication1;
    
    @FXML
    private TableColumn<PersonForView, String> clientAddress1;
    
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
    private TextArea nameEdit;
    
    @FXML
    private ComboBox<String> templateEdit;
    
    @FXML
    private TextField countEdit;
    
    @FXML
    private TextField compilerEdit;
    
    @FXML
    private TextField amountEdit;
    
    /**
     * выход из учетной записи работнкиа
     * @throws IOException
     */
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
    
    /**
     * расчет времени обработки
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void calculate()
        throws IOException, ClassNotFoundException {
        float result = 0;
        if (selectBodies.getItems().size() > 0 && selectedDocument != null) {
            result += selectedDocument.getTemplate().getTerm();
            for (int i = 0; i < selectBodies.getItems().size(); i++) {
                result += selectBodies.getItems().get(i).getTerm();
            }
            result += selectedDocument.getPrice() / 1000;
            result += selectedDocument.getTemplate().getTerm();
            selectedDocument.setTerm(result);
            sendData("Edit document");
            sendData(new Gson().toJson(selectedDocument));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите документ и хотя бы один гос.орган");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    /**
     * очистка полей для редактирования
     */
    @FXML
    void clearEdit() {
        nameEdit.setText("");
        compilerEdit.setText("");
        amountEdit.setText("");
        countEdit.setText("");
        selectedDocument = null;
    }
    
    /**
     * редактирование документа
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void editDocument()
        throws IOException, ClassNotFoundException {
        if(selectedDocument!=null){
            selectedDocument.setName(nameEdit.getText().toUpperCase());
            selectedDocument.setTemplateNameCast(templateEdit.getValue());
            selectedDocument.setCompiler(compilerEdit.getText().toUpperCase());
            try{
                selectedDocument.setCount(Integer.parseInt(countEdit.getText()));
            }catch (NumberFormatException e){
                selectedDocument.setCount(0);
            }
            try{
                selectedDocument.setPrice(Float.parseFloat(amountEdit.getText()));
            }catch (NumberFormatException e){
                selectedDocument.setPrice(0);
            }
            String checkResult = PatternChecker.documentCheck(selectedDocument);
            if(!checkResult.equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте заполненость полей: " + checkResult);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            }else{
                sendData("Edit document");
                sendData(new Gson().toJson(selectedDocument));
                initialize();
            }
        }
    }
    
    /**
     * нажатие на клиента в таблице
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void selectClient()
        throws IOException, ClassNotFoundException {
        PersonForView person = null;
        if (clientsTable.isVisible()) {
            person = clientsTable.getSelectionModel().getSelectedItem();
        }else if (clientsTable1.isVisible()) {
            person = clientsTable1.getSelectionModel().getSelectedItem();
        }
        if (person != null) {
            sendData("Мои документы");
            sendData(new Gson().toJson(person));
            String documentsInfo = (String) Main.input.readObject();
            Type type = new TypeToken<ArrayList<DocumentForView>>() {
            }.getType();
            ArrayList<DocumentForView> documents = new Gson().fromJson(documentsInfo, type);

            ObservableList<DocumentForView> documentObservableList = FXCollections.observableArrayList(documents);
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            template.setCellValueFactory(new PropertyValueFactory<>("templateName"));
            compiler.setCellValueFactory(new PropertyValueFactory<>("compiler"));
            count.setCellValueFactory(new PropertyValueFactory<>("count"));
            amount.setCellValueFactory(new PropertyValueFactory<>("price"));
            term.setCellValueFactory(new PropertyValueFactory<>("term"));
            documentsTable.setItems(documentObservableList);
        }
    }
    
    /**
     * нажатие на документ в таблице
     */
    @FXML
    void selectDocument() {
        if(documentsTable.getSelectionModel().getSelectedItem()!=null) {
            selectedDocument = documentsTable.getSelectionModel().getSelectedItem();
            nameEdit.setText(selectedDocument.getName());
            compilerEdit.setText(selectedDocument.getCompiler());
            templateEdit.setValue(selectedDocument.getTemplate().getName());
            amountEdit.setText(String.valueOf(selectedDocument.getPrice()));
            countEdit.setText(String.valueOf(selectedDocument.getCount()));
        }
    }
    
    /**
     * выбор физ лиц
     */
    @FXML
    void selectIndividuals() {
        clientsTable.setVisible(true);
        clientsTable1.setVisible(false);
        documentsTable.getItems().clear();
    }
    
    /**
     * выбор юр лиц
     */
    @FXML
    void unselectIndividuals() {
        clientsTable.setVisible(false);
        clientsTable1.setVisible(true);
        documentsTable.getItems().clear();
    }
    
    /**
     * отмена выбора гос органа
     */
    @FXML
    void toLeft() {
        if (selectBodies.getSelectionModel().getSelectedItem() != null) {
            unselectBodies.getItems().add(selectBodies.getSelectionModel().getSelectedItem());
            selectBodies.getItems().remove(selectBodies.getSelectionModel().getSelectedItem());
        }
    }
    
    /**
     * выбор гос органа
     */
    @FXML
    void toRight() {
        if (unselectBodies.getSelectionModel().getSelectedItem() != null) {
            selectBodies.getItems().add(unselectBodies.getSelectionModel().getSelectedItem());
            unselectBodies.getItems().remove(unselectBodies.getSelectionModel().getSelectedItem());
        }
    }
    
    @FXML
    void initialize()
        throws IOException, ClassNotFoundException {
        documentsTable.getItems().clear();
        selectBodies.getItems().clear();
        unselectBodies.getItems().clear();
        clearEdit();
        sendData("Все клиенты");
        
        String clientsInfo = (String) Main.input.readObject();
        Type type = new TypeToken<ArrayList<PersonForView>>() {}.getType();
        ArrayList<PersonForView> clientArrayList = new Gson().fromJson(clientsInfo, type);
        
        ArrayList<PersonForView> firstClientList = new ArrayList<>();
        ArrayList<PersonForView> secondClientList = new ArrayList<>();
        
        for (PersonForView personForView : clientArrayList) {
            if (personForView.getType().equals("Физ")) {
                firstClientList.add(personForView);
            }
        }
        
        for (PersonForView personForView : clientArrayList) {
            if (personForView.getType().equals("Юр")) {
                secondClientList.add(personForView);
            }
        }
        
        ObservableList<PersonForView> firstClientObservableList = FXCollections.observableArrayList(firstClientList);
        clientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        clientAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientCommunication.setCellValueFactory(new PropertyValueFactory<>("communication"));
        clientsTable.setItems(firstClientObservableList);
        
        ObservableList<PersonForView> secondClientObservableList = FXCollections.observableArrayList(secondClientList);
        clientName1.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientPassport1.setCellValueFactory(new PropertyValueFactory<>("passport"));
        clientAddress1.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientCommunication1.setCellValueFactory(new PropertyValueFactory<>("communication"));
        clientsTable1.setItems(secondClientObservableList);
        
        sendData("Все органы");
        
        String bodiesInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<StateBody>>() {}.getType();
        ArrayList<StateBody> stateBodyArrayList = new Gson().fromJson(bodiesInfo, type);
        
        ObservableList<StateBody> stateBodyObservableList = FXCollections.observableArrayList(stateBodyArrayList);
        selectBodies.setCellFactory(param -> new StateBodyCell());
        unselectBodies.setCellFactory(param -> new StateBodyCell());
        unselectBodies.setItems(stateBodyObservableList);
    
        sendData("Все шаблоны");
    
        String templatesInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<Template>>() {}.getType();
        ArrayList<Template> templates = new Gson().fromJson(templatesInfo, type);
        ArrayList<String> templatesName = new ArrayList<>();
        for (Template value : templates) {
            templatesName.add(value.getName());
        }
        ObservableList<String> templateObservableList = FXCollections.observableArrayList(templatesName);
        templateEdit.setItems(templateObservableList);
    }
    
}

class StateBodyCell extends ListCell<StateBody> {
    
    @Override
    protected void updateItem(StateBody item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty || item == null ? null : item.getName());
    }
    
}
