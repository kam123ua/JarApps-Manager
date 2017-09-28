package jarapps.manager;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author kam123ua
 */
public class JarAppsManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FileController.exist("name.txt");
        FileController.exist("path.txt");
        showWindows("FXMLDocument.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    public static void showWindows(String str){	
	Stage primaryStage = new Stage();
    	AnchorPane mainPane;
	try {
            mainPane = (AnchorPane)FXMLLoader.load(JarAppsManager.class.getResource(str));
            primaryStage.setScene(new Scene(mainPane));
            primaryStage.setTitle("JarApps Manager");
            primaryStage.setResizable(false);
            primaryStage.show();
	} catch (IOException e) {
		e.printStackTrace();
	}	
    }
    
}
