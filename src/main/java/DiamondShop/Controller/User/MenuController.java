package DiamondShop.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import DiamondShop.Dao.MenusDao;
import DiamondShop.Entity.Menus;

@Controller
public class MenuController extends BaseController {
	@Autowired
	private MenusDao menusDao = new MenusDao();

    @RequestMapping()
    public ModelAndView showHomePage() {
        List<Menus> menus = menusDao.GetDataMenus();
        _mvShare.setViewName("layouts/user/header");
        _mvShare.addObject("menus", menus);
        return _mvShare;
    }
}