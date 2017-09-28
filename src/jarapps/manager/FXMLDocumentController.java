package jarapps.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author kam123ua
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<String> textApps;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String buf = "";
        try {
            buf = FileController.read("name.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] applications = buf.split("\n");
        ObservableList<String> items = FXCollections.observableArrayList (applications);
        textApps.setItems(items);
    }    

    @FXML
    private void run(ActionEvent event) {
        String name = textApps.getSelectionModel().getSelectedItem();
        String buf1 = "";
        String buf2 = "";
        try {
            buf1 = FileController.read("path.txt");
            buf2 = FileController.read("name.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] path = buf1.split("\n");
        String[] applications = buf2.split("\n");
        String comand = "";
        for(int i = 0; i < applications.length; i++) {
            if(applications[i].equals(name)) {
                comand = path[i];
                break;
            }
        }
        try {
            Process p = Runtime.getRuntime().exec("java -jar " + comand);
 
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void add(ActionEvent event) {
        JarAppsManager.showWindows("Add.fxml");
        Stage stage = (Stage) textApps.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void delete(ActionEvent event) {
        String application = textApps.getSelectionModel().getSelectedItem();
        String buf1 = "";
        String buf2 = "";
        try {
            buf1 = FileController.read("path.txt");
            buf2 = FileController.read("name.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] path = buf1.split("\n");
        String[] name = buf2.split("\n");
        String nameText = "";
        String pathText = "";
        for(int i = 0; i < name.length; i++) {
            if(!name[i].equals(application)) {
                nameText += (name[i] + "\n");
                pathText += (path[i] + "\n");
            }
        }
        name = nameText.split("\n");
        path = pathText.split("\n");
        FileController.write("name.txt", nameText);
        FileController.write("path.txt", pathText);
        ObservableList<String> items = FXCollections.observableArrayList (name);
        textApps.setItems(items);
        
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }
    
}
