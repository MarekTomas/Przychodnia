package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
	
    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_login;

    @FXML
    private Button bt_logowanie;

    @FXML
    private Button bt_rejestracja;
 
    DBConnector db;
    public static String a;
    public static String pass;

	@FXML
    void buttonLogowanie(MouseEvent event) throws SQLException, IOException {
		Connection conn1 =  db.Connection();
    	Statement stmt = conn1.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT uprawnienia FROM users WHERE e_mail='"+tf_login.getText()+"' and pesel='"+pf_password.getText()+"';");
    	pass = pf_password.getText();
    	if(rs.next()) {
    		a = rs.getString("uprawnienia");
    		if(a.equals("user")) {
		    	Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PacjView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Witaj");	
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
	    		}
	    	else if(a.equals("lekarz")) {
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LekarzView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Witaj doktorze");	
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			}
			else if(a.equals("admin")) {
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/AdminView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Admin");	
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			}
		
    	}
    	else {
			Alert statement = new Alert(AlertType.INFORMATION);
			statement.setHeaderText("B³¹d");
			statement.setContentText("B³êdne has³o lub login");
			statement.setTitle("Nale¿y podaæ poprawne dane logowania");
			statement.showAndWait();
			}
	}
	
    @FXML
    void buttonRejestracja(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/RegistrationView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Rejestracja");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

    }
    public void initialize() {
    	db = new DBConnector();
  
}
}
