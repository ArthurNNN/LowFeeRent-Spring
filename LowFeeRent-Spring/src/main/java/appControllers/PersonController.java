package appControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
