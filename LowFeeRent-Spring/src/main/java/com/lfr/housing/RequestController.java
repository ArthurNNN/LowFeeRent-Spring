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
@RequestMapping("/request")
public class RequestController {

	@Autowired
	RequestRepository requestRepository;

	@RequestMapping("/allRequests")
	public String getAllRequests(Model boxToView) {

		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());

		return "requests.html";
	}

	@RequestMapping("/fillRequests")
	public String fillRequests(Model boxToView) {
		System.out.print("---------------- Adding 10 requests: ----------------");
		int n = 1;
		while (n <= 10) {
			Request request = new Request(LocalDate.of(2021, Utils.randRange(5, 6), Utils.randRange(1, 28)),
					LocalDate.of(2021, Utils.randRange(6, 7), Utils.randRange(1, 28)), Utils.randRange(5, 18) * 100,
					Utils.getRandomBoolean() ? Utils.randRange(8, 10) * 10 : 0,
					Utils.getRandomBoolean() ? Utils.randRange(1, 5) : 0,
					Utils.getRandomBoolean() ? Utils.randRange(1, 3) : 0);
			request = requestRepository.save(request);
			System.out.print("\n#" + n + " ");
			System.out.print(request);
			n++;
		}

		boxToView.addAttribute("requestListfromControllerAndDB", requestRepository.findAll());

		return "requests.html";
	}

}
