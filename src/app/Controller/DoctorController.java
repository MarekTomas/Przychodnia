package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;

import app.Database.DBConnector;
import app.Model.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private Button bt_wyloguj;
	
	//public  String send = String.valueOf(CheckController.message);
	public static String pas = String.valueOf(LoginController.pass);
	//HashMap<Integer, String> zbiorWiadomosci = new HashMap<>();
	
	@FXML
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
		db = new DBConnector();
		Connection conn = db.Connection();
		ResultSet rs = conn.createStatement().executeQuery(
				"select a.imie, a.nazwisko, b.day_v, b.time_v, b.id_d from users a join zarezerwowane b on a.pesel = b.pesel where a.pesel in (select pesel from zarezerwowane where id_d in (select id_d from doctors where pesel ="
						+ "'" + pas + "'));");
		list = FXCollections.observableArrayList();
		while (rs.next()) {
			list.add(new Doctor(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getTime(4), rs.getInt(5)));

			tc_imie.setCellValueFactory(new PropertyValueFactory<Doctor, String>("imie"));
			tc_nazwisko.setCellValueFactory(new PropertyValueFactory<Doctor, String>("nazwisko"));
			tc_data.setCellValueFactory(new PropertyValueFactory<Doctor, Date>("data"));
			tc_godzina.setCellValueFactory(new PropertyValueFactory<Doctor, Time>("godz"));
			tc_id_d.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("id_d"));
			tv_doctor.setItems(null);
			tv_doctor.setItems(list);
		}
		
		//lb_wiadomosc.setText(zbiorWiadomosci);

	}
}