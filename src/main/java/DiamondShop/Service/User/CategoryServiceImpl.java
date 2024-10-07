package DiamondShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.ProductsDao;
import DiamondShop.Dto.ProductsDto;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<ProductsDto> GetDataProductsPaginate(int id, int start, int totalPage) {
		List<ProductsDto> productsDtos = productsDao.GetDataProductsPaginate(id, start, totalPage);
		return productsDtos;
	}

	@Override
	public List<ProductsDto> GetAllProductsByID(int id) {
		return productsDao.GetAllProductsByID(id);	
	}
}