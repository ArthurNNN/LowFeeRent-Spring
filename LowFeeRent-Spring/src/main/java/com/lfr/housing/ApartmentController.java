package com.lfr.housing;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	@Autowired
	ApartmentRepository apartmentRepository;

	@RequestMapping("/allApartments")
	public String getAllApartments(Model boxToView) {

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());

		return "apartments.html";
	}

	@RequestMapping("/fillApartments")
	public String fillApartments(Model boxToView) {
		Faker faker = new Faker();
		System.out.print("\n---------------- Adding apartments: ----------------");
		int n = 1;
		while (n <= 10) {
			Apartment apartment = new Apartment(Utils.randRange(8, 25) * 100, Utils.randRange(10, 20) * 10,
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

		return "apartments.html";
	}

}
