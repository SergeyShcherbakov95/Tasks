package shcherbakov.sergey.onlineLibrary.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		user.setUserRole("USER");
		
		session.save(user);
	}

	@Override
	public User find(String email) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createQuery("from User where email = :email").setString("email", email).uniqueResult();
		return user;
	}

	@Override
	public List<Book> listFavourites(String email){
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.createQuery("from User where email = :email").setString("email", email).uniqueResult();
		
		List<Book> books = new ArrayList<Book>();
		books.addAll(user.getFavouritesBooks());
		
		return books;
	}

	@Override
	public void addToFavourite(String email, String idBook) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createQuery("from User where email = :email").setString("email", email).uniqueResult();
		Book book = (Book) session.createQuery("from Book where idBook = :idBook").setString("idBook", idBook).uniqueResult();
		
		user.getFavouritesBooks().add(book);
		
		session.save(user);
	}
}
