package shcherbakov.sergey.onlineLibrary.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shcherbakov.sergey.onlineLibrary.model.Book;
import shcherbakov.sergey.onlineLibrary.model.User;
import shcherbakov.sergey.onlineLibrary.service.book.BookService;
import shcherbakov.sergey.onlineLibrary.service.user.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(UserService userService){
        this.userService = userService;
    }
	
	private BookService bookService;
	
	@Autowired(required=true)
    @Qualifier(value="bookService")
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		return "register";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public String errorLogin(Model model){
		model.addAttribute("error", "*Incorrect email or password");
		
		return "login";
	}
	
	@RequestMapping(value = "/addToFavourite/{idBook}", method = RequestMethod.GET)
    public String book(@PathVariable String idBook, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    
	    this.userService.addToFavourite(email, idBook);
	    
		model.addAttribute("book", this.bookService.getBook(Integer.valueOf(idBook)));
		
		return "book";
    }
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String surname = request.getParameter("surname");
		String name = request.getParameter("name");
		
		User user = new User(email, password, surname, name);
		
		this.userService.addUser(user);
		
		return "redirect:" + "/";
	}
	
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
	    
		model.addAttribute("user", this.userService.find(email));
		model.addAttribute("books", this.userService.listFavourites(email));
		model.addAttribute("book", new Book());
		
		return "userInfo";
	}
}
