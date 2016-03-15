package shcherbakov.sergey.onlineLibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name = "Genres")
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idGenre")
	private Integer idGenre;

	@Column( name = "genre")
	private String genre;
	
	@OneToMany(mappedBy = "bookGenre")
	private Set<Book> books = new HashSet<Book>();

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
