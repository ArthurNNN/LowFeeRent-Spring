package com.lfr.rental;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ApartmentRepository apartmentRepository;

	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/")
	public String home(Model boxToView, Request req) {

		
		
		Integer price = req.priceMax != null ? req.priceMax : 100000;
		Integer area = req.areaMin != null ? req.areaMin : 0;
		Integer rooms = req.roomsMin != null ? req.roomsMin : 0;
		Integer bathrooms = req.bathroomsMin != null ? req.bathroomsMin : 0;
				
//		Request req = new Request(price, area, rooms, bathrooms);


		boxToView.addAttribute("apartmentList", apartmentRepository.fetchApartments(price, area, rooms, bathrooms));

		return "lowfeerent.html";
	}

	@RequestMapping("/fillin")
	public String fillApartments(int qtyToCreate) {
		Faker faker = new Faker();
		System.out.print("\n---------------- Adding " + qtyToCreate + " apartments: ----------------");
		int n = 1;
		while (n <= qtyToCreate) {
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

			apartmentRepository.save(apartment);
			System.out.print("\n#" + n + " ");
			System.out.print(apartment);
			n++;
		}

		return "home";
	}

	@RequestMapping("/admin")
	public String getAdminConsole(Model boxToView) {

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "admin.html";
	}

}
