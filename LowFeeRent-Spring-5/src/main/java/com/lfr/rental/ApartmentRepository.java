package com.lfr.rental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

	@Query("SELECT a FROM Apartment a WHERE a.price <= :price AND a.area >= :area AND a.rooms >= :rooms AND a.bathrooms >= :bathrooms")
	List<Apartment> fetchApartments(@Param("price") Integer price, @Param("area") Integer area,
			@Param("rooms") Integer rooms, @Param("bathrooms") Integer bathrooms);
}
