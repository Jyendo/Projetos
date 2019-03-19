package org.casadocodigo.controller;

import javax.validation.Valid;

import org.casadocodigo.component.FileSaver;
import org.casadocodigo.dao.ProductsDAO;
import org.casadocodigo.models.BookType;
import org.casadocodigo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new ProductValidator());
//	}

	@Autowired
	private ProductsDAO productsDAO;

	@Autowired
	private FileSaver fileSaver;

	@GetMapping("/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}

	@GetMapping
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productsDAO.list());
		return modelAndView;
	}

	@GetMapping("/show/{id}")
	public ModelAndView show(@PathVariable("id") Long id) {

		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productsDAO.find(id);

		if (product == null)
			return new ModelAndView("notFound");

		modelAndView.addObject("product", product);
		return modelAndView;
	}

	@PostMapping(name = "saveProduct")
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			System.out.println("Erro ao cadastrar o produto!");
			return form(product);
		}
		System.out.println("Cadastrando o produto");
		System.out.println(summary.getName() + ";" + summary.getOriginalFilename());

		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		productsDAO.save(product);
		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return modelAndView;
	}
}
