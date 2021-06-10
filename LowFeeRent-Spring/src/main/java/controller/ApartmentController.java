package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/apartment")
public class ApartmentController {
	
	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	PersonRepository personRepository;
	

	@RequestMapping("/allApartments")
	public String getAllApartments(Model boxToView) {
		

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll() );

			
		return "apartments.html";
	}
	
	@RequestMapping("/allRequests")
	public String getAllRequests(Model boxToView) {
		

		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll() );

			
		return "requests.html";
	}
	
	@RequestMapping("/allPersons")
	public String getAllPersons(Model boxToView) {
		

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll() );

			
		return "persons.html";
	}

}
