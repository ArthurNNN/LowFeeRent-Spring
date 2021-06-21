package com.lfr.rental;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.javafaker.Faker;
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
		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "admin.html";
	}

	@RequestMapping("/fillApt")
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

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "redirect:/admin";
	}

	@RequestMapping("/fillPers")
	public String fillAllPersons(Model boxToView) {

		Faker faker = new Faker();

		System.out.print("\n---------------- Add persons: ----------------");
		int n = 1;
		while (n <= 10) {
			Person person = new Person();
			person.setName(faker.name().firstName());
			person.setSurname(faker.name().lastName());
			person.setEmail(faker.internet().emailAddress());
			System.out.print("\n#" + n + " ");
			System.out.print(person);
			n++;
			person = personRepository.save(person);
			System.out.print("\n#" + n + " ");
			System.out.print(person);
			n++;

		}

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "redirect:/admin";
	}

//	public class Booking {
//
//		@Id
//		String id;
//		String personId;
//		String aptId;
//		LocalDate checkin;
//		LocalDate checkout;
//		int nights;
//		int amount;
//
//		public Booking() {
//			super();
//			this.setId();
//		}
//
//		public Booking(Person person, Apartment apt, LocalDate checkin, LocalDate checkout, int amount) {
//			super();
//			this.setId();
//			this.personId = person.getId();
//			this.aptId = apt.getId();
//			this.checkin = checkin;
//			this.checkout = checkout;
//			this.nights = Period.between(checkin, checkout).getDays();
//			this.amount = amount;
//		}

	@RequestMapping("/fillBook")
	public String fillInBookings(Model boxToView) {
		System.out.print("\n---------------- Adding 10 bookings: ----------------");
		int n = 1;
		while (n <= 10) {

			Optional<Person> personGen = personRepository.findById(Utils.randRange(1, (int) personRepository.count()));
			Optional<Apartment> apartmentGen = apartmentRepository
					.findById(Utils.randRange(1, (int) apartmentRepository.count()));

			if (apartmentGen.isPresent() && personGen.isPresent()) {
				HashMap<LocalDate, LocalDate> openDates = apartmentGen.get().getOpenDates();
				HashMap.Entry<LocalDate, LocalDate> firstDateHM = openDates.entrySet().stream().findFirst().get();
				LocalDate chekingDate = firstDateHM.getKey();
				LocalDate chekoutDate = firstDateHM.getValue();
				
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
