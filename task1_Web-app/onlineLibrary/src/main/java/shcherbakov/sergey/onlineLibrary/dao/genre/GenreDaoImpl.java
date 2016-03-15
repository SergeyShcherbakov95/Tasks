package shcherbakov.sergey.onlineLibrary.dao.genre;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import shcherbakov.sergey.onlineLibrary.model.Genre;

@Repository
public class GenreDaoImpl implements GenreDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> listGenres() {
		Session session = sessionFactory.getCurrentSession();
		List<Genre> genres = session.createQuery("from Genre").list();
		return genres;
	}

	@Override
	public Genre getGenre(String genre) {
		Session session = sessionFactory.getCurrentSession();
		Genre genreObject = (Genre)session.createQuery("from Genre where genre = :genre").setString("genre", genre).uniqueResult();
		return genreObject;
	}
}
