package com.lfr.rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.github.javafaker.Faker;
import com.lfr.utils.Utils;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	@Autowired
	ApartmentRepository apartmentRepository;

	@RequestMapping("/fillin100apartments")
	public String fillApartments(Model boxToView) {
		Faker faker = new Faker();
		int n = 1;
		while (n <= 32) {
			Apartment apartment = new Apartment(Utils.randRange(8, 25) * 100, Utils.randRange(6, 18) * 10,
					Utils.randRange(1, 5), Utils.randRange(1, 3), faker.address().streetAddress(true));

			ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
			dates.add(LocalDate.of(2021, Utils.randRange(1, 4), Utils.randRange(1, 28)));
			dates.add(LocalDate.of(2021, Utils.randRange(3, 4), Utils.randRange(1, 28)));
			dates.add(LocalDate.of(2021, Utils.randRange(5, 8), Utils.randRange(1, 28)));
			dates.add(LocalDate.of(2021, Utils.randRange(7, 8), Utils.randRange(1, 28)));
			dates.add(LocalDate.of(2021, Utils.randRange(9, 12), Utils.randRange(1, 28)));
			dates.add(LocalDate.of(2021, Utils.randRange(10, 12), Utils.randRange(1, 28)));
			apartment.setOpenDates(dates);

			apartmentRepository.save(apartment);
			n++;
		}

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "redirect:/apartment/allApartments";
	}

	// -----------------------add----------------------------------
	@RequestMapping("/newApartment")
	public String newApartment() {

		return "newapartment.html";
	}

	@RequestMapping("/addApartment")
	public String insertApartment(Apartment apartment) {

		apartmentRepository.save(apartment);

		return "redirect:/apartment/allApartments";
	}

	@RequestMapping("/allApartments")
	public String getApartments(Model boxToView) {

		boxToView.addAttribute("apartmentList", apartmentRepository.findAll());

		return "apartment.html";
	}

	// -----------------------update----------------------------------
	@RequestMapping("/updateApartment")
	public String updateApartment(int id, Model model) {

		Optional<Apartment> apartmentFound = apartmentRepository.findById(id);

		if (apartmentFound.isPresent()) {

			model.addAttribute("apartmentfromController", apartmentFound.get());
			return "updateapartment";
		}

		else
			return "notfound.html";
	}

	@PostMapping("/replaceApartment/{idFromView}")
	public String replaceApartment(@PathVariable("idFromView") int id, Apartment apartment) {

		Optional<Apartment> apartmentFound = apartmentRepository.findById(id);

		if (apartmentFound.isPresent()) {

			if (apartment.getAddress() != null)
				apartmentFound.get().setAddress(apartment.getAddress());
			if (apartment.getPrice() != 0)
				apartmentFound.get().setPrice(apartment.getPrice());
			if (apartment.getArea() != 0)
				apartmentFound.get().setArea(apartment.getArea());
			if (apartment.getRooms() != 0)
				apartmentFound.get().setRooms(apartment.getRooms());
			if (apartment.getBathrooms() != 0)
				apartmentFound.get().setBathrooms(apartment.getBathrooms());

			apartmentRepository.save(apartmentFound.get());
			return "redirect:/apartment/allApartments";

		} else
			return "notfound.html";

	}

	// -----------------------detail----------------------------------
	@RequestMapping("/detailApartment")
	public String detailApartment(int id, Model model) {

		Optional<Apartment> apartmentFound = apartmentRepository.findById(id);

		if (apartmentFound.isPresent()) {

			model.addAttribute("apartmentfromController", apartmentFound.get());
			return "detailapartment";
		}

		else
			return "notfound.html";
	}

	// -----------------------delete----------------------------------
	@RequestMapping("/deleteApartment")
	public String removeApartment(int id, Model model) {

		// System.out.println("inside removeApartment" + id);
		Optional<Apartment> apartmentFound = apartmentRepository.findById(id);

		// System.out.println("find inside removeApartment" + apartmentFound.get());

		if (apartmentFound.isPresent()) {

			apartmentRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("apartmentDeleted", apartmentFound.get());
		}

		else {
			model.addAttribute("message", "error");
		}

		// System.out.println("finishing removeApartment" + id);
		return "deletedapartment.html";
	}

	@RequestMapping("/deleteAllApartments")
	public String deleteAllApartments() {

		apartmentRepository.deleteAll();

		return "redirect:/apartment/allApartments";

	}

}
