package com.lfr.rental;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/")
public class HomeController {
	boolean isFirstRender = true;

	@Autowired
	ApartmentRepository apartmentRepository;

	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/")
	public String home(Model boxToView, Request req) {

		if (isFirstRender) {
			Faker faker = new Faker();
			int n = 1;
			while (n <= 32) {
				Apartment apartment = new Apartment(Utils.randRange(8, 25) * 100, Utils.randRange(6, 18) * 10,
						Utils.randRange(1, 5), Utils.randRange(1, 3), faker.address().streetAddress(true));

				Map<LocalDate, LocalDate> datesMap = new HashMap<LocalDate, LocalDate>();
				datesMap.put(LocalDate.of(2021, Utils.randRange(1, 4), Utils.randRange(1, 28)),
						LocalDate.of(2021, Utils.randRange(3, 4), Utils.randRange(1, 28)));
				datesMap.put(LocalDate.of(2021, Utils.randRange(5, 8), Utils.randRange(1, 28)),
						LocalDate.of(2021, Utils.randRange(7, 8), Utils.randRange(1, 28)));
				datesMap.put(LocalDate.of(2021, Utils.randRange(9, 12), Utils.randRange(1, 28)),
						LocalDate.of(2021, Utils.randRange(10, 12), Utils.randRange(1, 28)));

				System.out.println(datesMap);
				apartment.setOpenDates(datesMap);
				apartmentRepository.save(apartment);
				n++;
			}
			isFirstRender = false;
		}


		LocalDate checkin = req.checkin != null ? req.checkin : LocalDate.now();
		LocalDate checkout = req.checkout != null ? req.checkout : LocalDate.now();
		Integer price = req.priceMax != null ? req.priceMax : 5000;
		Integer area = req.areaMin != null ? req.areaMin : 0;
		Integer rooms = req.roomsMin != null ? req.roomsMin : 0;
		Integer bathrooms = req.bathroomsMin != null ? req.bathroomsMin : 0;

		boxToView.addAttribute("requestFromController", new Request(checkin, checkout, price, area, rooms, bathrooms));
		boxToView.addAttribute("apartmentList",
				apartmentRepository.fetchApartments(checkin, checkout, price, area, rooms, bathrooms));

		return "lowfeerent.html";
	}

	@RequestMapping("/admin")
	public String getAdminConsole(Model boxToView) {

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "admin.html";
	}

}
