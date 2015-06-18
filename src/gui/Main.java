package gui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import server_manager.ServerManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
    
    File fileName = new File("/home/mon/monkey_vault.csv");
    public Stage mainStage;
    ServerManager sm = new ServerManager();
    
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        mainStage = primaryStage;
        primaryStage.setTitle("Clever Monkey Video View Data Analizer");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    
    public void getFileName(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Data File");
        File file = fc.showOpenDialog(mainStage);
        fileName = (file != null) ? file : fileName;
    }
    public void analizeData(){
        try{
            ServerManager.findValue(fileName);
        }catch(FileNotFoundException e){
            System.err.print("File Not Found"+e.getMessage());
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
