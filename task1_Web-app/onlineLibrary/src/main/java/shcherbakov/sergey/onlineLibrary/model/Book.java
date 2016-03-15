package shcherbakov.sergey.onlineLibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "Books")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idBook")
	private Integer idBook;
	
	@Column( name = "title")
	private String title;
	
	@Column( name = "description")
	private String description;
	
	@Column( name = "pathToBook")
	private String pathToBook;
	
	@Column( name = "author")
	private String author;
	
	@ManyToOne
    @JoinColumn(name="idGenre", nullable=false)
	private Genre bookGenre;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "favouritesBooks")
	private Set<User> users = new HashSet<User>();
	
	public Book(){
		//NOP
	}

	public Book(String title, String description, String pathToBook, String author, Genre bookGenre) {
		super();
		this.title = title;
		this.description = description;
		this.pathToBook = pathToBook;
		this.author = author;
		this.bookGenre = bookGenre;
	}

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPathToBook() {
		return pathToBook;
	}

	public void setPathToBook(String pathToBook) {
		this.pathToBook = pathToBook;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Genre getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(Genre bookGenre) {
		this.bookGenre = bookGenre;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
