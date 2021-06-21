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
@RequestMapping("/apartment")
public class ApartmentController {

	@Autowired
	ApartmentRepository apartmentRepository;



	@RequestMapping("")
	public String getApartments( Model boxToView) {
		//System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());
		

		return "apartment.html";
	}

}
