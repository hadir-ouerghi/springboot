package tn.esprit.spring.entities;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "disponibility")
public class Disponibility {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idD;
	
	@Enumerated(EnumType.STRING)
	
	Days days;
	
	@Enumerated(EnumType.STRING)
	StateDispo statedispo;
	
	@Column(name = "hourD")
	private java.sql.Time hourd;
	
	@Column(name = "hourF")
	private java.sql.Time hourf;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	User userIdD;

	public int getIdD() {
		return idD;
	}

	public void setIdD(int idD) {
		this.idD = idD;
	}

	public Days getDays() {
		return days;
	}

	public void setDays(Days days) {
		this.days = days;
	}

	public StateDispo getStatedispo() {
		return statedispo;
	}

	public void setStatedispo(StateDispo statedispo) {
		this.statedispo = statedispo;
	}

	public java.sql.Time getHourd() {
		return hourd;
	}

	public void setHourd(java.sql.Time hourd) {
		this.hourd = hourd;
	}

	public java.sql.Time getHourf() {
		return hourf;
	}

	public void setHourf(java.sql.Time hourf) {
		this.hourf = hourf;
	}

	public User getUserIdD() {
		return userIdD;
	}

	public void setUserIdD(User userIdD) {
		this.userIdD = userIdD;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Disponibility [idD=" + idD + ", days=" + days + ", statedispo=" + statedispo + ", hourd=" + hourd
				+ ", hourf=" + hourf + ", userIdD=" + userIdD + "]";
	}

	public Disponibility() {
		super();
		
	}

	public Disponibility(int idD, Days days, StateDispo statedispo, Time hourd, Time hourf, User userIdD) {
		super();
		this.idD = idD;
		this.days = days;
		this.statedispo = statedispo;
		this.hourd = hourd;
		this.hourf = hourf;
		this.userIdD = userIdD;
	}
	
	
	
	

}
