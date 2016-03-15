package shcherbakov.sergey.onlineLibrary.dao.genre;

import java.util.List;

import shcherbakov.sergey.onlineLibrary.model.Genre;

public interface GenreDao {
	public List<Genre> listGenres();
	
	public Genre getGenre(String genre);
}
