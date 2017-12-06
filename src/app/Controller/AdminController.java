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
import javafx.scene.control.TableColumn;
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
	private RadioButton rb_potwierdzL;

	@FXML
	private TableColumn<?, ?> tb_admin_idv;

	@FXML
	private TableColumn<?, ?> tb_admin_imie;

	@FXML
	private TableColumn<?, ?> tb_admin_nazwisko;

	@FXML
	private TableColumn<?, ?> tb_admin_specjal;

	@FXML
	private TableColumn<?, ?> tb_admin_dzien;

	@FXML
	private TableColumn<?, ?> tb_admin_godz;

	@FXML
	private TableColumn<?, ?> tb_admin_pesel;

	@FXML
	private TableColumn<?, ?> tb_admin_id;

	@FXML
	private Button bt_admin_pokaz;

	@FXML
	private RadioButton rb_potwierdz_wizyt;

	@FXML
	void ButtonDodajLekarza(MouseEvent event) throws SQLException {//dodawanie lekarza
		Connection conn = db.Connection();
		PreparedStatement ps = conn
				.prepareStatement("insert into doctors (imie, nazwisko,e_mail,pesel,uprawnienia,specjalizacja) values"
						+ "('" + tf_imie_le.getText() + "','" + tf_nazwisko_le.getText() + "','" + tf_email_le.getText()
						+ "','" + tf_pesel_le.getText() + "','" + tf_upraw_le.getText() + "','" + tf_specja_le.getText()
						+ "');");

		if (tf_pesel_le.getText().length() != 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B��d przy podwaniau has�a");
			PreparedStatement.setTitle("B��d");
			PreparedStatement.showAndWait();
		} else {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U�ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
		ps.executeUpdate();
		conn.close();
	}

	@FXML
	void ButtonDodajUztkownika(MouseEvent event) throws SQLException {//dodawanie uzytkownika
		Connection conn = db.Connection();
		PreparedStatement ps1 = conn
				.prepareStatement("insert into users (imie, nazwisko,e_mail,pesel,uprawnienia) values" + "('"
						+ tf_imie_uzytkownik.getText() + "','" + tf_nazwisko_uzytkownik.getText() + "','"
						+ tf_email_uzytkownik.getText() + "','" + tf_pesel_uzytkownik.getText() + "','"
						+ tf_upraw_uzytkownik.getText() + "');");
		if (tf_pesel_uzytkownik.getText().length() != 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B��d przy podwaniau has�a");
			PreparedStatement.setTitle("B��d");
			PreparedStatement.showAndWait();
		} else {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U�ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
		ps1.executeUpdate();
		conn.close();
	}

	@FXML
	void ButtonRezerwujWizyte(MouseEvent event) {

	}

	@FXML
	void ButtonUsunLekarza(MouseEvent event) throws SQLException {//usuwanie lekarza
		Connection conn = db.Connection();
		if (!rb_potwierdzL.isSelected()) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Prosz� potwierdzi� usuni�cie lekarza");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		} else {
			String sql = "delete from doctors where pesel = '" + tf_pesel_le.getText() + "'" + "and e_mail='"
					+ tf_email_le.getText() + "';";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Lekarz usuni�ty poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		}
	}

	@FXML
	void ButtonUsunUzytkownika(MouseEvent event) throws SQLException {//usuwanie uzytkownika
		Connection conn = db.Connection();
		if (!rb_potwierdz.isSelected()) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Prosz� potwierdzi� usuni�cie u�ytkownika");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		} else {
			String sql1 = "delete from users where pesel = '" + tf_pesel_uzytkownik.getText() + "'" + "and e_mail='"
					+ tf_email_uzytkownik.getText() + "';";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.executeUpdate();

			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U�ytkownik usuni�ty poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
	}

	@FXML
	void ButtonUsunWizyte(MouseEvent event) {

	}

	@FXML
	void buttonWyloguj(MouseEvent event) throws IOException {//wylogowywanie do okna logowanie
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