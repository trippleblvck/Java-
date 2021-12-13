package javaСode.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends Application {
    
    static public Socket connection;
    static public ObjectOutputStream output;
    static public ObjectInputStream input;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            connection = new Socket("127.0.0.1", 4041);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/resource/fxml/other/authorization.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ЖЦ документов");
        primaryStage.getIcons().add(new Image("/resource/images/logo.jpg"));
        primaryStage.show();
    }
    
    
    public static void sendData(Object message) {
        try {
            output.flush();
            output.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
