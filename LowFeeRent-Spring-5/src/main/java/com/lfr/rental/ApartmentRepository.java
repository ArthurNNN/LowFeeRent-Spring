package com.lfr.rental;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

	@Query("SELECT a FROM Apartment a WHERE a.price <= :price AND a.area >= :area AND a.rooms >= :rooms AND a.bathrooms >= :bathrooms")
	List<Apartment> fetchApartments(
//			@Param("checkin") LocalDate checkin, @Param("checkout") LocalDate checkout, 
			@Param("price") Integer price, @Param("area") Integer area,
			@Param("rooms") Integer rooms, @Param("bathrooms") Integer bathrooms);
}
