package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.company.classes.Controller;

import java.io.IOException;

public class Main extends Application {
    public static Thread emuThread;
    public static Controller controller;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("window/interface.fxml"));
        Parent root = loader.load();
        controller =  loader.getController();
        emuThread = new Thread(new com.company.classes.TLauncher());


        stage.setTitle("VTaskViewer");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();

    }


    @Override
    public void stop() throws Exception {
        super.stop();
        if(emuThread.isAlive())
            emuThread.stop();
    }

}

