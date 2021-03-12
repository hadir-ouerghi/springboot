package tn.esprit.spring.contollers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Disponibility;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.AdsRepository;
import tn.esprit.spring.repositories.AppointmentRepository;
import tn.esprit.spring.repositories.DisponibilityRepository;
import tn.esprit.spring.services.IAppointmentService;
import tn.esprit.spring.services.IDisponibilityService;


@Scope(value = "session")
@ELBeanName(value = "AptController")
@Join(path = "/user/apt", to = "/pages/MyAppointments.jsf")
@Controller(value = "AptController")
public class AptController {
	@Autowired
	private JavaMailSender javaMailSender;
	
	SimpleMailMessage msg = new SimpleMailMessage();
	
	StateApt state1 = StateApt.waiting ;
	StateApt state2 = StateApt.confirmed ;
	StateApt state3 = StateApt.demande ;
	
	@Autowired
	IAppointmentService iappointmentservice;

	@Autowired
	IDisponibilityService idisponibilityservice;

	@Autowired
	DisponibilityRepository disponibilityRepository;

	@Autowired
	AdsRepository adsRepository;

	@Autowired
	AppointmentRepository appointmentRepository;
	
	StateApt s = StateApt.confirmed;
	StateApt s1 = StateApt.waiting;
	
	private int idapt;
	private Date dateOfApt = new Date();
	private java.sql.Time hour;
	private String location;
	private String clientfirstName;
	private String clientlastName;
	private String phonenumber;
	private String mail;
	StateApt stateapt;
	User userId;
	Ads adId;
	
	LocalDateTime localDate = LocalDateTime.now();
	Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
	
	
	
	
	public LocalDateTime getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDateTime localDate) {
		this.localDate = localDate;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public StateApt getS() {
		return s;
	}
	public void setS(StateApt s) {
		this.s = s;
	}
	public StateApt getS1() {
		return s1;
	}
	public void setS1(StateApt s1) {
		this.s1 = s1;
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
	
	
	private List<Appointment> apt ;
	public List<Appointment> getApt() {
		apt=iappointmentservice.AfficherDemandes();
		return apt;
	}
	public void setApt(List<Appointment> apt) {
		this.apt = apt;
	}
	
	private List<Appointment> aptt ;
	public List<Appointment> getAptt() {
		aptt=iappointmentservice.getAllRdvByUserId();
		return aptt;
	}
	public void setAptt(List<Appointment> aptt) {
		this.aptt = aptt;
	}
	
	public String afficher(Appointment apt)
	{
		String navigateTo ="null";
	this.setDateOfApt(apt.getDateOfApt());//getId() mel entity Announce w setIdannonce mel controller
	this.setIdapt(apt.getIdapt());
	this.setHour(apt.getHour());
	this.setLocation(apt.getLocation());
	this.setClientfirstName(apt.getClientfirstName());
	this.setClientlastName(apt.getClientlastName());
	this.setMail(apt.getMail());
	this.setPhonenumber(apt.getPhonenumber());
	this.setAdId(apt.getAdId());
	this.setUserId(apt.getUserId());
	this.setStateapt(apt.getStateapt());
	navigateTo = "/pages/detail.xhtml?faces-redirect=false";//bech tHezzni lel page elli bech naffichi fiha les détails
		return navigateTo;
	}
	
	public void deleteAdsById(int idapt) {
		
		iappointmentservice.deleteAdsById1(idapt);
	}
	
	
	public void ChangeStateById(int idapt) {
		iappointmentservice.ChangeStateById(idapt);
	}
	
	public void ChangeStateById1(int idapt) {
		iappointmentservice.ChangeStateById1(idapt);
	}
	
	public String getAllRdvByUserIdWaiting1() {
		String navigateTo ="null";
		navigateTo = "/pages/Waiting.xhtml?faces-redirect=false";//bech tHezzni lel page elli bech naffichi fiha les détails
		return navigateTo;
	}
	
	public String getAllRdvByUserIdWaiting3() {
		String navigateTo ="null";
		navigateTo = "/pages/MyAppointments.xhtml?faces-redirect=false";//bech tHezzni lel page elli bech naffichi fiha les détails
		return navigateTo;
	}
	
	public String getAllRdvByUserIdWaiting2() {
		String navigateTo ="null";
		navigateTo = "/pages/Confirmed.xhtml?faces-redirect=false";//bech tHezzni lel page elli bech naffichi fiha les détails
		return navigateTo;
	}
	
	
	private List<Appointment> tapt ;
	public List<Appointment> getTapt() {
		tapt=iappointmentservice.getTodaysApt();
		return tapt;
	}
	public void setTapt(List<Appointment> tapt) {
		this.tapt = tapt;
	}
	
	
	private List<Appointment> aptw;


	public List<Appointment> getAptw() {
		aptw=iappointmentservice.getAllRdvByUserIdWaiting();
		return aptw;
	}
	public void setAptw(List<Appointment> aptw) {
		this.aptw = aptw;
	}
	
	private List<Appointment> aptc;


	public List<Appointment> getAptc() {
		aptc=iappointmentservice.getAllRdvByUserIdWaiting2();
		return aptc;
	}
	public void setAptc(List<Appointment> aptc) {
		this.aptc = aptc;
	}
	
	public String Etat(Date date , int idapt){
		String s1 = appointmentRepository.getAptToDelete(idapt).getAdId().getUser().getEmail();
		int adid = appointmentRepository.getAptToDelete(idapt).getAdId().getUser().getId();
		String titre = appointmentRepository.getAptToDelete(idapt).getAdId().getTitle();
		String s= appointmentRepository.getAptToDelete(idapt).getUserId().getEmail();
	       long diff = date.getTime() - d.getTime();
	       float res = (diff / (1000*60*60*24));
	       
	       
		if((res<=1) && (res>=0.5))
		{
			msg.setTo(s1);
			msg.setSubject(titre);
			msg.setText("Dont forget you have an appointment coming soon" );
			javaMailSender.send(msg);
			
			msg.setTo(s);
			msg.setSubject(titre);
			msg.setText("Dont forget you have an appointment coming soon" );
			javaMailSender.send(msg);
			return "c";}
		else if((res<0) && (res>-7))
		{return "d";}
		else if(res<-7)
		{iappointmentservice.deleteAdsById1(idapt);
		return "j";}
		else return "e";
	}
}


