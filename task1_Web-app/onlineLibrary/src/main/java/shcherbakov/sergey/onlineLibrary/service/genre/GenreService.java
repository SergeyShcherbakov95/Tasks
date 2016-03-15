package shcherbakov.sergey.onlineLibrary.service.genre;

import java.util.List;

import shcherbakov.sergey.onlineLibrary.model.Genre;

public interface GenreService {
	public List<Genre> listGenres();
	
	public Genre getGenre(String genre);
}
