package com.lfr.rental;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Request,Integer> {
	
}
