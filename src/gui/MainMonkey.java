package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import server_manager.ServerManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainMonkey extends Application {
    @FXML
    private Label outputArea;

    File fileName = new File("/home/mon/monkey_vault.csv");
    public Stage mainStage;
    ServerManager serverManager = new ServerManager();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        mainStage = primaryStage;
        primaryStage.setTitle("Clever Monkey Video View Data Analizer");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
    }

    public void getFileName() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Data File");
        File file = fc.showOpenDialog(mainStage);
        fileName = (file != null) ? file : fileName;
    }

    public void analizeData() {
        try {
            serverManager.findValue(fileName);
            String outputText = "";
            outputText += "Data file contains data for " + serverManager.params.getBandwidthPerDay().size() + " days\n";
            outputText += "Reserved Server Price: $" + serverManager.params.getReservedHourlyCost() + " per hour\n";
            outputText += "On Demand Server Price : $" + serverManager.params.getOnDemandHourlyCost() + " per hour\n";
            outputText += "We Recommend using " + (int) serverManager.params.getAvgServers() + " reserved Server Instances\n";
            outputText += (int) (serverManager.params.getAvgServers() * 0.5) + " servers should also be avilable on Demand\n";
            outputText += "Which will only run during peak times\n";
            outputText += "Estimated Cost = $" + (serverManager.getEstimatedCost()) + " per hour\n\n";
            outputArea.setText(outputText);
        } catch (FileNotFoundException e) {
            System.err.print("File Not Found" + e.getMessage());
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void exitApp(ActionEvent actionEvent) {
        Platform.exit();
    }
}
