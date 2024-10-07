package DiamondShop.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import DiamondShop.Entity.MapperUsers;
import DiamondShop.Entity.Users;

@Repository
public class UsersDao extends BaseDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UsersDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Users findByEmailAndPassword(String email, String password) {
		String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email, password }, new MapperUsers());
	}

	public int AddAccount(Users users) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO diamond_shop.users(user, password, display_name, address) VALUES ('" + users.getUser()
				+ "', '" + users.getPassword() + "', '" + users.getDisplay_name() + "', '" + users.getAddress()
				+ "');");
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}

	public Users GetUsersByAccount(Users users) {
		String sql = "SELECT * FROM diamond_shop.users WHERE user = '" + users.getUser() + "'";
		Users user = _jdbcTemplate.queryForObject(sql, new MapperUsers());
		return user;
	}
}