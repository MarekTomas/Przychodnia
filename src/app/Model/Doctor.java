package app.Model;

import java.sql.Date;
import java.sql.Time;

public class Doctor {
	private String imie;
	private String nazwisko;
	private Date data;
	private Time godz;
	private Integer id_d;
	
	public String getImie() {
		return imie;
	}
	
	public Integer getId_d() {
		return id_d;
	}


	public void setId_d(Integer id_d) {
		this.id_d = id_d;
	}


	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getGodz() {
		return godz;
	}
	public void setGodz(Time godz) {
		this.godz = godz;
	}
	
	

	public Doctor() {
		super();
	}

	public Doctor(String imie, String nazwisko, Date data, Time godz, Integer id_d) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data = data;
		this.godz = godz;
		this.id_d = id_d;
	}

	
	
	
	
}
