package com.lfr.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/request")
public class RequestController {

	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/")
	public String getAllRequests(Model boxToView) {

		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());

		return "requests.html";
	}



}
