package tn.esprit.spring.contollers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entities.Days;
import tn.esprit.spring.entities.Disponibility;
import tn.esprit.spring.entities.StateDispo;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IDisponibilityService;

@Scope(value = "session")
@ELBeanName(value = "DispController")
@Join(path = "/user/visite", to = "/pages/visite.jsf")
@Controller(value = "DispController")
public class DispController {
	@Autowired
	IDisponibilityService idisponibilityservice;
	
	
	
	private int idD;
	Days days;
	StateDispo statedispo;
	private java.sql.Time hourd;
	private java.sql.Time hourf;
	User userIdD;
	private int idAd;
	
	
	public IDisponibilityService getIdisponibilityservice() {
		return idisponibilityservice;
	}
	public void setIdisponibilityservice(IDisponibilityService idisponibilityservice) {
		this.idisponibilityservice = idisponibilityservice;
	}
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
	
	public int getIdAd() {
		return idAd;
	}
	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}




	private List<Disponibility> disp ;


	public List<Disponibility> getDisp() {
		disp =idisponibilityservice.getDspById();
		return disp;
	}
	public void setDisp(List<Disponibility> disp) {
		this.disp = disp;
	}
	
	private List<Disponibility> dispo ;


	public List<Disponibility> getDispo() {
		dispo = idisponibilityservice.getAllDspByUserId();
		return dispo;
	}
	public void setDispo(List<Disponibility> dispo) {
		this.dispo = dispo;
	}


	
	
	

}
