package shcherbakov.sergey.onlineLibrary.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shcherbakov.sergey.onlineLibrary.dao.user.UserDao;
import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	 
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Transactional
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
    
    @Transactional
	@Override
	public User find(String email) {
		return userDao.find(email);
	}

    @Transactional
	@Override
	public List<Book> listFavourites(String email) {
		return userDao.listFavourites(email);
	}

    @Transactional
	@Override
	public void addToFavourite(String email, String idBook) {
		this.userDao.addToFavourite(email, idBook);
	}
}
