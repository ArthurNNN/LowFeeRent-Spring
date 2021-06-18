package com.lfr.rental;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/")
public class ApartmentController {

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/template")
	public String getHotel(
	// Model boxToView
	) {

		// boxToView.addAttribute("apartmentListfromControllerAndDB",
		// apartmentRepository.findAll());

		return "template.html";
	}

	@RequestMapping("/")
	public String getApartments( Model boxToView) {
		//System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
		

		return "home.html";
	}

	@RequestMapping("/filter")
	public String createRequest(Request request) {
		System.out.println(request);
		requestRepository.save(request);

		return "redirect:/home.html";
	}

	@RequestMapping("/fill")
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

		return "redirect:/";
	}

}
