package tn.esprit.spring.contollers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import aj.org.objectweb.asm.Type;
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
import tn.esprit.spring.services.IAppointmentService;
import tn.esprit.spring.services.IDisponibilityService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Controller
public class AppointmentController {
	@Autowired
	private JavaMailSender javaMailSender;
	
	SimpleMailMessage msg = new SimpleMailMessage();

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
	
	StateApt state1 = StateApt.waiting ;
	StateApt state2 = StateApt.confirmed ;
	StateApt state3 = StateApt.demande ;

	Days jour;

	// *********GESTION DES RENDEZ VOUS*********

	@SuppressWarnings("deprecation")
	// ajouter un rendez vous
	// http://localhost:8081/SpringMVC/servlet/ajouterApt/2 #POST
	@PostMapping("/ajouterApt/{ads}")
	@ResponseBody
	public void ajouterApt(@RequestBody Appointment apt, @PathVariable("ads") int ads) {
		int id = adsRepository.findById(ads).get().getUser().getId(); //l'id du client qui a déposé l'annonce 5

		LocalDateTime localDate = LocalDateTime.now();
		Date d = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());

		java.sql.Time h = apt.getHour();
		
	
		
		Calendar cal = Calendar.getInstance();//pour convertir la date en num du jour
		cal.setTime(apt.getDateOfApt());
		int i1 = cal.get(Calendar.DAY_OF_WEEK);
		int i= i1-1;
		System.out.println(i);

		{
			if (i == 1)
				jour = Days.Monday;

			else if (i == 2)
				jour = Days.Tuesday;

			else if (i == 3)
				jour = Days.Wednesday;

			else if (i == 4)
				jour = Days.Thursday;

			else if (i == 5)
				jour = Days.Friday;

			else if (i == 6)
				jour = Days.Saturday;

			else if (i == 7)
				jour = Days.Sunday;
		}
		
		Date day = apt.getDateOfApt();
		int v1 = (disponibilityRepository.getHourF(jour , id)).getHours();
		int v2 = (disponibilityRepository.getHourD(jour , id)).getHours();
		int v3 = v1 - v2 ;
		System.out.println(v3);
		

		// DayOfWeek val1 = DayOfWeek.of(val);
		// String val2 = val1.toString();
		// Days val3 = Days.valueOf(val2);
		// assertTrue(val3 == Days.val2);
		// Enum.Parse(typeof(Days), val2, true);
		// Days val3 = (Days)Enum.Parse( typeof(Days.class), val2 );
		/*
		 * Disponibility dsp = new Disponibility(); String val2 =
		 * disponibilityRepository.getDspByD().toString();
		 */
		
		
		
		int v4 = appointmentRepository.getAptByDate(id , day , state1 , state2);
		System.out.println(v4);
		if (v4-1 == v3)
		{disponibilityRepository.getDspByDaySync(jour, id).setStatedispo(StateDispo.NotAvailable);
			System.out.println("erreurrrrr");
		} // condition pour vérifier si tous les heures de l'intervalle de disponibilité sont disponibles ou non 
		
		
		else if ((apt.getDateOfApt()).before(d)) {
			System.out.println("Error , Please choose a date");
		} // condition pour que la date nes soit pas avant la date de systéme
		
		else if (disponibilityRepository.getDspByDaySync(jour, id) == null) {
			System.out.println(jour);
			System.out.println("This day is not available");
		} // condition pour vérifier si le jour choisi est disponible ou non (s'il existe dans la base de données ou non)
		
		else if (disponibilityRepository.getStateDispo(jour , id) == StateDispo.NotAvailable){
			System.out.println("Not Available");
		} // condition pour vérifier l'état du jour
		
		else if ((apt.getHour().getMinutes() != 00) || (apt.getHour().getSeconds() != 00)) {
			System.out.println("erreur");
		} // condition sur l'heure chiffre rond
		
		else if((disponibilityRepository.getHourD(jour , id)).after(h)){
			System.out.println("The owner in not availablee");
		} // condition pour vérifier si l'heure choisie est aprés l'heure de début de la diponibilité du client désiré
		 

		/*else if((disponibilityRepository.getHourF(jour , id)).before(h)){
			System.out.println("The owner in not available");
		} // condition pour vérifier si l'heure choisie est avant l'heure de fin de la diponibilité du client désiré*/
		
		else if (appointmentRepository.getAptHour(day , h , id , state2 ) != null) {
			System.out.println("The owner is busy");
			StateApt a = StateApt.demande; 
			apt.setStateapt(a);
			iappointmentservice.ajouterApt(apt, ads);
		} // condition pour vérifier si le propriétaire a un autre rendez vous à l'heure choisie par le client 
		
		
		
		else
		{   apt.setStateapt(StateApt.waiting);
			iappointmentservice.ajouterApt(apt, ads);}
	}

	
	
	// afficher toute la liste des rendez vous
	// http://localhost:8081/SpringMVC/servlet/retrieve-all-apt #GET
	@GetMapping("/retrieve-all-apt")
	@ResponseBody
	public List<Appointment> getApt() {
		List<Appointment> list = iappointmentservice.retrieveAllApt();
		return list;
	}

	// annuler un rendez vous
	// http://localhost:8081/SpringMVC/servlet/deleteAptById/2 #DELETE
	@DeleteMapping("/deleteAptById/{idapt}")
	@ResponseBody
	public void deleteAptById(@PathVariable("idapt") int aptId) {
		Date dateapt = appointmentRepository.getAptToDelete(aptId).getDateOfApt();
		java.sql.Time hourapt = appointmentRepository.getAptToDelete(aptId).getHour();
		int adid = appointmentRepository.getAptToDelete(aptId).getAdId().getUser().getId();
		String titre = appointmentRepository.getAptToDelete(aptId).getAdId().getTitle();
		
		String m = appointmentRepository.getAptToSendMail(adid, dateapt, hourapt , state3).getUserId().getEmail();
		
		msg.setTo(m);
		msg.setSubject(titre);
		msg.setText("The owner is now available you can take an appointment" );
		javaMailSender.send(msg);
		
		
		iappointmentservice.deleteAdsById1(aptId);
	}
	

	// modifier un rendez vous
	// http://localhost:8081/SpringMVC/servlet/modifyApt #PUT
	@PutMapping("/modifyApt")
	@ResponseBody
	public Appointment modifyApt(@RequestBody Appointment apt) {
		return iappointmentservice.updateApt(apt);
	}

	// affichage de mes rendez vous triés par date
	// http://localhost:8081/SpringMVC/servlet/getAllRdvByUserId/2 #GET
	@GetMapping(value = "getAllRdvByUserId/{iduser}")
	@ResponseBody
	public List<Appointment> getAllRdvByUserId(@PathVariable("iduser") int userId) {
		return iappointmentservice.getAllRdvByUserId();
	}
	//userId

	// filtre selon l'etat du rendez vous
	// http://localhost:8081/SpringMVC/servlet/getAptByState/confirmed #GET
	@GetMapping(value = "getAptByState/{stateApt}")
	@ResponseBody
	public List<Appointment> getAptByState(@PathVariable("stateApt") StateApt state) {

		return iappointmentservice.getAptByState(state);
	}

	// affichage de tout les rendez vous triés par date
	// http://localhost:8081/SpringMVC/servlet/getAllAptListOrderedByDate #GET
	@GetMapping(value = "/getAllAptListOrderedByDate")
	@ResponseBody
	public List<Appointment> getAllAptList() {
		return iappointmentservice.getAllAptList();
	}

	// lorsque j'accepte une demande de rendez vous state change à confirmed
	// http://localhost:8081/SpringMVC/servlet/ChangeStateById/2 #PUT
	@PutMapping(value = "ChangeStateById/{idapt}")
	@ResponseBody
	public void ChangeStateById(@PathVariable("idapt") int aptId) {
		iappointmentservice.ChangeStateById(aptId);
	}

	// lorsque je refuse une demande de rendez vous state change à refused
	// http://localhost:8081/SpringMVC/servlet/ChangeStateById1/2 #PUT
	@PutMapping(value = "ChangeStateById1/{idapt}")
	@ResponseBody
	public void ChangeStateById1(@PathVariable("idapt") int aptId) {
		iappointmentservice.ChangeStateById1(aptId);
	}

	// afficher la liste de mes demandes
	// http://localhost:8081/SpringMVC/servlet/AfficherDemandes/2 #PUT
	@GetMapping(value = "AfficherDemandes/{iduser}")
	@ResponseBody
	public List<Appointment> AfficherDemandes(@PathVariable("iduser") int iduser) {
		return iappointmentservice.AfficherDemandes();
	}
	//iduser

	
	
		// affichage de mes rendez vous triés par date dont le stateapt est waiting
		// http://localhost:8081/SpringMVC/servlet/getAllRdvByUserIdWaiting/2 #GET
		@GetMapping(value = "getAllRdvByUserIdWaiting/{iduser}")
		@ResponseBody
		public List<Appointment> getAllRdvByUserIdWaiting(@PathVariable("iduser") int userId) {
			return iappointmentservice.getAllRdvByUserIdWaiting();
		}
}
