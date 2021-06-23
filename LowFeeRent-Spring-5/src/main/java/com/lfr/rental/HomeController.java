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
public class HomeController {

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	RequestRepository requestRepository;


	@RequestMapping("/")
	public String getApartments( Model boxToView) {
		//System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
		

		return "lowfeerent.html";
	}

	@RequestMapping("/filter")
	public String filterApartments(Request request, Model boxToView) {
		System.out.println(request);
		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
//		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findByPriceAndRooms(request.priceMax, request.roomsMin));
		//requestRepository.save(request);

		return "redirect:/";
	}
	
	@RequestMapping("/admin")
	public String getAdminConsole( Model boxToView) {
		//System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
		

		return "admin.html";
	}

	

}
