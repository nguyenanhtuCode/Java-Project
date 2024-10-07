package DiamondShop.Service.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.UsersDao;
import DiamondShop.Entity.Users;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	UsersDao usersDao;
	
	@Override
	public int AddAccount(Users users) {
		users.setPassword(BCrypt.hashpw(users.getPassword(), BCrypt.gensalt(12)));
		return usersDao.AddAccount(users);
	}

	@Override
	public Users CheckAccount(Users users) {
		String password = users.getPassword();
		users = usersDao.GetUsersByAccount(users);
		if (users != null ) {
			if (BCrypt.checkpw(password, users.getPassword())) {
				return users;
			} else {
				return null;
			}
		}
		return null;
	}
}