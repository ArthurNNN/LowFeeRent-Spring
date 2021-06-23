package com.lfr.rental;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.javafaker.Faker;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@RequestMapping("/allPersons")
	public String getAllPersons(Model boxToView) {

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "person.html";
	}

	@RequestMapping("/fillIn10Persons")
	public String fillAllPersons(Model boxToView) {

		Faker faker = new Faker();

		System.out.print("\n---------------- Add persons: ----------------");
		int n = 1;
		while (n <= 10) {
			Person person = new Person();
			person.setName(faker.name().firstName());
			person.setSurname(faker.name().lastName());
			person.setEmail(faker.internet().emailAddress());

			personRepository.save(person);
			System.out.print("\n#" + n + " ");
			System.out.print(person);
			n++;

		}

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "redirect:/person/allPersons";
	}

	// -----------------------delete----------------------------------
	@RequestMapping("/deletePerson")
	public String removePerson(int id, Model model) {

		// System.out.println("inside removePerson" + id);
		Optional<Person> personFound = personRepository.findById(id);

		// System.out.println("find inside removePerson" + personFound.get());

		if (personFound.isPresent()) {

			personRepository.deleteById(id);
			model.addAttribute("message", "done");
			model.addAttribute("personDeleted", personFound.get());
		}

		else {
			model.addAttribute("message", "error");
		}

		// System.out.println("finishing removePerson" + id);
		return "deletedperson.html";
	}

	@RequestMapping("/deleteAllPersons")
	public String deleteAllPersons() {

		personRepository.deleteAll();

		return "redirect:/person/allPersons";

	}

}
