package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Days;
import tn.esprit.spring.entities.Disponibility;

public interface IDisponibilityService {
	
	//public void ajouterDispo(Disponibility dsp);
	public void ajouterDispo(Disponibility dsp);
	public List<Disponibility> getAllDspByUserId();
	List<Disponibility> retrieveAllDsp();
	//public List<Disponibility> getDspByDay(Days day);
	//public List<Disponibility> getDspByDays(Days day, int idAd);
	public List<Disponibility> getDspById();

}
