package DiamondShop.Controller.User;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamondShop.Dto.CartDto;
import DiamondShop.Entity.Bill;
import DiamondShop.Entity.Users;
import DiamondShop.Service.User.BillServiceImpl;
import DiamondShop.Service.User.CartServiceImpl;

@Controller
public class CartController extends BaseController {
	@Autowired
	private CartServiceImpl cartServiceImpl = new CartServiceImpl();
	
	@Autowired
	private BillServiceImpl billServiceImpl = new BillServiceImpl();
	
	@RequestMapping(value = "gio-hang")
	public ModelAndView Index() {
		_mvShare.addObject("slides", _homeService.GetDataSlides());
		_mvShare.addObject("categories", _homeService.GetDataCategories());
		_mvShare.addObject("products", _homeService.GetDataProducts());
		_mvShare.setViewName("user/cart/list-cart");
		return _mvShare;
	}
	
	@RequestMapping(value = "AddCart/{id}")
	public String addCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.addCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "EditCart/{id}/{quantity}")
	public String EditCart(HttpServletRequest request, HttpSession session, @PathVariable int id, @PathVariable int quantity) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.editCart(id, quantity, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("Cart");
		if (cart == null) {
			cart = new HashMap<Integer, CartDto>();
		}
		cart = cartServiceImpl.deleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantityCart", cartServiceImpl.totalQuantity(cart));
		session.setAttribute("TotalPriceCart", cartServiceImpl.totalPrice(cart));
		return "redirect:" + request.getHeader("Referer");
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView Checkout(HttpServletRequest request, HttpSession session) {
		_mvShare.setViewName("user/bill/checkout");
		Bill bill = new Bill();
		Users login = (Users) session.getAttribute("Login");
		if (login != null) {
			bill.setAddress(login.getAddress());
			bill.setDisplay_name(login.getDisplay_name());
			bill.setUser(login.getUser());
		}
		_mvShare.addObject("bill", bill);
		return _mvShare;
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String CheckoutBill(HttpServletRequest request, HttpSession session, @ModelAttribute("bill") Bill bill) {
		Object totalQuantityObj = session.getAttribute("TotalQuantityCart");
		Object totalPriceObj = session.getAttribute("TotalPriceCart");

		if (totalQuantityObj != null && totalPriceObj != null) {
		    try {
		        bill.setQuantity(Integer.parseInt(totalQuantityObj.toString()));
		        bill.setTotal(Double.parseDouble(totalPriceObj.toString()));
		    } catch (NumberFormatException e) {
		        // Handle the exception (e.g., log it, show an error message)
		        e.printStackTrace();
		    }
		} else {
		    System.out.println("Session attributes for quantity or price are null");
		}
		if (billServiceImpl.AddBill(bill) > 0) {
			HashMap<Integer, CartDto> carts = (HashMap<Integer, CartDto>) session.getAttribute("Cart");
			billServiceImpl.AddBillDetail(carts);
		}
		session.removeAttribute("Cart");
		return "redirect:gio-hang";
	}
}