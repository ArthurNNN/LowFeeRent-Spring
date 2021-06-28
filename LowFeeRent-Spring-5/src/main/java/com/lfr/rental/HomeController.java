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
	public String getApartments(Model boxToView, @Param("price") Integer price, @Param("area") Integer area,
			@Param("rooms") Integer rooms, @Param("bathrooms") Integer bathrooms) {

//		System.out.println("price: " + price);
//		System.out.println("price >= null: " + (1500 > null));
//		System.out.println("area: " + area);
//		System.out.println("rooms: " + rooms);
//		System.out.println("bathrooms: " + bathrooms);

		if (price == null) {
			price = 10000;
		}
		if (area == null) {
			area = 0;
		}
		if (rooms == null) {
			rooms = 0;
		}
		if (bathrooms == null) {
			bathrooms = 0;
		}

		boxToView.addAttribute("apartmentList", apartmentRepository.fetchApartments(price, area, rooms, bathrooms));


		return "lowfeerent.html";
	}

	@RequestMapping("/admin")
	public String getAdminConsole(Model boxToView) {
		// System.out.println(request);

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "admin.html";
	}

}
