package tn.esprit.spring.services;


import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Ads;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.entities.User;

public interface IAppointmentService {
	
	//public Appointment ajouterApt(Appointment apt);
	 //public void ajouterApt(Appointment apt);
	 public void ajouterApt(Appointment apt , int idAd);
	 List<Appointment> retrieveAllApt();
	 public void deleteAptById(int aptId);
	 public Appointment updateApt(Appointment apt);
	 public List<Appointment> getAllRdvByUserId() ;
	 //public Appointment getAptByState(StateApt state);
	 public List<Appointment> getAptByState(StateApt state);
	// public List<Appointment> getAptOrderedByDate();
	 public List<Appointment> getAllAptList();
	 public void ChangeStateById(int aptId);
	 public void ChangeStateById1(int aptId);
	 public List<Appointment> AfficherDemandes();
	 public void deleteAdsById1(int aptId);
	 public List<Appointment> getAllRdvByUserIdWaiting();
	 public List<Appointment> getTodaysApt();
	 public List<Appointment> getAllRdvByUserIdWaiting2();

}
