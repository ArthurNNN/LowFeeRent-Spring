package com.lfr.rental;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {

	@Query("select a from Apartment a join a.openDates od"
			+ " where (key(od) <= :checkin and od >= :checkout) "
			+ "and a.price <= :price AND a.area >= :area AND a.rooms >= :rooms AND a.bathrooms >= :bathrooms")
	List<Apartment> fetchApartments(@Param("checkin") LocalDate checkin, @Param("checkout") LocalDate checkout,
			@Param("price") Integer price, @Param("area") Integer area, @Param("rooms") Integer rooms,
			@Param("bathrooms") Integer bathrooms

	);
}
