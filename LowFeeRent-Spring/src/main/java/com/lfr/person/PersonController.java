package com.lfr.person;

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

		return "persons.html";
	}

	@RequestMapping("/fillPersons")
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

		}

		boxToView.addAttribute("personListfromControllerAndDB", personRepository.findAll());

		return "persons.html";
	}

}
