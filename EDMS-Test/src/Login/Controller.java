package Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {


    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginbtn;


    @FXML
    private void login(ActionEvent event) {
        String usernametext;
        String passwordtext;


        //This block pulls data from the textfield and passwordfield to be used for login
        usernametext = username.getText();
        passwordtext = password.getText();
        try {
            //This is the sql statement for the login from database
            String sql = "Select * from LoginInfo where Username = ? and Password = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usernametext);
            pst.setString(2, passwordtext);
            rs = pst.executeQuery();


            if (rs.next()) {

                if (rs.getString("AccountType").equals("Assistant")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Succesful");
                    Stage stage = new Stage();
                    ((Node)(event.getSource())).getScene().getWindow().hide();



                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("/Dashboard/AssistantDash/AssistantDashboard.fxml"));
                    Loader.load();
                    Parent root;
                    root = Loader.getRoot();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                    alert.showAndWait();

                } else if (rs.getString("AccountType").equals("Admin")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Succesful");
                    Stage stage = new Stage();
                    ((Node)(event.getSource())).getScene().getWindow().hide();

                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("/Dashboard/Dashboard.fxml"));
                    Loader.load();
                    Parent root;
                    root = Loader.getRoot();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
                    alert.showAndWait();
                }


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login information Incorrect");
                alert.showAndWait();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {

        con = DataCenter.DbConnector();
    }

}
