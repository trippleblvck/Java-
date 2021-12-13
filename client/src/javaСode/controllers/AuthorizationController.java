package javaСode.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javaСode.app.Main;
import javaСode.entity.User;

import java.io.IOException;

import static javaСode.app.Main.sendData;

public class AuthorizationController {

    @FXML
    private TextField loginField;
    
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passField;

    @FXML
    void login() throws IOException, ClassNotFoundException {
        User user = new User();
        if(loginField.getText().length()>0 && passField.getText().length()>0) {
            user.setLogin(loginField.getText());
            user.setPassword(passField.getText());
            sendData("Авторизация");
            sendData(new Gson().toJson(user));

            String serverResponse = (String) Main.input.readObject();
            if (!serverResponse.equals("Вход успешен")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(serverResponse);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                alert.showAndWait();
            } else {
                String userInfo = (String) Main.input.readObject();
                user = new Gson().fromJson(userInfo, User.class);
                switch (user.getRole()) {
                    case "Админ":
                        loginButton.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/admin/adminWindow.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("ЖЦ документов");
                        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                        stage.show();
                        break;
                    case "Клиент":
                        loginButton.getScene().getWindow().hide();
                        stage = new Stage();
                        loader = new FXMLLoader(Main.class.getResource("/resource/fxml/client/clientWindow.fxml"));
                        root = loader.load();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("ЖЦ документов");
                        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                        stage.show();
                        break;
                    case "Работник":
                        loginButton.getScene().getWindow().hide();
                        stage = new Stage();
                        loader = new FXMLLoader(Main.class.getResource("/resource/fxml/worker/workerWindow.fxml"));
                        root = loader.load();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("ЖЦ документов");
                        stage.getIcons().add(new Image("/resource/images/logo.jpg"));
                        stage.show();
                        break;
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите все данные");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resource/images/logo.jpg"));
            alert.showAndWait();
        }
    }

}