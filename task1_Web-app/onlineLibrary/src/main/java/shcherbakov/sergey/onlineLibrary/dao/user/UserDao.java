package shcherbakov.sergey.onlineLibrary.dao.user;

import java.util.List;

import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.model.User;

public interface UserDao {
	public void addUser(User user);
	
	public User find(String email);
	
	public List<Book> listFavourites(String email);
	
	public void addToFavourite(String email, String idBook);
}
