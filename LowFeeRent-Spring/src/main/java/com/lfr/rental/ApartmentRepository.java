package com.lfr.rental;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApartmentRepository extends CrudRepository<Apartment,Integer> {
	
//  List<Apartment> findByPrice(int price);
//  List<Apartment> findByRooms(int rooms);
//  List<Apartment> findByPriceAndRooms(int price, int rooms);
//  @Query("SELECT a FROM Apartment a WHERE a.price<=:price and a.rooms<=:rooms")
//  List<Apartment> fetchApartments(@Param("price") int price, @Param("rooms") int rooms);
	
}
