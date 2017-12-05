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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistrationController {
	public DBConnector db;

	@FXML
	private TextField tf_re_name;

	@FXML
	private TextField tf_re_surname;

	@FXML
	private TextField tf_re_email;

	@FXML
	private PasswordField pf_re_repeat_password;

	@FXML
	private PasswordField pf_re_password;

	@FXML
	private Button bt_re_rejestracja;

	@FXML
	private Button bt_re_powrot;

	@FXML
	void buttonPowrot(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	@FXML
	void buttonReRejestracja(MouseEvent event) throws SQLException, IOException {

		Connection conn = db.Connection();
		PreparedStatement ps = conn.prepareStatement("insert into users (imie, nazwisko,e_mail,pesel) values" + "('"
				+ tf_re_name.getText() + "','" + tf_re_surname.getText() + "','" + tf_re_email.getText() + "','"
				+ pf_re_password.getText() + "')");
		
		if (!pf_re_password.getText().equals(pf_re_repeat_password.getText())
				|| pf_re_password.getText().length() < 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);// komunikat przy rejestracji jezeli pesel != powtórz pesel													
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B³¹d przy podwaniau has³a");
			PreparedStatement.setTitle("B³¹d");
			PreparedStatement.showAndWait();
		} else if (pf_re_password.getText().equals(pf_re_repeat_password.getText())) {
			ps.executeUpdate();
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);// komunikat po rejestracji rejestracja przebieg³a poprawnie											
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
			buttonPowrot(event);// wywo³anie metody odpowiedzialnej za powrót do widoku logowanie
		}
		conn.close();
	}

	public void initialize() {
		db = new DBConnector();
	}
}
