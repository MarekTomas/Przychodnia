package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import app.Database.DBConnector;
import app.Model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DoctorController {
	public DBConnector db;
	public ObservableList<Doctor> list;
	@FXML
	private TableView<Doctor> tv_doctor;

	@FXML
	private Label lb_wiadomosc;

	@FXML
	private Button bt_usun;

	@FXML
	private Button bt_pokaz;

	@FXML
	private TableColumn<Doctor, String> tc_imie;

	@FXML
	private TableColumn<Doctor, String> tc_nazwisko;

	@FXML
	private TableColumn<Doctor, Date> tc_data;

	@FXML
	private TableColumn<Doctor, Time> tc_godzina;

	@FXML
	private TableColumn<Doctor, Integer> tc_id_d;

	@FXML
	private TableColumn<Doctor, String> tc_info;

	@FXML
	private Button bt_wyloguj;

	public static String pas = String.valueOf(LoginController.pass);

	@FXML // przechodzenie do widoku logowania po nicisnieciu przycisku
	void ButtonWyloguj(MouseEvent event) throws IOException {

		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	public void initialize() throws SQLException {
		// wyœwietlanie wizyt lekarza
		db = new DBConnector();
		Connection conn = db.Connection();
		ResultSet rs = conn.createStatement().executeQuery(
				"select a.imie, a.nazwisko, b.day_v, b.time_v, b.id_d, b.info from users a join zarezerwowane b on a.pesel = b.pesel where a.pesel in (select pesel from zarezerwowane where id_d in (select id_d from doctors where pesel ="
						+ "'" + pas + "'));");
		list = FXCollections.observableArrayList();
		while (rs.next()) {
			list.add(new Doctor(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getTime(4), rs.getInt(5),
					rs.getString(6)));

			tc_imie.setCellValueFactory(new PropertyValueFactory<Doctor, String>("imie"));
			tc_nazwisko.setCellValueFactory(new PropertyValueFactory<Doctor, String>("nazwisko"));
			tc_data.setCellValueFactory(new PropertyValueFactory<Doctor, Date>("data"));
			tc_godzina.setCellValueFactory(new PropertyValueFactory<Doctor, Time>("godz"));
			tc_id_d.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("id_d"));
			tc_info.setCellValueFactory(new PropertyValueFactory<Doctor, String>("info"));
			tv_doctor.setItems(null);
			tv_doctor.setItems(list);

		}

	}

	@FXML
	void buttonPokaz(MouseEvent event) throws SQLException {
		// pokazywanie widomosci dla lekarza po wybraniu pracjenta
		Date day_selected = tv_doctor.getSelectionModel().getSelectedItem().getData();
		Time time_selected = tv_doctor.getSelectionModel().getSelectedItem().getGodz();
		Integer id_d_selected = tv_doctor.getSelectionModel().getSelectedItem().getId_d();

		Connection conn = db.Connection();
		ResultSet rspokaz = conn.createStatement()
				.executeQuery("select info from zarezerwowane where day_v='" + day_selected.toString()
						+ "' and time_v='" + time_selected.toString() + "' and id_d =" + id_d_selected + ";");
		rspokaz.next();
		lb_wiadomosc.setText(rspokaz.getString(1).toString());
	}

	@FXML
	void buttonUsun(MouseEvent event) throws SQLException {
		Connection conn = db.Connection();
		Date date_selected = tv_doctor.getSelectionModel().getSelectedItem().getData();
		Time time_selected = tv_doctor.getSelectionModel().getSelectedItem().getGodz();

		// usuwanie z tabeli doctor_wizyty zarezerwowane terminy
		String usu = "delete from zarezerwowane where day_v ='" + date_selected + "' and time_v='" + time_selected
				+ "';";
		PreparedStatement psusu = conn.prepareStatement(usu);
		psusu.executeUpdate();
		initialize();

		Alert PreparedStatement = new Alert(AlertType.INFORMATION);
		PreparedStatement.setHeaderText("Infromacja");
		PreparedStatement.setContentText("Wizyta odwo³ana");
		PreparedStatement.setTitle("");
		PreparedStatement.showAndWait();

	}

}
