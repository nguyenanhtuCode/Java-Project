package DiamondShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.CategoriesDao;
import DiamondShop.Dao.MenusDao;
import DiamondShop.Dao.ProductsDao;
import DiamondShop.Dao.SlidesDao;
import DiamondShop.Dto.ProductsDto;
import DiamondShop.Entity.Categories;
import DiamondShop.Entity.Menus;
import DiamondShop.Entity.Slides;

@Service
public class HomeServiceImpl implements IHomeService {
	@Autowired
	private SlidesDao slidesDao;
	@Autowired
	private CategoriesDao categoriesDao;
	@Autowired
	private MenusDao menusDao;
	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<Slides> GetDataSlides() {	
		return slidesDao.GetDataSlides();
	}
	
	@Override
	public List<Categories> GetDataCategories() {
		return categoriesDao.GetDataCategories();
	}
	
	@Override
	public List<Menus> GetDataMenus() {
		return menusDao.GetDataMenus();
	}

	@Override
	public List<ProductsDto> GetDataProducts() {
		List<ProductsDto> listProducts = productsDao.GetDataProducts();
		return listProducts;
	}
}