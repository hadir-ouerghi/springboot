package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Ads;



public interface AdsRepository extends  CrudRepository<Ads, Integer> {

}
