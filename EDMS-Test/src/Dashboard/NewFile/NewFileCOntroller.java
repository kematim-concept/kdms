package Dashboard.NewFile;

import Dashboard.Dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;

public class NewFileCOntroller {


    String filename;
    String createdby;

    @FXML
    private TextField filenametxt;

    @FXML
    private TextField createdbytxt;

    @FXML
    private Button submit;

    public String getFilename() {
        return filename;
    }

    public String getCreatedby() {
        return createdby;
    }



    public void CreateFile(ActionEvent evt){
        filename = filenametxt.getText();
        createdby = createdbytxt.getText();

       new File("../EDMS-Test/Files/"+filename).mkdir();


    }
}


