package DiamondShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dto.ProductsDto;
import DiamondShop.Entity.Categories;
import DiamondShop.Entity.Menus;
import DiamondShop.Entity.Slides;

@Service
public interface IHomeService {
	@Autowired
	public List<Slides> GetDataSlides();
	public List<Categories> GetDataCategories();
	public List<Menus> GetDataMenus();
	public List<ProductsDto> GetDataProducts();
}