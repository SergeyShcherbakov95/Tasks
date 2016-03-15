package shcherbakov.sergey.onlineLibrary.service.book;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.dao.book.BookDao;;

@Service
public class BookServiceImpl implements BookService{

	private BookDao bookDao;
	 
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
	
    @Transactional
	@Override
	public List<Book> listBooks() {
		return this.bookDao.listBooks();
	}

    @Transactional
	@Override
	public void addBook(Book book) {
		this.bookDao.addBook(book);
	}
    
    @Transactional
	@Override
	public Book getBook(Integer idBook) {
    	return this.bookDao.getBook(idBook);
	}
    
    @Transactional
	@Override
	public List<Book> listBooksByParameters(String text, String genre) {
		return this.bookDao.listBooksByParameters(text, genre);
	}

    @Transactional
	@Override
	public void deleteBook(Integer idBook) {
		this.bookDao.deleteBook(idBook);
	}

    @Transactional
	@Override
	public void editBook(Book book) {
		this.bookDao.editBook(book);
	}
}
