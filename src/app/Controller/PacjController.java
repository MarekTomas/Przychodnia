package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import app.Database.DBConnector;
import app.Model.Rezervation;
import app.Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PacjController {
	public DBConnector db;

	@FXML
	private ChoiceBox<?> cb_lekarz;

	@FXML
	private TableView<Users> tv_tabela;

	@FXML
	private TableColumn<Users, String> tc_imie;
	

    @FXML
    private TableColumn<Users, Integer> tc_id_v;

	@FXML
	private TableColumn<Users, String> tc_nazwisko;

	@FXML
	private TableColumn<Users, String> tz_specjalizacja;

	@FXML
	private TableColumn<Users, Date> tc_data;

	@FXML
	private TableColumn<Users, Time> tc_godzina;

	@FXML
	private Button bt_rezerwuj;

	@FXML
	private Button bt_pokaz;

	@FXML
	private Button bt_odwolaj;

	@FXML
	private Button bt_zarezWyswietl;

	@FXML
	private TableView<Rezervation> tv_zarezWizyty;
	
    @FXML
    private TableColumn<Rezervation, Integer> tc_zarezid_v;

	@FXML
	private TableColumn<Rezervation, String> tc_zarezImie;

	@FXML
	private TableColumn<Rezervation,String> tc_zarezNazwisko;

	@FXML
	private TableColumn<Rezervation, String> tc_zarezSpecjalizacja;

	@FXML
	private TableColumn<Rezervation, Date> tc_zarezData;

	@FXML
	private TableColumn<Rezervation, Time> tc_zarezGodz;

	@FXML
	void CheckBoxWybierz(MouseEvent event) {

	}
	public static String pas = String.valueOf(LoginController.pass); 
	public ObservableList<Users> visits;
	public ObservableList<Rezervation> rezer;

	@FXML
	//wy�wietlanie wszystkich dost�pnych wizyt
	void buttonClickPokaz(MouseEvent event) throws SQLException {

		visits = FXCollections.observableArrayList();

		Connection conn = db.Connection();
		ResultSet rs = conn.createStatement().executeQuery(
				"select id_v,imie, nazwisko, specjalizacja, day_v, time_v,id_d from doctors natural join visits where id_d = id_d;");
		while (rs.next()) {
			visits.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
					rs.getTime(6)));
		}
		tc_id_v.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id_v"));
		tc_imie.setCellValueFactory(new PropertyValueFactory<Users, String>("imie"));
		tc_nazwisko.setCellValueFactory(new PropertyValueFactory<Users, String>("nazwisko"));
		tz_specjalizacja.setCellValueFactory(new PropertyValueFactory<Users, String>("specjalizacja"));
		tc_data.setCellValueFactory(new PropertyValueFactory<Users, Date>("day_v"));
		tc_godzina.setCellValueFactory(new PropertyValueFactory<Users, Time>("time_v"));
		tv_tabela.setItems(null);
		tv_tabela.setItems(visits);

	}

	@FXML
	//rezerwowanie wizyty
	void buttonClickRezerwuj(MouseEvent event) throws SQLException, IOException {
		
		Connection conn = db.Connection();
		int id_selected = tv_tabela.getSelectionModel().getSelectedItem().getId_v();
		String imie_selected = tv_tabela.getSelectionModel().getSelectedItem().getImie();
		String nazwisko_selected = tv_tabela.getSelectionModel().getSelectedItem().getNazwisko();
		String specjalizacja_selected = tv_tabela.getSelectionModel().getSelectedItem().getSpecjalizacja();
		Date data_selected = tv_tabela.getSelectionModel().getSelectedItem().getDay_v();
		Time czas_selected = tv_tabela.getSelectionModel().getSelectedItem().getTime_v();
		
		String rezer = "insert into zarezerwowane(id_v,imie, nazwisko, specjalizacja, day_v, time_v,pesel)values('"
				+ id_selected +"','"
				+ imie_selected + "','" + nazwisko_selected+ "','"
				+ specjalizacja_selected + "','" + data_selected + "','"
				+ czas_selected + "','"+ pas+ "');";
		PreparedStatement psrezer = conn.prepareStatement(rezer);
		System.out.println(rezer);
		psrezer.executeUpdate();
		buttonClickZarezWyswietl(event);

		
	//usuwanie zarezerwowanych wizyt z widoku wszystkich dost�pnych wizyt
		String usu = "delete from visits where id_v="+id_selected;
		PreparedStatement psusu = conn.prepareStatement(usu);
		psusu.executeUpdate();
		buttonClickPokaz(event);
		
	/*//Przechodzenie do widoku wysy�ania widomo�ci
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/CheckView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Witaj");	
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();*/
	}

	@FXML
	//Wy�wietlanie zarezerwowanych wizyt
	void buttonClickZarezWyswietl(MouseEvent event) throws SQLException {
		
		rezer = FXCollections.observableArrayList();
		
		Connection conn = db.Connection();
		ResultSet rezs = conn.createStatement().executeQuery(
				"select id_v,imie, nazwisko, specjalizacja, day_v, time_v, pesel from zarezerwowane where pesel ="+"'"+pas+"';");
		while (rezs.next()) {
			rezer.add(new Rezervation(rezs.getInt(1), rezs.getString(2), rezs.getString(3), rezs.getString(4), rezs.getDate(5),
					rezs.getTime(6), rezs.getString(7)));
			
	}
		tc_zarezid_v.setCellValueFactory(new PropertyValueFactory<Rezervation, Integer>("id_v"));
		tc_zarezImie.setCellValueFactory(new PropertyValueFactory<Rezervation, String>("imie"));
		tc_zarezNazwisko.setCellValueFactory(new PropertyValueFactory<Rezervation, String>("nazwisko"));
		tc_zarezSpecjalizacja.setCellValueFactory(new PropertyValueFactory<Rezervation, String>("specjalizacja"));
		tc_zarezData.setCellValueFactory(new PropertyValueFactory<Rezervation, Date>("day_v"));
		tc_zarezGodz.setCellValueFactory(new PropertyValueFactory<Rezervation, Time>("time_v"));
		tv_zarezWizyty.setItems(null);
		tv_zarezWizyty.setItems(rezer);
	}
	// odwo�anie rezerwacji
	@FXML
	void ButtonClickZarezOdwolaj(MouseEvent event) throws SQLException {
		
		Connection conn = db.Connection();
		// dodawanie do tabeli Visits zwolnione terminy
		int id_selected = tv_zarezWizyty.getSelectionModel().getSelectedItem().getId_v();
		Date data_selected = tv_tabela.getSelectionModel().getSelectedItem().getDay_v();
		Time czas_selected = tv_tabela.getSelectionModel().getSelectedItem().getTime_v();
		
		String zwrezer = "insert into visits(day_v,time_v,id_d)values('"
				+ id_selected +"','"+ "','" + data_selected + "','" + czas_selected + "');";
		
		PreparedStatement pszwrezer = conn.prepareStatement(zwrezer);
		System.out.println(pszwrezer);
		pszwrezer.executeUpdate();
		buttonClickZarezWyswietl(event);
		
		//usuwanie z tabeli wizyty zarezerwowane
		String usu = "delete from zarezerwowane where id_v="+id_selected;
		PreparedStatement psusu = conn.prepareStatement(usu);
		psusu.executeUpdate();
		buttonClickZarezWyswietl(event);
		System.out.println(id_selected);
		
	}

	public void initialize() throws SQLException {
		db = new DBConnector();
	

	}

}