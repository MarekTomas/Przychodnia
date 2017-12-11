package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import app.Database.DBConnector;
import app.Model.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableView<Admin> tv_admin_rezerwation;

	@FXML
	private TableColumn<Admin, Integer> tb_admin_idv;

	@FXML
	private TableColumn<Admin, String> tb_admin_imie;

	@FXML
	private TableColumn<Admin, String> tb_admin_nazwisko;

	@FXML
	private TableColumn<Admin, String> tb_admin_specjal;

	@FXML
	private TableColumn<Admin, Date> tb_admin_dzien;

	@FXML
	private TableColumn<Admin, Time> tb_admin_godz;

	@FXML
	private TableColumn<Admin, String> tb_admin_pesel;

	@FXML
	private TableColumn<Admin, Integer> tb_admin_id;

	@FXML
	private RadioButton rb_potwierdz_wizyt;

	public ObservableList<Admin> zarez;

	@FXML
	// dodawanie lekarza
	void ButtonDodajLekarza(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		PreparedStatement ps = conn
				.prepareStatement("insert into doctors (imie, nazwisko,e_mail,pesel,uprawnienia,specjalizacja) values"
						+ "('" + tf_imie_le.getText() + "','" + tf_nazwisko_le.getText() + "','" + tf_email_le.getText()
						+ "','" + tf_pesel_le.getText() + "','" + tf_upraw_le.getText() + "','" + tf_specja_le.getText()
						+ "');");

		if (tf_pesel_le.getText().length() != 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B³¹d przy podwaniau has³a");
			PreparedStatement.setTitle("B³¹d");
			PreparedStatement.showAndWait();
		} else {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
		ps.executeUpdate();
		conn.close();
	}

	@FXML
	// dodawanie uzytkownika
	void ButtonDodajUztkownika(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		PreparedStatement ps1 = conn
				.prepareStatement("insert into users (imie, nazwisko,e_mail,pesel,uprawnienia) values" + "('"
						+ tf_imie_uzytkownik.getText() + "','" + tf_nazwisko_uzytkownik.getText() + "','"
						+ tf_email_uzytkownik.getText() + "','" + tf_pesel_uzytkownik.getText() + "','"
						+ tf_upraw_uzytkownik.getText() + "');");
		if (tf_pesel_uzytkownik.getText().length() != 11) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Popraw pesel");
			PreparedStatement.setContentText("B³¹d przy podwaniau has³a");
			PreparedStatement.setTitle("B³¹d");
			PreparedStatement.showAndWait();
		} else {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik zarejestrowany poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
		ps1.executeUpdate();
		conn.close();
	}

	@FXML
	// usuwanie lekarza
	void ButtonUsunLekarza(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		if (!rb_potwierdzL.isSelected()) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Proszê potwierdziæ usuniêcie lekarza");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		} else {
			String sql = "delete from doctors where pesel = '" + tf_pesel_le.getText() + "'" + "and e_mail='"
					+ tf_email_le.getText() + "';";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();

			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Lekarz usuniêty poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		}
	}

	@FXML
	// usuwanie uzytkownika
	void ButtonUsunUzytkownika(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		if (!rb_potwierdz.isSelected()) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Proszê potwierdziæ usuniêcie u¿ytkownika");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();

		} else {
			String sql1 = "delete from users where pesel = '" + tf_pesel_uzytkownik.getText() + "'" + "and e_mail='"
					+ tf_email_uzytkownik.getText() + "';";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.executeUpdate();

			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("U¿ytkownik usuniêty poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
	}

	@FXML
	// usuwanie wizyty
	void ButtonUsunWizyte(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		if (!rb_potwierdz_wizyt.isSelected()) {
			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Proszê potwierdziæ usuniêcie u¿ytkownika");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		} else {
			// dodawanie do tabeli Visits zwolnione terminy
			int id_selected = tv_admin_rezerwation.getSelectionModel().getSelectedItem().getId_v();
			Date date_selected = tv_admin_rezerwation.getSelectionModel().getSelectedItem().getDay_v();
			Time time_selected = tv_admin_rezerwation.getSelectionModel().getSelectedItem().getTime_v();
			Integer id_d_selected = tv_admin_rezerwation.getSelectionModel().getSelectedItem().getTc_id_d();

			String zwrezer = "insert into visits(id_v,day_v,time_v,id_d)values('" + id_selected + "','" + date_selected
					+ "','" + time_selected + "','" + id_d_selected + "');";

			PreparedStatement pszwrezer = conn.prepareStatement(zwrezer);
			pszwrezer.executeUpdate();
			initialize();

			// usuwanie z tabeli wizyty zarezerwowane zwolnione terminy
			String usu = "delete from zarezerwowane where id_v=" + id_selected;
			PreparedStatement psusu = conn.prepareStatement(usu);
			psusu.executeUpdate();
			initialize();

			Alert PreparedStatement = new Alert(AlertType.INFORMATION);
			PreparedStatement.setHeaderText("Infromacja");
			PreparedStatement.setContentText("Wizyta usniêta poprawnie");
			PreparedStatement.setTitle("");
			PreparedStatement.showAndWait();
		}
	}

	@FXML
	// wylogowywanie do okna logowanie
	void buttonWyloguj(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	public void initialize() throws SQLException {
		db = new DBConnector();

		zarez = FXCollections.observableArrayList();
		// pokazanie zarezerwowanych wizyt
		Connection conn = db.Connection();
		ResultSet rs = conn.createStatement().executeQuery("select * from zarezerwowane ;");
		while (rs.next()) {
			zarez.add(new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getTime(6), rs.getString(7), rs.getInt(8)));

			tb_admin_idv.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("id_v"));
			tb_admin_imie.setCellValueFactory(new PropertyValueFactory<Admin, String>("imie"));
			tb_admin_nazwisko.setCellValueFactory(new PropertyValueFactory<Admin, String>("nazwisko"));
			tb_admin_specjal.setCellValueFactory(new PropertyValueFactory<Admin, String>("specjalizacja"));
			tb_admin_dzien.setCellValueFactory(new PropertyValueFactory<Admin, Date>("day_v"));
			tb_admin_godz.setCellValueFactory(new PropertyValueFactory<Admin, Time>("time_v"));
			tb_admin_pesel.setCellValueFactory(new PropertyValueFactory<Admin, String>("pesel"));
			tb_admin_id.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("tc_id_d"));
			tv_admin_rezerwation.setItems(null);
			tv_admin_rezerwation.setItems(zarez);
		}
	}
}