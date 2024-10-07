package DiamondShop.Controller.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DiamondShop.Entity.Users;
import DiamondShop.Service.User.AccountServiceImpl;

@Controller
public class UserController extends BaseController {
	@Autowired
	AccountServiceImpl accountServiceImpl = new AccountServiceImpl();

	@RequestMapping(value = "dang-ki", method = RequestMethod.GET)
	public ModelAndView Register() {
		_mvShare.setViewName("user/account/register");
		_mvShare.addObject("user", new Users());
		return _mvShare;
	}

	@RequestMapping(value = "/dang-ki", method = RequestMethod.POST)
	public ModelAndView CreateAccount(@ModelAttribute("user") Users users) {
		int count = accountServiceImpl.AddAccount(users);
		if (count > 0) {
			_mvShare.addObject("status", "Đăng kí tài khoản thành công!");
		} else {
			_mvShare.addObject("status", "Đăng kí tài khoản thất bại!");
		}
		_mvShare.setViewName("user/account/register");
		return _mvShare;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.POST)
	public ModelAndView Login(HttpSession session, @ModelAttribute("user") Users users) {
		users = accountServiceImpl.CheckAccount(users);
		if (users != null) {
			_mvShare.setViewName("redirect:trang-chu");
			session.setAttribute("Login", users);
		} else {
			_mvShare.addObject("login", "Đăng nhập tài khoản thất bại!");
		}
		return _mvShare;
	}

	@RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
	public String Login(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("Login");
		return "redirect:" + request.getHeader("Referer");
	}
}