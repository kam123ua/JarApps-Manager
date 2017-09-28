package jarapps.manager;

import static jarapps.manager.JarAppsManager.showWindows;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kam123ua
 */
public class AddController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search(ActionEvent event) {
        String path = pathFile();
        txtSearch.setText(path);
    }

    @FXML
    private void add(ActionEvent event) {
        String name = txtName.getText();
        String path = txtSearch.getText();
        if(name.length() > 0 && path.length() > 0) {
            FileController.update("name.txt", name + "\n");
            FileController.update("path.txt", path + "\n");
            showWindows("FXMLDocument.fxml");
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        }
    }
    
    public static String pathFile() {
        Stage primaryStage = null;
        String res = "";
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(primaryStage);
        res = file.getAbsolutePath();
        if(res.length() > 0) return  res;
        else return pathFile();
    }
    
}
