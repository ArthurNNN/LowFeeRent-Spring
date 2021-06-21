package com.lfr.rental;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.javafaker.Faker;
import com.lfr.person.Person;
import com.lfr.person.PersonRepository;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/")
	public String getAdminConsole( Model boxToView) {
		//System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());
		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());
		

		return "admin.html";
	}

	@RequestMapping("/fillApt")
	public String fillApartments(Model boxToView) {
		Faker faker = new Faker();
		System.out.print("\n---------------- Adding apartments: ----------------");
		int n = 1;
		while (n <= 10) {
			Apartment apartment = new Apartment(Utils.randRange(8, 25) * 100, Utils.randRange(6, 18) * 10,
					Utils.randRange(1, 5), Utils.randRange(1, 3), faker.address().streetAddress(true));

			HashMap<LocalDate, LocalDate> dates = new HashMap<LocalDate, LocalDate>();
			dates.put(LocalDate.of(2021, Utils.randRange(1, 4), Utils.randRange(1, 28)),
					LocalDate.of(2021, Utils.randRange(3, 4), Utils.randRange(1, 28)));
			dates.put(LocalDate.of(2021, Utils.randRange(5, 8), Utils.randRange(1, 28)),
					LocalDate.of(2021, Utils.randRange(7, 8), Utils.randRange(1, 28)));
			dates.put(LocalDate.of(2021, Utils.randRange(9, 12), Utils.randRange(1, 28)),
					LocalDate.of(2021, Utils.randRange(10, 12), Utils.randRange(1, 28)));
			apartment.setOpenDates(dates);

			apartment = apartmentRepository.save(apartment);
			System.out.print("\n#" + n + " ");
			System.out.print(apartment);
			n++;
		}

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());

		return "redirect:/admin";
	}
	
	@RequestMapping("/fillPers")
	public String fillAllPersons(Model boxToView) {

		Faker faker = new Faker();

		System.out.print("\n---------------- Add persons: ----------------");
		int n = 1;
		while (n <= 10) {
			Person person = new Person();
			person.setName(faker.name().firstName());
			person.setSurname(faker.name().lastName());
			person.setEmail(faker.internet().emailAddress());
			System.out.print("\n#" + n + " ");
			System.out.print(person);
			n++;
			person = personRepository.save(person);
			System.out.print("\n#" + n + " ");
			System.out.print(person);
			n++;

		}

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "redirect:/admin";
	}
	
	@RequestMapping("/fillReq")
	public String fillInRequests(Model boxToView) {
		System.out.print("\n---------------- Adding 10 requests: ----------------");
		int n = 1;
		while (n <= 10) {
			Request request = new Request(LocalDate.of(2021, Utils.randRange(5, 6), Utils.randRange(1, 28)),
					LocalDate.of(2021, Utils.randRange(6, 7), Utils.randRange(1, 28)), Utils.randRange(5, 18) * 100,
					Utils.getRandomBoolean() ? Utils.randRange(8, 10) * 10 : 0,
					Utils.getRandomBoolean() ? Utils.randRange(1, 5) : 0,
					Utils.getRandomBoolean() ? Utils.randRange(1, 3) : 0);
			request = requestRepository.save(request);
			System.out.print("\n#" + n + " ");
			System.out.print(request);
			n++;
		}

		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());

		return "redirect:/admin";
	}

}
