package DiamondShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamondShop.Entity.MapperMenus;
import DiamondShop.Entity.Menus;

@Repository
public class MenusDao extends BaseDao {
	public List<Menus> GetDataMenus() {
		List<Menus> list = new ArrayList<Menus>();
		String sql = "SELECT * FROM diamond_shop.menus";
		list = _jdbcTemplate.query(sql, new MapperMenus());
		return list;
	}
}