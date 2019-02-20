package Dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dashboard {

    ObservableList Docs = FXCollections.observableArrayList();
    String fileextension, filename, filesize;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateform = new SimpleDateFormat("dd/MM/YY");
    String dateofuploads = dateform.format(date);
    @FXML
    private MenuItem addfile;
    @FXML
    private TableView<Document> documents;
    @FXML
    private TableColumn<Document, String> documentname;
    @FXML
    private TableColumn<Document, String> documentsize;
    @FXML
    private TableColumn<Document, String> documentextension;
    @FXML
    private TableColumn<Document, String> dateofupload;
    @FXML
    private Button Uploaddoc;
    @FXML
    private ImageView thumbnail;
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button logout;
    @FXML
    private Text name;
    @FXML
    private Text extension;
    @FXML
    private Text size;
    @FXML
    private TreeView<String> files;

    @FXML
    private Button update;

    @FXML
    private Button newAct;

    public static void createTree(File file, TreeItem<String> parent) {

        if (file.isDirectory()) {
            TreeItem<String> treeitem = new TreeItem<>(file.getName());
            parent.getChildren().add(treeitem);
            for (File f : file.listFiles()) {
                createTree(f, treeitem);
            }
        }

    }

    @FXML
    private void newfile(ActionEvent evt) throws IOException {
        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Dashboard/NewFile/NewFile.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void logoutact(ActionEvent evt) throws IOException {


        Stage stage = new Stage();
        ((Node) evt.getSource()).getScene().getWindow().hide();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Login/Login.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void uploadfile(ActionEvent event) throws IOException {


        Stage stage = new Stage();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Files");
        File file = filechooser.showOpenDialog(stage);
        if (file != null) {

            filename = file.getName();
            fileextension = filename.substring(filename.lastIndexOf("."));
            String filepath = String.valueOf(file.toURI());
            long filez = file.length() / 1000;
            filesize = "" + filez + "kb";

            Image image = new Image(filepath, 100, 100, false, false);
            thumbnail.setImage(image);

            addfileproperties(filename, fileextension, filesize);
            File source = new File(file.getAbsolutePath());
            File dest = new File("../EDMS-Test/Files/" + getFileDirectory() + "/" + file.getName());

            FileInputStream sourcefile = new FileInputStream(source);
            FileOutputStream destfile = new FileOutputStream(dest);

            int buffersize;
            byte[] buffer = new byte[512];
            while ((buffersize = sourcefile.read(buffer)) > 0) {
                destfile.write(buffer, 0, buffersize);
            }
            sourcefile.close();
            destfile.close();

            Docs.add(new Document(filename, filesize, fileextension, dateofuploads));

            documentname.setCellValueFactory(new PropertyValueFactory<>("documentname"));
            documentsize.setCellValueFactory(new PropertyValueFactory<>("documentSize"));
            documentextension.setCellValueFactory(new PropertyValueFactory<>("documentExtension"));
            dateofupload.setCellValueFactory(new PropertyValueFactory<>("dateofupload"));
            documents.setItems(Docs);

        }
    }

    @FXML
    private void tableClick(MouseEvent event) throws Exception {
        if (event.getClickCount() == 1) {
            name.setText(documents.getSelectionModel().getSelectedItem().getDocumentname());
            size.setText(documents.getSelectionModel().getSelectedItem().getDocumentSize());
            extension.setText(documents.getSelectionModel().getSelectedItem().getDocumentExtension());

        }
    }

    private void addfileproperties(String filename, String fileextension, String filesize) {

        name.setText(filename);
        extension.setText(fileextension);
        size.setText(filesize);

    }

    @FXML
    private void viewfile() {

        File dir = new File("../EDMS-Test/Files/" + getFileDirectory() + "/");
        Docs.clear();
        for (File file : dir.listFiles()) {

            String filesname = file.getName();
            Long filessize = file.length() / 1000;


            String filesextension = filesname.substring(filesname.lastIndexOf("."));
            String size = filessize + "kb";

            Docs.add(new Document(filesname, size, filesextension, dateofuploads));

            documentname.setCellValueFactory(new PropertyValueFactory<>("documentname"));
            documentsize.setCellValueFactory(new PropertyValueFactory<>("documentSize"));
            documentextension.setCellValueFactory(new PropertyValueFactory<>("documentExtension"));
            dateofupload.setCellValueFactory(new PropertyValueFactory<>("dateofupload"));
            documents.setItems(Docs);
        }


    }

    public void displaytree(String edmsdirectory) {

        TreeItem<String> rootItem = new TreeItem<String>(edmsdirectory);


        File edmsdirectorylocation = new File(edmsdirectory);
        File filelist[] = edmsdirectorylocation.listFiles();

        for (File file : filelist) {
            createTree(file, rootItem);
        }

        files.setRoot(rootItem);
    }

    @FXML
    private void updatefiles() {


        displaytree("../EDMS-Test/Files");

    }

    @FXML
    private void newAccount(ActionEvent evt) throws IOException {

        Stage stage = new Stage();

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/Dashboard/NewAccount.fxml"));
        Loader.load();
        Parent root;
        root = Loader.getRoot();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public String getFileDirectory() {
        String filedirectory = files.getSelectionModel().getSelectedItem().toString();

        return filedirectory.substring(18, filedirectory.indexOf(" ]"));

    }


    //~*The method below is used to open a file*~
    /*  private void OpenFile(File file) {

      try {

        desktop.open(file);

   } catch (Exception e) {
     e.printStackTrace();
    }
       }
 */
    public void initialize() throws Exception {
        displaytree("../EDMS-Test/Files");

    }
}
