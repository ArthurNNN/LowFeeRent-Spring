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

		LocalDate checkin = req.checkin != null ? req.checkin : LocalDate.now();
		LocalDate checkout = req.checkout != null ? req.checkout : LocalDate.now();
		Integer price = req.priceMax != null ? req.priceMax : 5000;
		Integer area = req.areaMin != null ? req.areaMin : 0;
		Integer rooms = req.roomsMin != null ? req.roomsMin : 0;
		Integer bathrooms = req.bathroomsMin != null ? req.bathroomsMin : 0;

		boxToView.addAttribute("requestFromController", new Request(checkin, checkout, price, area, rooms, bathrooms));
		boxToView.addAttribute("apartmentList", apartmentRepository.fetchApartments(
//				checkin, checkout, 
				price, area, rooms, bathrooms));

		return "lowfeerent.html";
	}


	@RequestMapping("/admin")
	public String getAdminConsole(Model boxToView) {

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "admin.html";
	}

}
