package tn.esprit.spring.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Days;
import tn.esprit.spring.entities.Disponibility;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.services.IDisponibilityService;

@Controller
public class DisponibilityController {
	
	@Autowired
	IDisponibilityService idisponibilityservice;
	
			//*********GESTION DE LA DISPONIBILITE*********
	
	
			//ajouter la disponibilité de la semaine pour chaque client
			//http://localhost:8081/SpringMVC/servlet/ajouterDispo #POST
			@PostMapping("/ajouterDispo")
			@ResponseBody
			public void ajouterDispo(@RequestBody Disponibility dsp){
				
				if((dsp.getHourd().getMinutes() != 00) || (dsp.getHourd().getSeconds() != 00) ){
					System.out.println("erreur");	
				}
				else if((dsp.getHourf().getMinutes() != 00) || (dsp.getHourf().getSeconds() != 00) ){
						System.out.println("erreur");	
					}
				else
			idisponibilityservice.ajouterDispo(dsp);	
			}
			
			//afficher toute la liste des disponibilités
			//http://localhost:8081/SpringMVC/servlet/retrieve-all-dsp #GET
		 	@GetMapping("/retrieve-all-dsp")
		 	@ResponseBody
		 	public List<Disponibility> getDsp() {
		 	List<Disponibility> list= idisponibilityservice.retrieveAllDsp();
		 	return list;
		 	}
			
			//affichage de ma disponibilité de chaque semaine
		 	//http://localhost:8081/SpringMVC/servlet/getAllDspByUserId/1 #GET
		    @GetMapping(value = "getAllDspByUserId/{iduser}")
		    @ResponseBody
		  	public List<Disponibility> getAllDspByUserId(@PathVariable("iduser") int userId){
		    return idisponibilityservice.getAllDspByUserId();
		  	}
		    //userId
		    
		   /* //filtre selon le jour de la disponibilité
		    //http://localhost:8081/SpringMVC/servlet/getDspByDays/Monday #GET
		    @GetMapping(value = "getDspByDays/{day}")
		    @ResponseBody
			public List<Disponibility> getDspByDay(@PathVariable("day") Days day) {

				return idisponibilityservice.getDspByDay(day);
			}
		    */
		    //afficher la disponibilité de l'annonceur
		    //http://localhost:8081/SpringMVC/servlet/getDspById/2 #GET
		    @GetMapping(value = "getDspById/{idAd}")
		    @ResponseBody
			public List<Disponibility> getDspById(@PathVariable("idAd") int idAd) {

				return idisponibilityservice.getDspById();
				//int idAd
			}
		   
		

}
