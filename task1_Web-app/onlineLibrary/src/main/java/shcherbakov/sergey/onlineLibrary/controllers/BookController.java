package shcherbakov.sergey.onlineLibrary.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.model.Genre;
import shcherbakov.sergey.onlineLibrary.service.book.BookService;
import shcherbakov.sergey.onlineLibrary.service.genre.GenreService;

@Controller
public class BookController {

	private BookService bookService;
	
	@Autowired(required=true)
    @Qualifier(value="bookService")
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
	
	private GenreService genreService;
	
	@Autowired(required=true)
    @Qualifier(value="genreService")
    public void setGenreService(GenreService genreService){
        this.genreService = genreService;
    }
	
	private ServletContext servletContext;
	
	@Autowired
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;	 
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getBooks(HttpServletRequest request, HttpServletResponse response, Model model) {
		String text = request.getParameter("text");
		String genre = request.getParameter("genres");
		
		List<Book> books;
		if((text == null || text.equals("")) && (genre == null || genre.equals("all")))
			books = this.bookService.listBooks();
		else
			books = this.bookService.listBooksByParameters(text, genre);
		
		model.addAttribute("listGenres", this.genreService.listGenres());
		
		model.addAttribute("books", books);
		model.addAttribute("book", new Book());
        
		return "books";
    }
	
	@RequestMapping(value = "/book/{idBook}", method = RequestMethod.GET)
    public String book(@PathVariable String idBook, Model model) {
		model.addAttribute("book", this.bookService.getBook(Integer.valueOf(idBook)));
        
		return "book";
    }
	
	@RequestMapping(value = "/delete/{idBook}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable String idBook, Model model) {
		this.bookService.deleteBook(Integer.valueOf(idBook));
        
		return "redirect:" + "/";
    }
	
	@RequestMapping(value = "/editBook/{idBook}", method = RequestMethod.GET)
    public String editBookPage(@PathVariable String idBook, Model model) {
		model.addAttribute("book", this.bookService.getBook(Integer.valueOf(idBook)));
		model.addAttribute("listGenres", this.genreService.listGenres());
		model.addAttribute("genre", new Genre());
        
		return "editBook";
    }
	
	@RequestMapping(value = "/editBook/{idBook}", method = RequestMethod.POST)
    public String editBook(@PathVariable String idBook, @RequestParam(value = "file") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String genre = request.getParameter("genres");

		Genre genreObject = this.genreService.getGenre(genre);
		
		String pathToBookFile = "oldPath";
		if(!file.isEmpty())
			pathToBookFile = saveBookFile(file, title, author);
        
		Book book = new Book(title, description, pathToBookFile, author, genreObject);
		book.setIdBook(Integer.valueOf(idBook));
		
		this.bookService.editBook(book);
		
		return "redirect:" + "/book/" + idBook;
    }
	
	@RequestMapping(value = "/addBookPage", method = RequestMethod.GET)
    public String addBookPage(Model model) {
		model.addAttribute("listGenres", this.genreService.listGenres());
		model.addAttribute("genre", new Genre());
        
		return "addBook";
    }
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(@RequestParam(value = "file") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String genre = request.getParameter("genres");

		Genre genreObject = this.genreService.getGenre(genre);
		
		String pathToBookFile = saveBookFile(file, title, author);
		
		Book book = new Book(title, description, pathToBookFile, author, genreObject);
		
		this.bookService.addBook(book);
        
		return "redirect:" + "/";
    }
	
	public String saveBookFile(MultipartFile file, String title, String author){
		String[] fileContentType = file.getContentType().split("/");
		String pathToImage = "/resources/books/" + title + "_" + author + "." +  fileContentType[fileContentType.length - 1];
		if (!file.isEmpty()) {
			File bookFile = new File(servletContext.getRealPath("/") + pathToImage);
			byte[] bytes;
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(bookFile))){
				bytes = file.getBytes();
	            stream.write(bytes);
	            stream.flush();
	            stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathToImage;
	}
}
