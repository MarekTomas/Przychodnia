package app.Model;

import java.sql.Date;
import java.sql.Time;

public class Admin {
	
	private Integer id_v;
	private String imie ;
	private String nazwisko;
	private String specjalizacja;
	private Date day_v;
	private Time time_v;
	private String pesel;
	private Integer tc_id_d;
	public Integer getId_v() {
		return id_v;
	}
	public void setId_v(Integer id_v) {
		this.id_v = id_v;
	}
	public String getImie() {
		return imie;
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
	public String getSpecjalizacja() {
		return specjalizacja;
	}
	public void setSpecjalizacja(String specjalizacja) {
		this.specjalizacja = specjalizacja;
	}
	public Date getDay_v() {
		return day_v;
	}
	public void setDay_v(Date day_v) {
		this.day_v = day_v;
	}
	public Time getTime_v() {
		return time_v;
	}
	public void setTime_v(Time time_v) {
		this.time_v = time_v;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public Integer getTc_id_d() {
		return tc_id_d;
	}
	public void setTc_id_d(Integer tc_id_d) {
		this.tc_id_d = tc_id_d;
	}
	public Admin(Integer id_v, String imie, String nazwisko, String specjalizacja, Date day_v, Time time_v,
			String pesel, Integer tc_id_d) {
		super();
		this.id_v = id_v;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.specjalizacja = specjalizacja;
		this.day_v = day_v;
		this.time_v = time_v;
		this.pesel = pesel;
		this.tc_id_d = tc_id_d;
	}
	public Admin() {
		super();
	}

	
}
