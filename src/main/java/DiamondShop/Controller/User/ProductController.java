package DiamondShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamondShop.Service.User.IProductService;

@Controller
public class ProductController extends BaseController {
	@Autowired
	private IProductService _iProductService;
	
	@RequestMapping(value = {"chi-tiet-san-pham/{id}"})
	public ModelAndView Index(@PathVariable int id) {
		_mvShare.setViewName("user/products/product");
		_mvShare.addObject("product", _iProductService.GetProductByID(id));
		int idCategory = _iProductService.GetProductByID(id).getId_category();
		_mvShare.addObject("productByIDCategory", _iProductService.GetProductByIDCategory(idCategory));
		return _mvShare;
	}
}