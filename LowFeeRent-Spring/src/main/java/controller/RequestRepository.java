package controller;

import org.springframework.data.repository.CrudRepository;

import model.Request;

public interface RequestRepository extends CrudRepository<Request,Integer> {
	
}
