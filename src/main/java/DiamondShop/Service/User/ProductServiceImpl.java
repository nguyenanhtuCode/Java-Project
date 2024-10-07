package DiamondShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.ProductsDao;
import DiamondShop.Dto.ProductsDto;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	ProductsDao productsDao;
	
	@Override
	public ProductsDto GetProductByID(int id) {
		List<ProductsDto> listProducts = productsDao.GetProductByID(id);
		return listProducts.get(0);
	}

	@Override
	public List<ProductsDto> GetProductByIDCategory(int id) {
		return productsDao.GetAllProductsByID(id);
	}
}