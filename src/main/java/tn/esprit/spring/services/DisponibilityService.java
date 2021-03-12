package tn.esprit.spring.services;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Days;
import tn.esprit.spring.entities.Disponibility;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.entities.StateDispo;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.AdsRepository;
import tn.esprit.spring.repositories.AppointmentRepository;
import tn.esprit.spring.repositories.DisponibilityRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service("IDisponibilityService")
public class DisponibilityService implements IDisponibilityService {
	
	@Autowired
	DisponibilityRepository disponibilityRepository;
	
	@Autowired
	AdsRepository adsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void ajouterDispo(Disponibility dsp){
		
			User userr = userRepository.findById(4).get();
	
			dsp.setUserIdD(userr);
			dsp.setStatedispo(StateDispo.Available);
			
			Days val= dsp.getDays();
			System.out.println(val);
			if(disponibilityRepository.getDspByDay1(val,userr) != null){
				System.out.println("This day already exist you can only edit it");
			}
			else
			/*if(dsp.getDays().toString().contains("Monday")){
				System.out.println("This day already exist you can only edit it");
			}
			else*/
			
			disponibilityRepository.save(dsp);
	}
	
	private static final Logger l =LogManager.getLogger(DisponibilityService.class);
	@Override
	public List<Disponibility> retrieveAllDsp() {
		List<Disponibility> dsps = (List<Disponibility>) disponibilityRepository.findAll();
		for (Disponibility disponibility : dsps){
			l.info("appointment :" +disponibility);
		}
		return dsps;
	}
	
	@Override
	public List<Disponibility> getAllDspByUserId() {
		return disponibilityRepository.getAllDspByUserId(5);
	}
	//int id
	
	/*@Override
	public List<Disponibility> getDspByDay(Days day) {
		return disponibilityRepository.getDspByDay(day);
	}*/
	
	@Override
	public List<Disponibility> getDspById() {
	
		//return disponibilityRepository.getDspById1(idAd);
		int user = adsRepository.findById(5).get().getUser().getId() ;
		List <Disponibility> dsps = disponibilityRepository.getAllDspByUserId(user);
		
			return dsps;
			
		
				
	}

}
