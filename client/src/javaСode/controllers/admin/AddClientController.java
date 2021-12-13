package javaСode.controllers.admin;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javaСode.app.Main;
import javaСode.app.util.PatternChecker;
import javaСode.entity.Person;
import javaСode.entity.User;

import java.io.IOException;

import static javaСode.app.Main.sendData;

public class AddClientController {
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField passport;
    
    @FXML
    private TextField address;
    
    @FXML
    private TextField communication;
    
    @FXML
    private TextField login;
    
    @FXML
    private TextField password;
    
    @FXML
    private RadioButton individualType;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    void cancel() throws IOException {
        cancelButton.getScene().getWindow().hide();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/admin/adminWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ЖЦ документов");
        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
        stage.show();
    }
    
    @FXML
    void save()
        throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setName(name.getText().toUpperCase());
        person.setPassport(passport.getText().toUpperCase());
        person.setAddress(address.getText().toUpperCase());
        person.setCommunication(communication.getText().toUpperCase());
        person.setType(individualType.isSelected() ? "Физ" : "Юр");
        User user = new User();
        user.setLogin(login.getText());
        user.setPassword(password.getText());
        user.setStatus(1);
        user.setRole("Клиент");
    
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
                if(serverAnswer.equals("Успешно добавлено"))
                    cancel();
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
    void selectIndividual() {
        passport.setPromptText("Паспорт");
    }
    
    @FXML
    void unselectIndividual() {
        passport.setPromptText("УНП");
    }
    
}
