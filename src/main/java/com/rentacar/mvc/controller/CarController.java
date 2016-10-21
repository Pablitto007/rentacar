package com.rentacar.mvc.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.rentacar.mvc.doamin.Car;
import com.rentacar.mvc.doamin.Transaction;
import com.rentacar.mvc.service.CarService;
import com.rentacar.mvc.service.CustomerService;
import com.rentacar.mvc.service.TransactionService;
import com.rentacar.mvc.service.impl.AuthenticationService;

@Controller
@RequestMapping("/cars")
public class CarController {

	private CarService carService;
	private CustomerService customerService;
	private TransactionService transactionService;

	@Autowired
	public CarController(CarService carService, CustomerService customerService,
			TransactionService transactionService) {
		this.carService = carService;
		this.customerService = customerService;
		this.transactionService = transactionService;
	}

	@RequestMapping
	public String getAvailableCars(Model model) {
		model.addAttribute("cars", carService.getAvailableCars());
		return "cars";
	}

	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public String getProductById(Model model, @RequestParam String id) {
		model.addAttribute("car", carService.getCarById(Integer.parseInt(id)));
		model.addAttribute("username", AuthenticationService.getLoggedUsername());
		Transaction transaction = new Transaction();
		model.addAttribute("newTransaction", transaction);
		return "car";
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public String processCarTransaction(@ModelAttribute("newTransaction") Transaction transactionToPersist, Model model,
			@RequestParam String id, BindingResult result) {
		String currentUser = AuthenticationService.getLoggedUsername();
		Car car = carService.getCarById(Integer.parseInt(id));

		transactionToPersist.setCarId(car.getCarId());
		transactionToPersist.setCustomerId(customerService.getIdFromLoggedCustomer(currentUser));
		try {
			transactionService.saveTransaction(transactionToPersist);
		} catch (DataAccessException e) {
			result.rejectValue("start", "message", "Plesase verify dates");
			model.addAttribute("car", car);
			model.addAttribute("username", AuthenticationService.getLoggedUsername());
			return "car";
		}
		return "redirect:/cars";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddCarForm(Model model) {
		Car newCar = new Car();
		model.addAttribute("newCar", newCar);
		return "addCar";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddCarForm(@ModelAttribute("newCar") Car carToPersist, BindingResult result,
			HttpServletRequest request) {
		MultipartFile carImage = carToPersist.getCarImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/") + "resources/images/";
		if (carImage != null && !carImage.isEmpty()) {
			try {
				carImage.transferTo(new File(rootDirectory + carToPersist.getVin().trim() + ".jpg"));

			} catch (Exception e) {
				throw new RuntimeException("Adding car image failed", e);
			}
		}
		carService.addCar(carToPersist);
		return "redirect:/cars";
	}
}
