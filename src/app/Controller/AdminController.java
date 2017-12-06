package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminController {
	
	public DBConnector db;

    @FXML
    private Button bt_admin_dodajUz;

    @FXML
    private Button bt_admin_usunUz;

    @FXML
    private Button bt_admin_dodajLe;

    @FXML
    private Button bt_admin_usunLe;

    @FXML
    private Button bt_admin_rezWi;

    @FXML
    private Button bt_admin_usunWi;

    @FXML
    private Button bt_admin_wylog;

    @FXML
    private TextField tf_imie_uzytkownik;

    @FXML
    private TextField tf_nazwisko_uzytkownik;

    @FXML
    private TextField tf_email_uzytkownik;

    @FXML
    private TextField tf_pesel_uzytkownik;

    @FXML
    private TextField tf_upraw_uzytkownik;

    @FXML
    private TextField tf_imie_le;

    @FXML
    private TextField tf_nazwisko_le;

    @FXML
    private TextField tf_pesel_le;

    @FXML
    private TextField tf_email_le;

    @FXML
    private TextField tf_upraw_le;

    @FXML
    private TextField tf_specja_le;
    
    @FXML
    private RadioButton rb_potwierdz;

    @FXML
    void ButtonDodajLekarza(MouseEvent event) {

    }

    @FXML
    void ButtonDodajUztkownika(MouseEvent event) throws SQLException {
    	Connection conn = db.Connection();
		PreparedStatement ps = conn.prepareStatement("insert into users (imie, nazwisko,e_mail,pesel,uprawnienia) values" + "('"
				+ tf_imie_uzytkownik.getText() + "','" + tf_nazwisko_uzytkownik.getText() + "','" + tf_email_uzytkownik.getText() + "','"
				+ tf_pesel_uzytkownik.getText() +"','"+tf_upraw_uzytkownik+ "')");
		
		if (tf_pesel_uzytkownik.getText().length() < 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);// komunikat przy rejestracji jezeli pesel != powtórz pesel													
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B³¹d przy podwaniau has³a");
			PreparedStatement.setTitle("B³¹d");
			PreparedStatement.showAndWait();
		} else {
			ps.executeUpdate();
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);// komunikat po rejestracji rejestracja przebieg³a poprawnie											
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
		conn.close();

    }

    @FXML
    void ButtonRezerwujWizyte(MouseEvent event) {

    }

    @FXML
    void ButtonUsunLekarza(MouseEvent event) {

    }

    @FXML
    void ButtonUsunUzytkownika(MouseEvent event) throws SQLException {
    	Connection conn = db.Connection();
    	if(rb_potwierdz.isSelected()) {
		String sql = ("delete from users where (imie, nazwisko,pesel) values" + "('"
				+ tf_imie_uzytkownik.getText() + "','" + tf_nazwisko_uzytkownik.getText()+ "','"
				+ tf_pesel_uzytkownik.getText()+ "')");
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
    	}else {
    		Alert PreparedStatement = new Alert(AlertType.INFORMATION);
    				PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
    	}

    }

    @FXML
    void ButtonUsunWizyte(MouseEvent event) {

    }

    @FXML
    void buttonWyloguj(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

    }
    
    public void initialize() {
		db = new DBConnector();
	}


}