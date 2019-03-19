package org.casadocodigo.controller;

import java.math.BigDecimal;

import org.casadocodigo.component.ShoppingCart;
import org.casadocodigo.dto.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping
	public ModelAndView items() {
		return new ModelAndView("shoppingCart/items");
	}

	@GetMapping("/success")
	public ModelAndView success() {
		return new ModelAndView("payment/success");
	}

	@GetMapping("/error")
	public ModelAndView error() {
		return new ModelAndView("payment/error");
	}

	@PostMapping("/checkout")
	public ModelAndView checkout(RedirectAttributes redirectAttributes) {
		BigDecimal total = shoppingCart.getTotal();

		String uriToPay = "http://book-payment.herokuapp.com/payment";
		String response = "";

		try {

			response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			redirectAttributes.addFlashAttribute("sucesso", response);

			return new ModelAndView("redirect:success");

		} catch (HttpClientErrorException exception) {
			redirectAttributes.addFlashAttribute("error", exception.getResponseBodyAsString());
			return new ModelAndView("redirect:error");
		}
	}
}