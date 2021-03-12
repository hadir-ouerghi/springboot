package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Days;
import tn.esprit.spring.entities.Disponibility;
import tn.esprit.spring.entities.StateApt;
import tn.esprit.spring.entities.StateDispo;
import tn.esprit.spring.entities.User;

@Repository
public interface DisponibilityRepository extends CrudRepository<Disponibility, Integer> {
	
	
	
	Appointment apt = new Appointment();
	
	@Query("Select "
			+ "dsp from Disponibility dsp  "
			+ "where dsp.userIdD.id = :idUser ")
			
    public List<Disponibility> getAllDspByUserId(@Param("idUser") int iduser);
	
	
	@Query("Select "
			+ "dsp from Disponibility dsp  "
			+ "where dsp.days = :day ")
	public List<Disponibility> getDspByDay(@Param("day") Days day);
	
	
	@Query("Select "
			+ "days from Disponibility dsp  "
			+ "where dsp.days = :val and dsp.userIdD = :idD")
	public Days getDspByDay1(@Param("val") Days val , @Param("idD") User idD);
	
	@Query("Select "
			+ "dsp from Disponibility dsp  "
			+ "where :val2 = :val1 ")
	public List<Disponibility> getDspByDayToSync(@Param("val1") String val1 ,  @Param("val2") String val2);
	
	@Query("Select "
			+ "days from Disponibility dsp  "
			)
	public Days getDspByD();
	

	@Query("Select "
			+ "dsp from Disponibility dsp  "
			+ "where dsp.days = :jour "
			+ "and dsp.userIdD.id = :idD")
	public Disponibility getDspByDaySync(@Param("jour") Days jour , @Param("idD") int idD);
	
	@Query("Select "
			+ "dsp.hourd from Disponibility dsp  "
			//+ "where dsp.hourd = :heured "
			+ "where dsp.days = :jour "
			+ "and dsp.userIdD.id = :idD")
	public java.sql.Time getHourD(@Param("jour") Days jour ,@Param("idD") int idD);
	
	@Query("Select "
			+ "dsp.hourf from Disponibility dsp  "
			//+ "where dsp.hourd = :heured "
			+ "where dsp.days = :jour "
			+ "and dsp.userIdD.id = :idD")
	public java.sql.Time getHourF(@Param("jour") Days jour ,@Param("idD") int idD);
	
	@Query("Select "
			+ "dsp.statedispo from Disponibility dsp  "
			+ "where dsp.days = :jour "
			+ "and dsp.userIdD.id = :idD")
	public StateDispo getStateDispo(@Param("jour") Days jour ,@Param("idD") int idD);

	
	/*@Query("Select "
			+ "dsp from Disponibility dsp  "
			+ "where dsp.days = :day "
			+ "where dsp.userIdD.id = :idUser ")
	public List<Disponibility> getDspByDay(@Param("day") Days day ,@Param("idUser")  int iduser);*/
	
	
	/*@Query("Select "
			+ "dsp from Disponibility dsp "
			//+ "and apt from Appointment apt"
			//+ "where apt.adId.user.id = dsp.userIdD")
			+ "where dsp.userIdD.Adss.id = :idAd ")
	public List<Disponibility> getDspById1(@Param("idAd") int idAd);
			
			//@Param("idAd") int idAd
	
	//@Query(value="Select d from Disponibility d where d.userIdD.Adss.id= :idAnnonce")*/

	
	
}

	