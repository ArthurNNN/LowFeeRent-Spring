package controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.javafaker.Faker;



//import model.Lessor;
//import model.Request;
//import model.Tenant;

import utils.Utils;

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

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());

		return "apartments.html";
	}

	@RequestMapping("/fillApartments")
	public String fillApartments(Model boxToView) {
		Faker faker = new Faker();
		System.out.print("---------------- Adding 10 apartments: ----------------");
		int n = 1;
		while (n <= 10) {
			Apartment apartment = new Apartment(Utils.randRange(8, 25) * 100, Utils.randRange(10, 20) * 10,
					Utils.randRange(1, 5), Utils.randRange(1, 3), faker.address().streetAddress(true));
			apartment = apartmentRepository.save(apartment);
			System.out.print("\n#" + n + " ");
			System.out.print(apartment);
			n++;
		}

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());

		return "apartments.html";
	}

//	@RequestMapping("/allRequests")
//	public String getAllRequests(Model boxToView) {
//
//		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());
//
//		return "requests.html";
//	}
	
//	@RequestMapping("/fillRequests")
//	public String fillRequests(Model boxToView) {
//		System.out.print("---------------- Adding 10 requests: ----------------");
//		int n = 1;
//		while (n <= 10) {
//			Request request = new Request(
//					LocalDate.of(2021, Utils.randRange(5, 6), Utils.randRange(1, 28)),
//					LocalDate.of(2021, Utils.randRange(6, 7), Utils.randRange(1, 28)), 
//					Utils.randRange(5, 18) * 100,
//					Utils.getRandomBoolean() ? Utils.randRange(8, 10) * 10 : 0,
//					Utils.getRandomBoolean() ? Utils.randRange(1, 5) : 0,
//					Utils.getRandomBoolean() ? Utils.randRange(1, 3) : 0);
//			request = requestRepository.save(request);
//			System.out.print("\n#" + n + " ");
//			System.out.print(request);
//			n++;
//		}
//
//		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());
//
//		return "requests.html";
//	}

	@RequestMapping("/allPersons")
	public String getAllPersons(Model boxToView) {

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "persons.html";
	}

}
