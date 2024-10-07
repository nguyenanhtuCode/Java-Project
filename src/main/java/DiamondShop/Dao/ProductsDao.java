package DiamondShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamondShop.Dto.ProductsDto;
import DiamondShop.Dto.ProductsDtoMapper;

@Repository
public class ProductsDao extends BaseDao {
	private final boolean YES = true;
	private final boolean NO = false;

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("    p.id as id_product, ");
		sql.append("    p.id_category, ");
		sql.append("    p.sizes, ");
		sql.append("    p.name, ");
		sql.append("    p.price, ");
		sql.append("    p.sale, ");
		sql.append("    p.title, ");
		sql.append("    p.highlight, ");
		sql.append("    p.new_product, ");
		sql.append("    p.detail, ");
		sql.append("    MAX(c.id) as id_color, ");
		sql.append("    MAX(c.name) as name_color, ");
		sql.append("    MAX(c.code) as code_color, ");
		sql.append("    MAX(c.img) as img, ");
		sql.append("    p.created, ");
		sql.append("    p.updated ");
		sql.append("FROM ");
		sql.append("    diamond_shop.products as p ");
		sql.append("INNER JOIN ");
		sql.append("    diamond_shop.colors as c ");
		sql.append("    ON p.id = c.id_product ");
		return sql;
	}

	private String SqlProducts(boolean newProduct, boolean highlight) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");

		if (highlight) {
			sql.append("AND ");
			sql.append("    p.highlight = true ");
		}

		if (newProduct) {
			sql.append("AND ");
			sql.append("    p.new_product = true ");
		}

		sql.append("GROUP BY ");
		sql.append("    p.id, c.id_product ");
		sql.append("ORDER BY ");
		sql.append("    RAND() ");

		if (highlight) {
			sql.append("LIMIT 9 ");
		}

		if (newProduct) {
			sql.append("LIMIT 12 ");
		}
		return sql.toString();
	}
	
	private StringBuffer SqlProductsByID(int id) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ");
		sql.append("    id_category = " + id + " ");
		sql.append("GROUP BY ");
		sql.append("    p.id, c.id_product ");
		return sql;
	}

	private String SqlProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sql = SqlProductsByID(id);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductsDto> GetAllProductsByID(int id) {
		String sql = SqlProductsByID(id).toString();
		List<ProductsDto> listProducts = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return listProducts;
	}

	public List<ProductsDto> GetDataProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sqlGetDataByID = SqlProductsByID(id);
		List<ProductsDto> listProductsByID = _jdbcTemplate.query(sqlGetDataByID.toString(), new ProductsDtoMapper());
		List<ProductsDto> listProducts = new ArrayList<ProductsDto>();
		if (listProductsByID.size() > 0) {
			String sql = SqlProductsPaginate(id, start, totalPage);
			listProducts = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		}
		return listProducts;
	}

	public List<ProductsDto> GetDataProducts() {
		String sql = SqlProducts(YES, NO);
		List<ProductsDto> listProducts = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return listProducts;
	}

	private String SqlProductByID(int id) {
		StringBuffer sql = SqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND p.id = " + id + " ");
		sql.append("LIMIT 1 ");
		return sql.toString();
	}

	public List<ProductsDto> GetProductByID(int id) {
		String sql = SqlProductByID(id);
		List<ProductsDto> listProduct = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return listProduct;
	}

	public ProductsDto FindProductByID(int id) {
		String sql = SqlProductByID(id);
		ProductsDto product = _jdbcTemplate.queryForObject(sql, new ProductsDtoMapper());
		return product;
	}
}