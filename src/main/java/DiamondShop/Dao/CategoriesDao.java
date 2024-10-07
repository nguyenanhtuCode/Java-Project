package DiamondShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamondShop.Entity.Categories;
import DiamondShop.Entity.MapperCategories;

@Repository
public class CategoriesDao extends BaseDao {

	public List<Categories> GetDataCategories() {
		List<Categories> list = new ArrayList<Categories>();
		String sql = "SELECT * FROM diamond_shop.categories";
		list = _jdbcTemplate.query(sql, new MapperCategories());
		return list;
	}
}