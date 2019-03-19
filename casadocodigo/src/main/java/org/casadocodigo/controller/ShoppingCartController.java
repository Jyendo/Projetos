package org.casadocodigo.controller;

import org.casadocodigo.component.ShoppingCart;
import org.casadocodigo.dao.ProductsDAO;
import org.casadocodigo.models.BookType;
import org.casadocodigo.models.Product;
import org.casadocodigo.models.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	@Autowired
	private ProductsDAO productsDAO;

	@Autowired
	private ShoppingCart shoppingCart;

	@PostMapping
	public ModelAndView add(Long productId, BookType bookType) {
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/produtos");
	}

	private ShoppingItem createItem(Long productId, BookType bookType) {
		Product product = productsDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String items() {
		return "shoppingCart/items";
	}
}