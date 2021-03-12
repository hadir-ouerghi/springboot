package tn.esprit.spring.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.StateApt;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.AdsRepository;
import tn.esprit.spring.repositories.AppointmentRepository;
import tn.esprit.spring.repositories.DisponibilityRepository;
import tn.esprit.spring.repositories.UserRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Service("IAppointmentService")
public class AppointmentService implements IAppointmentService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	SimpleMailMessage msg = new SimpleMailMessage();

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AdsRepository adsRepository;
	
	@Autowired
	DisponibilityRepository disponibilityRepository;
	
	StateApt state1 = StateApt.waiting ;
	StateApt state2 = StateApt.confirmed ;
	

	@Override
	public void ajouterApt(Appointment apt, int idAd) {
		// int i ;
		User userr = userRepository.findById(4).get();
		Ads ads = adsRepository.findById(idAd).get();

		// int j = userr.getId();
		// Ads ads = adsRepository.findById(j).get();

		apt.setAdId(ads);
		apt.setUserId(userr);
		
		// apt.setTypeapt(TypeApt.appointmentlist);
		
		
		appointmentRepository.save(apt);

		/*
		 * else if (i==1) { apt.setUserId(ads);
		 * apt.setStateapt(StateApt.waiting); apt.setTypeapt(TypeApt.request);
		 * appointmentRepository.save(apt); }
		 */

		/*
		 * User user = userRepository.findById(2).get(); apt.setUserId(user);
		 * apt.setStateapt(StateApt.waiting);
		 * apt.setTypeapt(TypeApt.appointmentlist);
		 * appointmentRepository.save(apt);
		 */
	}

	private static final Logger l = LogManager.getLogger(AppointmentService.class);

	@Override
	public List<Appointment> retrieveAllApt() {
		List<Appointment> apts = (List<Appointment>) appointmentRepository.findAll();
		for (Appointment appointment : apts) {
			l.info("appointment :" + appointment);
		}
		return apts;
	}

	@Override
	public void deleteAptById(int aptId) {
		/*int u = 3 ;
		User userr = userRepository.findById(u).get();
		if(appointmentRepository.getAptToDelete(aptId).getUserId() != userr)
		{System.out.println("you can't delete this appointment");}
		
		else*/
		Appointment a =appointmentRepository.findById(aptId).get();
		appointmentRepository.delete(a);
	}
	
	
	@Override
	public void deleteAdsById1(int aptId) {
		appointmentRepository.DeleteApt(aptId);	
	}

	@Override
	public Appointment updateApt(Appointment apt) {
		return appointmentRepository.save(apt);
	}

	@Override
	public List<Appointment> getAllRdvByUserId() {
		return appointmentRepository.getAllRdvByUserId(4 , state1 , state2);
	}
	//int id

	
	@Override
	public List<Appointment> getAllRdvByUserIdWaiting() {
		return appointmentRepository.getAllRdvByUserIdWaiting(4 , state1);
	}
	
	@Override
	public List<Appointment> getAllRdvByUserIdWaiting2() {
		return appointmentRepository.getAllRdvByUserIdWaiting(4 , state2);
	}
	
	
	@Override
	public List<Appointment> getAptByState(StateApt state) {
		return appointmentRepository.getAptByState(state);
	}

	@Override
	public List<Appointment> getAllAptList() {
		List<Appointment> apt = (List<Appointment>) appointmentRepository.getAptOrderedByDate();
		return apt;
	}

	@Override
	public void ChangeStateById(int aptId) {
		String titre = appointmentRepository.getAptToDelete(aptId).getAdId().getTitle();
		String to = appointmentRepository.findById(aptId).get().getUserId().getEmail();
		Appointment apt = appointmentRepository.findById(aptId).get();
		apt.setStateapt(StateApt.confirmed);
		appointmentRepository.save(apt);
		msg.setTo(apt.getMail());
		msg.setSubject(titre);
		msg.setText("Your appointment has been accepted");
		javaMailSender.send(msg);

	}

	@Override
	public void ChangeStateById1(int aptId) {
		String titre = appointmentRepository.getAptToDelete(aptId).getAdId().getTitle();
		Appointment apt = appointmentRepository.findById(aptId).get();
		apt.setStateapt(StateApt.refused);
		appointmentRepository.save(apt);
		msg.setTo(apt.getMail());
		msg.setSubject(titre);
		msg.setText("Your appointment has been refused");
		javaMailSender.send(msg);

	}

	@Override
	public List<Appointment> AfficherDemandes() {

		return appointmentRepository.getAptDemande(4 , state1 , state2);

	}
	//int userId
	
	@Override
	public List<Appointment> getTodaysApt() {
		LocalDateTime localDate = LocalDateTime.now();
		Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		return appointmentRepository.getTodaysApt(4 , state2 , d);

	}

}
