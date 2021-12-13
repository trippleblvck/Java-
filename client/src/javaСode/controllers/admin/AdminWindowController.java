package javaСode.controllers.admin;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javaСode.app.Main;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import javaСode.app.util.PatternChecker;
import javaСode.entity.DocumentForView;
import javaСode.entity.Person;
import javaСode.entity.PersonForView;
import javaСode.entity.StateBody;
import javaСode.entity.Template;
import javaСode.entity.User;

import java.util.ArrayList;
import java.util.Locale;

import static javaСode.app.Main.sendData;

public class AdminWindowController {
    
    public Template selectedTemplate;
    public StateBody selectedStateBody;
    public PersonForView selectedWorker;

    @FXML
    private TableView<PersonForView> legalEntitiesTable;
    
    @FXML
    private TableColumn<PersonForView, String> legalEntitiesName;
    
    @FXML
    private TableColumn<PersonForView, String> legalEntitiesPassport;
    
    @FXML
    private TableColumn<PersonForView, String> legalEntitiesCommunication;
    
    @FXML
    private TableColumn<PersonForView, String> legalEntitiesAddress;
    
    @FXML
    private TextField clientLogin;
    
    @FXML
    private TextField clientPassword;
    
    @FXML
    private TextField clientStatus;
    
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
    private TableView<PersonForView> individualsTable;
    
    @FXML
    private TableColumn<PersonForView, String> individualName;
    
    @FXML
    private TableColumn<PersonForView, String> individualsPassport;
    
    @FXML
    private TableColumn<PersonForView, String> individualsCommunication;
    
    @FXML
    private TableColumn<PersonForView, String> individualsAddress;
    
    @FXML
    private TableView<PersonForView> workersTable;
    
    @FXML
    private TableColumn<PersonForView, String> workerName;
    
    @FXML
    private TableColumn<PersonForView, String> workerPassport;
    
    @FXML
    private TableColumn<PersonForView, String> workerCommunication;
    
    @FXML
    private TableColumn<PersonForView, String> workerAddress;
    
    @FXML
    private TableColumn<PersonForView, String> workerLogin;
    
    @FXML
    private TableColumn<PersonForView, String> workerPassword;
    
    @FXML
    private TextField workerNameAdd;
    
    @FXML
    private TextField workerPassportAdd;
    
    @FXML
    private TextField workerTelephoneAdd;
    
    @FXML
    private TextField workerEmailAdd;
    
    @FXML
    private TextField workerLoginAdd;
    
    @FXML
    private TextField workerPasswordAdd;
    
    @FXML
    private TextField workerNameEdit;
    
    @FXML
    private TextField workerPassportEdit;
    
    @FXML
    private TextField workerTelephoneEdit;
    
    @FXML
    private TextField workerEmailEdit;
    
    @FXML
    private TextField workerLoginEdit;
    
    @FXML
    private TextField workerPasswordEdit;
    
    @FXML
    private TableView<Template> categoriesTable;
    
    @FXML
    private TableColumn<Template, String> categoriesName;
    
    @FXML
    private TableColumn<Template, Float> categoriesAmount;
    
    @FXML
    private TextField categoryNameAdd;
    
    @FXML
    private TextField categoryAmountAdd;
    
    @FXML
    private TextField categoryNameEdit;
    
    @FXML
    private TextField categoryAmountEdit;
    
    @FXML
    private BarChart<String, Float> categoryGraph;
    
    @FXML
    private TableView<StateBody> stateBodiesTable;
    
    @FXML
    private TableColumn<StateBody, String> stateBodyName;
    
    @FXML
    private TableColumn<StateBody, Float> stateBodyAmount;
    
    @FXML
    private TableColumn<StateBody, String> stateBodyCommunication;
    
    @FXML
    private TextField stateBodyNameAdd;
    
    @FXML
    private TextField stateBodyTermAdd;
    
    @FXML
    private TextField stateBodyCommunicationAdd;
    
    @FXML
    private TextField stateBodyNameEdit;
    
    @FXML
    private TextField stateBodyTermEdit;
    
    @FXML
    private TextField stateBodyCommunicationEdit;
    
    @FXML
    private Button exitButton;
    
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
    void addNewClient()
        throws IOException {
        exitButton.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/admin/addClient.fxml"));
        Parent root = loader.load();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ЖЦ документов");
        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
        stage.show();
    }
    
    @FXML
    void editClient()
        throws IOException {
        PersonForView personForView = null;
        if(individualsTable.getSelectionModel().getSelectedItem() != null && individualsTable.isVisible()){
            personForView = individualsTable.getSelectionModel().getSelectedItem();
        }else if(legalEntitiesTable.getSelectionModel().getSelectedItem() != null && legalEntitiesTable.isVisible()){
            personForView = legalEntitiesTable.getSelectionModel().getSelectedItem();
        }
        if(personForView != null) {
            exitButton.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/admin/editClient.fxml"));
            Parent root = loader.load();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("ЖЦ документов");
            EditClientController editClientController = loader.getController();
            editClientController.initialize(personForView);
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите клиента");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void deleteClient()
        throws IOException, ClassNotFoundException {
        PersonForView personForView = null;
        if(individualsTable.getSelectionModel().getSelectedItem() != null && individualsTable.isVisible()){
            personForView = individualsTable.getSelectionModel().getSelectedItem();
        }else if(legalEntitiesTable.getSelectionModel().getSelectedItem() != null && legalEntitiesTable.isVisible()){
            personForView = legalEntitiesTable.getSelectionModel().getSelectedItem();
        }
        if (personForView != null) {
            sendData("Delete person");
            sendData(new Gson().toJson(personForView.getUser()));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите клиента");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
        
    }
    
    @FXML
    void selectStateBody() {
        if (stateBodiesTable.getSelectionModel().getSelectedItem() != null) {
            selectedStateBody = stateBodiesTable.getSelectionModel().getSelectedItem();
            stateBodyNameEdit.setText(selectedStateBody.getName());
            stateBodyCommunicationEdit.setText(selectedStateBody.getCommunication());
            stateBodyTermEdit.setText(String.valueOf(selectedStateBody.getTerm()));
        }
    }
    
    @FXML
    void addNewStateBody()
        throws IOException, ClassNotFoundException {
        StateBody stateBody = new StateBody();
        stateBody.setName(stateBodyNameAdd.getText().toUpperCase());
        try{
            stateBody.setTerm(Float.parseFloat(stateBodyTermAdd.getText()));
        }catch (NumberFormatException e){
            stateBody.setTerm(0);
        }
        stateBody.setCommunication(stateBodyCommunicationAdd.getText().toUpperCase());
        if(PatternChecker.stateBodyCheck(stateBody).equals("")) {
            sendData("Add body");
            sendData(new Gson().toJson(stateBody));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Проверьте заполненность полей: " + PatternChecker.stateBodyCheck(stateBody));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void editStateBody()
        throws IOException, ClassNotFoundException {
        if (selectedStateBody != null) {
            selectedStateBody.setName(stateBodyNameEdit.getText().toUpperCase());
            try{
                selectedStateBody.setTerm(Float.parseFloat(stateBodyTermEdit.getText()));
            }catch (NumberFormatException e){
                selectedStateBody.setTerm(0);
            }
            selectedStateBody.setCommunication(stateBodyCommunicationEdit.getText().toUpperCase(Locale.ROOT));
            if(PatternChecker.stateBodyCheck(selectedStateBody).equals("")) {
                sendData("Edit body");
                sendData(new Gson().toJson(selectedStateBody));
                initialize();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте заполненность полей: " + PatternChecker.stateBodyCheck(selectedStateBody));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    void deleteStateBody()
        throws IOException, ClassNotFoundException {
        if (selectedStateBody != null) {
            sendData("Delete body");
            sendData(new Gson().toJson(selectedStateBody));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите орган");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void clearStateBodyAdd() {
        stateBodyNameAdd.setText("");
        stateBodyCommunicationAdd.setText("");
        stateBodyTermAdd.setText("");
    }
    
    @FXML
    void clearStateBodyEdit() {
        stateBodyNameEdit.setText("");
        stateBodyCommunicationEdit.setText("");
        stateBodyTermEdit.setText("");
        selectedStateBody = null;
    }
    
    @FXML
    void selectTemplate() {
        if (categoriesTable.getSelectionModel().getSelectedItem() != null) {
            selectedTemplate = categoriesTable.getSelectionModel().getSelectedItem();
            categoryNameEdit.setText(selectedTemplate.getName());
            categoryAmountEdit.setText(String.valueOf(selectedTemplate.getTerm()));
        }
    }
    
    @FXML
    void addNewTemplate()
        throws IOException, ClassNotFoundException {
        Template template = new Template();
        template.setName(categoryNameAdd.getText().toUpperCase());
        try {
            template.setTerm(Float.parseFloat(categoryAmountAdd.getText()));
        } catch (NumberFormatException e) {
            template.setTerm(0);
        }
        if (PatternChecker.templateCheck(template).equals("")) {
            sendData("Add template");
            sendData(new Gson().toJson(template));
            initialize();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Проверьте заполненость полей: " + PatternChecker.templateCheck(template));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void editTemplate()
        throws IOException, ClassNotFoundException {
        if (selectedTemplate != null) {
            selectedTemplate.setName(categoryNameEdit.getText().toUpperCase());
            try {
                selectedTemplate.setTerm(Float.parseFloat(categoryAmountEdit.getText()));
            } catch (NumberFormatException e) {
                selectedTemplate.setTerm(0);
            }
            if (PatternChecker.templateCheck(selectedTemplate).equals("")) {
                sendData("Edit template");
                sendData(new Gson().toJson(selectedTemplate));
                initialize();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    void deleteTemplate()
        throws IOException, ClassNotFoundException {
        if (selectedTemplate != null) {
            sendData("Delete template");
            sendData(new Gson().toJson(selectedTemplate));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите шаблон");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void clearTemplateAdd() {
        categoryNameAdd.setText("");
        categoryAmountAdd.setText("");
    }
    
    @FXML
    void clearTemplateEdit() {
        categoryNameEdit.setText("");
        categoryAmountEdit.setText("");
        selectedTemplate = null;
    }
    
    @FXML
    void lockUnlockClient()
        throws IOException, ClassNotFoundException {
        User user = null;
        if (individualsTable.getSelectionModel().getSelectedItem() != null && individualsTable.isVisible()) {
            user = individualsTable.getSelectionModel().getSelectedItem().getUser();
        } else if (legalEntitiesTable.getSelectionModel().getSelectedItem() != null && legalEntitiesTable.isVisible()) {
            user = legalEntitiesTable.getSelectionModel().getSelectedItem().getUser();
        }
        if (user != null) {
            user.setStatus(user.getStatus() == 1 ? 2 : 1);
            sendData("Lock/unlock");
            sendData(new Gson().toJson(user));
        }
        initialize();
    }
    
    @FXML
    void selectClient()
        throws IOException, ClassNotFoundException {
        PersonForView personForView = null;
        if (individualsTable.getSelectionModel().getSelectedItem() != null && individualsTable.isVisible()) {
            personForView = individualsTable.getSelectionModel().getSelectedItem();
        } else if (legalEntitiesTable.getSelectionModel().getSelectedItem() != null && legalEntitiesTable.isVisible()) {
            personForView = legalEntitiesTable.getSelectionModel().getSelectedItem();
        }
        if (personForView != null) {
            clientLogin.setText(personForView.getUser().getLogin());
            clientPassword.setText(personForView.getUser().getPassword());
            clientStatus.setText(personForView.getUser().getStatus() == 1 ? "Активный" : "Заблокирован");
        }
        if(personForView != null) {
            sendData("Мои документы");
            sendData(new Gson().toJson(personForView));

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
    
    @FXML
    void selectWorker() {
        if (workersTable.getSelectionModel().getSelectedItem() != null) {
            selectedWorker = workersTable.getSelectionModel().getSelectedItem();
            workerNameEdit.setText(selectedWorker.getName());
            workerPassportEdit.setText(selectedWorker.getPassport());
            workerTelephoneEdit.setText(selectedWorker.getAddress());
            workerEmailEdit.setText(selectedWorker.getCommunication());
            workerLoginEdit.setText(selectedWorker.getUser().getLogin());
            workerPasswordEdit.setText(selectedWorker.getUser().getPassword());
        }
    }
    
    @FXML
    void addNewWorker()
        throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setName(workerNameAdd.getText().toUpperCase());
        person.setPassport(workerPassportAdd.getText().toUpperCase());
        person.setAddress(workerTelephoneAdd.getText().toUpperCase());
        person.setCommunication(workerEmailAdd.getText().toUpperCase());
        person.setType("-");
        User user = new User();
        user.setLogin(workerLoginAdd.getText());
        user.setPassword(workerPasswordAdd.getText());
        user.setStatus(1);
        user.setRole("Работник");
    
        if (PatternChecker.personCheck(person).equals("")) {
            if (PatternChecker.userCheck(user).equals("")) {
                sendData("Add person");
                sendData(new Gson().toJson(user));
                sendData(new Gson().toJson(person));
                String serverAnswer = (String) Main.input.readObject();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Информация");
                alert.setHeaderText(serverAnswer);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
                initialize();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте заполненость полей: " + PatternChecker.userCheck(user));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Проверьте заполненость полей: " + PatternChecker.personCheck(person));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }

    @FXML
    void editWorker()
        throws IOException, ClassNotFoundException {
        if (selectedWorker != null) {
            selectedWorker.setName(workerNameEdit.getText().toUpperCase());
            selectedWorker.setPassport(workerPassportEdit.getText().toUpperCase());
            selectedWorker.setAddress(workerTelephoneEdit.getText().toUpperCase());
            selectedWorker.setCommunication(workerEmailEdit.getText().toUpperCase(Locale.ROOT));
            selectedWorker.getUser().setLogin(workerLoginEdit.getText());
            selectedWorker.getUser().setPassword(workerPasswordEdit.getText());
            if (PatternChecker.personForViewCheck(selectedWorker).equals("")) {
                sendData("Edit person");
                sendData(new Gson().toJson(selectedWorker));
                initialize();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Проверьте заполненость полей: " + PatternChecker.personForViewCheck(selectedWorker));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    void deleteWorker()
        throws IOException, ClassNotFoundException {
        if (workersTable.getSelectionModel().getSelectedItem() != null) {
            sendData("Delete person");
            sendData(new Gson().toJson(workersTable.getSelectionModel().getSelectedItem().getUser()));
            initialize();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите работника");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }
    
    @FXML
    void clearWorkerAdd() {
        workerNameAdd.setText("");
        workerPassportAdd.setText("");
        workerTelephoneAdd.setText("");
        workerEmailAdd.setText("");
        workerLoginAdd.setText("");
        workerPasswordAdd.setText("");
    }
    
    @FXML
    void clearWorkerEdit() {
        workerNameEdit.setText("");
        workerPassportEdit.setText("");
        workerTelephoneEdit.setText("");
        workerEmailEdit.setText("");
        workerLoginEdit.setText("");
        workerPasswordEdit.setText("");
        selectedWorker = null;
    }
    
    @FXML
    void selectFirstType() {
        individualsTable.setVisible(true);
        legalEntitiesTable.setVisible(false);
    }

    @FXML
    void selectSecondType() {
        individualsTable.setVisible(false);
        legalEntitiesTable.setVisible(true);
    }
    
    @FXML
    void initialize()
        throws IOException, ClassNotFoundException {
        
        clearWorkerAdd();
        clearWorkerEdit();
        
        clearTemplateAdd();
        clearTemplateEdit();
        
        clearStateBodyAdd();
        clearStateBodyEdit();
        
        clientLogin.setText("");
        clientPassword.setText("");
        clientStatus.setText("");
        
        documentsTable.getItems().clear();
        
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
        individualName.setCellValueFactory(new PropertyValueFactory<>("name"));
        individualsPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        individualsAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        individualsCommunication.setCellValueFactory(new PropertyValueFactory<>("communication"));
        individualsTable.setItems(firstClientObservableList);
        
        ObservableList<PersonForView> secondClientObservableList = FXCollections.observableArrayList(secondClientList);
        legalEntitiesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        legalEntitiesPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        legalEntitiesAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        legalEntitiesCommunication.setCellValueFactory(new PropertyValueFactory<>("communication"));
        legalEntitiesTable.setItems(secondClientObservableList);
        
        sendData("Все работники");
        
        String workersInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<PersonForView>>() {}.getType();
        ArrayList<PersonForView> workersArrayList = new Gson().fromJson(workersInfo, type);
        
        ObservableList<PersonForView> workersObservableList = FXCollections.observableArrayList(workersArrayList);
        workerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        workerPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        workerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        workerCommunication.setCellValueFactory(new PropertyValueFactory<>("communication"));
        workerLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        workerPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        workersTable.setItems(workersObservableList);
        
        sendData("Все шаблоны");
        
        String templatesInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<Template>>() {}.getType();
        ArrayList<Template> templateArrayList = new Gson().fromJson(templatesInfo, type);
        
        ObservableList<Template> templateObservableList = FXCollections.observableArrayList(templateArrayList);
        categoriesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoriesAmount.setCellValueFactory(new PropertyValueFactory<>("term"));
        categoriesTable.setItems(templateObservableList);
        
        XYChart.Series<String, Float> dataSeries = new XYChart.Series<String, Float>();
        dataSeries.getData().clear();
        dataSeries.setName("");
        for (Template template : templateArrayList) {
            dataSeries.getData().add(new XYChart.Data<String, Float>(template.getName(), template.getTerm()));
        }
        categoryGraph.getData().clear();
        categoryGraph.getData().add(dataSeries);
        
        sendData("Все органы");
        
        String bodiesInfo = (String) Main.input.readObject();
        type = new TypeToken<ArrayList<StateBody>>() {}.getType();
        ArrayList<StateBody> stateBodyArrayList = new Gson().fromJson(bodiesInfo, type);
        
        ObservableList<StateBody> stateBodyObservableList = FXCollections.observableArrayList(stateBodyArrayList);
        stateBodyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        stateBodyAmount.setCellValueFactory(new PropertyValueFactory<>("term"));
        stateBodyCommunication.setCellValueFactory(new PropertyValueFactory<>("communication"));
        stateBodiesTable.setItems(stateBodyObservableList);
    }
    
}

