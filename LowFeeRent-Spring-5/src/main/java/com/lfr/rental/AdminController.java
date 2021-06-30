package com.lfr.rental;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.lfr.utils.Utils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ApartmentRepository apartmentRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	BookingRepository bookingRepository;

	@RequestMapping("/")
	public String getAdminConsole(Model boxToView) {
		// System.out.println(request);

		boxToView.addAttribute("apartmentListfromControllerAndDB", apartmentRepository.findAll());

		return "admin.html";
	}

	




	@RequestMapping("/fillBook")
	public String fillInBookings(Model boxToView) {
		System.out.print("\n---------------- Adding 10 bookings: ----------------");
		int n = 1;
		while (n <= 10) {

			Optional<Person> personGen = personRepository.findById(Utils.randRange(1, (int) personRepository.count()));
			Optional<Apartment> apartmentGen = apartmentRepository
					.findById(Utils.randRange(1, (int) apartmentRepository.count()));

			if (apartmentGen.isPresent() && personGen.isPresent()) {
				List<LocalDate> openDates = apartmentGen.get().getOpenDates();
				LocalDate chekingDate = openDates.get(0);
				LocalDate chekoutDate = openDates.get(1);
				
				Booking booking = new Booking( personGen.get(), apartmentGen.get(),
						chekingDate, chekoutDate, Utils.randRange(8, 10) * 10);
				

				booking = bookingRepository.save(booking);
				System.out.print("\n#" + n + " ");
				System.out.print(booking);
				n++;
				
			}


		}

		boxToView.addAttribute("bookingListfromControllerAndDB", bookingRepository.findAll());

		return "redirect:/admin";
	}

}
