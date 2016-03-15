package shcherbakov.sergey.onlineLibrary.dao.book;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import shcherbakov.sergey.onlineLibrary.model.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooks() {
		Session session = sessionFactory.getCurrentSession();
		List<Book> books = session.createQuery("from Book").list();
		
		return books;
	}

	@Override
	public void addBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
	}

	@Override
	public Book getBook(Integer idBook) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, idBook);
		
		return book;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBooksByParameters(String text, String genre) {
		Session session = sessionFactory.getCurrentSession();
		List<Book> books;
		
		if(!(text == null || text.equals("")))
			 if(genre == null || genre.equals("all"))
				 books = (List<Book>)session.createQuery("from Book b where b.title like :text or b.author like :text").setString("text", "%" + text + "%").list();
			 else
				 books = (List<Book>)session.createQuery("select b from Book b INNER JOIN b.bookGenre bg where bg.genre = :genre and ( b.title like :text or b.author like :text )").setString("text", "%" + text + "%").setString("genre", genre).list();
		else
			books = (List<Book>) session.createQuery("select b from Book b INNER JOIN b.bookGenre bg where bg.genre = :genre").setString("genre", genre).list();
			
		return books;
	}

	@Override
	public void deleteBook(Integer idBook) {
		Session session = sessionFactory.getCurrentSession();
		
		Book book = (Book) session.get(Book.class, idBook);
		
		session.delete(book);
	}

	@Override
	public void editBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		
		Book oldBook = (Book) session.get(Book.class, book.getIdBook());
		
		oldBook.setTitle(book.getTitle());
		oldBook.setAuthor(book.getAuthor());
		oldBook.setDescription(book.getDescription());
		oldBook.setBookGenre(book.getBookGenre());
		if(!book.getPathToBook().equals("oldPath"))
			oldBook.setPathToBook(book.getPathToBook());
	}

}
