package shcherbakov.sergey.onlineLibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "idUser")
	private Integer idUser;

	@Column( name = "email")
	private String email;
	
	@Column( name = "password")
	private String userPassword;
	
	@Column( name = "surname")
	private String surname;
	
	@Column( name = "name")
	private String name;
	
	@Column( name = "role")
	private String userRole;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "Favourites", joinColumns = { 
			@JoinColumn(name = "idUser", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idBook", nullable = false, updatable = false) })
	private Set<Book> favouritesBooks = new HashSet<Book>();
	
	public User(){
		//NOP
	}
	
	public User(String email, String userPassword, String surname, String name){
		this.email = email;
		this.userPassword = userPassword;
		this.surname = surname;
		this.name = name;
	}
	
	public User(String email, String userPassword, String surname, String name, String userRole){
		this.email = email;
		this.userPassword = userPassword;
		this.surname = surname;
		this.name = name;
		this.userRole = userRole;
	}
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getFavouritesBooks() {
		return favouritesBooks;
	}

	public void setFavouritesBooks(Set<Book> favouritesBooks) {
		this.favouritesBooks = favouritesBooks;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
