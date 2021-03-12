package tn.esprit.spring.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idapt;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfApt = new Date();
	
	@Column(name = "hour")
	private java.sql.Time hour;
	
	@Column(name = "location")
	private String location;
	
	@NotNull(message="First name is compulsory")
	@Column(name = "client_first_name")
	private String clientfirstName;
	
	@NotNull(message="Last name is compulsory")
	@Column(name = "client_last_name")
	private String clientlastName;
	
	@Pattern(regexp="(^$|[0-9]{8})",message="Mobile number must be 8 digits")
	private String phonenumber;
	
	@Email(message = "Email is invalid")
	@Column(name = "mail", nullable=false, unique= false)
	private String mail;
	
	@Enumerated(EnumType.STRING)
	StateApt stateapt;
	
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	User userId;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	Ads adId;
	

	
	

	

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	

	public Ads getAdId() {
		return adId;
	}

	public void setAdId(Ads adId) {
		this.adId = adId;
	}

	public int getIdapt() {
		return idapt;
	}

	public void setIdapt(int idapt) {
		this.idapt = idapt;
	}

	public Date getDateOfApt() {
		return dateOfApt;
	}

	public void setDateOfApt(Date dateOfApt) {
		this.dateOfApt = dateOfApt;
	}

	public java.sql.Time getHour() {
		return hour;
	}

	public void setHour(java.sql.Time hour) {
		this.hour = hour;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClientfirstName() {
		return clientfirstName;
	}

	public void setClientfirstName(String clientfirstName) {
		this.clientfirstName = clientfirstName;
	}

	public String getClientlastName() {
		return clientlastName;
	}

	public void setClientlastName(String clientlastName) {
		this.clientlastName = clientlastName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public StateApt getStateapt() {
		return stateapt;
	}

	public void setStateapt(StateApt stateapt) {
		this.stateapt = stateapt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	

	public Appointment() {
		super();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientfirstName == null) ? 0 : clientfirstName.hashCode());
		result = prime * result + ((clientlastName == null) ? 0 : clientlastName.hashCode());
		result = prime * result + ((dateOfApt == null) ? 0 : dateOfApt.hashCode());
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + idapt;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((phonenumber == null) ? 0 : phonenumber.hashCode());
		result = prime * result + ((stateapt == null) ? 0 : stateapt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (clientfirstName == null) {
			if (other.clientfirstName != null)
				return false;
		} else if (!clientfirstName.equals(other.clientfirstName))
			return false;
		if (clientlastName == null) {
			if (other.clientlastName != null)
				return false;
		} else if (!clientlastName.equals(other.clientlastName))
			return false;
		if (dateOfApt == null) {
			if (other.dateOfApt != null)
				return false;
		} else if (!dateOfApt.equals(other.dateOfApt))
			return false;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (idapt != other.idapt)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (phonenumber == null) {
			if (other.phonenumber != null)
				return false;
		} else if (!phonenumber.equals(other.phonenumber))
			return false;
		if (stateapt != other.stateapt)
			return false;
		return true;
	}


	
	
	

	
}
