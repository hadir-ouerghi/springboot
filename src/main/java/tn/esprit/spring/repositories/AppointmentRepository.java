package tn.esprit.spring.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.entities.User;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	@Query("Select "
			+ "apt from Appointment apt where apt.userId.id = :idUser and (apt.stateapt = :state1 or apt.stateapt = :state2) order by apt.dateOfApt ")
    public List<Appointment> getAllRdvByUserId(@Param("idUser") int iduser , @Param("state1") StateApt state1 , @Param("state2") StateApt state2 );
	
	@Query("Select "
			+ "apt from Appointment apt  "
			+ "where apt.stateapt = :state "
			+ "order by apt.dateOfApt ")
    public List<Appointment> getAptByState(@Param("state") StateApt state);
	
	
	@Query("Select "
			+ "apt from Appointment apt  "
			+ "order by apt.dateOfApt ")
    public List<Appointment> getAptOrderedByDate();
	
	@Query("Select apt from Appointment apt where apt.adId.user.id = :idUser and (apt.stateapt = :state1 or apt.stateapt = :state2) order by apt.dateOfApt ")
    public List<Appointment> getAptDemande(@Param("idUser") int idUser , @Param("state1") StateApt state1 , @Param("state2") StateApt state2);
	
	@Query("Select apt from Appointment apt where apt.dateOfApt = :date and apt.hour = :h and apt.adId.user.id = :id and apt.stateapt = :state1")
	public Appointment getAptHour(@Param("date") Date date ,  @Param("h") java.sql.Time h , @Param("id") int id , @Param("state1") StateApt state1);
	
	@Query("Select Count(apt) from Appointment apt where apt.dateOfApt = :date and apt.adId.user.id = :id and apt.stateapt = :state1 or apt.stateapt = :state2")
	public int getAptByDate(@Param("id") int id , @Param("date") Date date , @Param("state1") StateApt state1 , @Param("state2") StateApt state2);
	
	@Query("Select apt from Appointment apt where apt.idapt = :idapt ")
    public Appointment getAptToDelete(@Param("idapt") int idapt);
	
	@Query("Select apt from Appointment apt where apt.adId.user.id = :id and apt.dateOfApt = :dateapt and apt.hour = :hourapt and apt.stateapt = :state3 ")
    public Appointment getAptToSendMail(@Param("id") int id , @Param("dateapt") Date dateapt , @Param("hourapt") java.sql.Time hourapt , @Param("state3") StateApt state3 );
	
	@Modifying
	@Transactional
	@Query(value="DELETE from Appointment apt where apt.idapt = :id ")
	public void DeleteApt(@Param("id") int id);
	
	
	@Query("Select "
			+ "apt from Appointment apt where apt.userId.id = :idUser and apt.stateapt = :state1 order by apt.dateOfApt ")
    public List<Appointment> getAllRdvByUserIdWaiting(@Param("idUser") int iduser , @Param("state1") StateApt state1);
	
	@Query("Select "
			+ "apt from Appointment apt where (apt.userId.id = :idUser and apt.stateapt = :state1 and apt.dateOfApt= :date) or (apt.adId.user.id = :idUser and apt.stateapt = :state1 and apt.dateOfApt= :date) order by apt.dateOfApt")
    public List<Appointment> getTodaysApt(@Param("idUser") int iduser , @Param("state1") StateApt state1 , @Param("date") Date date );
	
	

}
