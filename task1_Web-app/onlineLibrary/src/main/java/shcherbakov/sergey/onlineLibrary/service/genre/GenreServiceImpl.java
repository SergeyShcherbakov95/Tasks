package shcherbakov.sergey.onlineLibrary.service.genre;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shcherbakov.sergey.onlineLibrary.dao.genre.GenreDao;
import shcherbakov.sergey.onlineLibrary.model.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	private GenreDao genreDao;
	 
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
	
    @Transactional
    @Override
	public List<Genre> listGenres() {
		return this.genreDao.listGenres();
	}

    @Transactional
    @Override
    public Genre getGenre(String genre){
    	return this.genreDao.getGenre(genre);
    }
}
