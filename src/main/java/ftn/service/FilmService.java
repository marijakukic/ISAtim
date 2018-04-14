package ftn.service;

import ftn.model.Film;
import ftn.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Film findOne(Long id) {
        return filmRepository.findOne(id);
    }

    public Collection<Film> findAll() {
        return filmRepository.findAll();
    }

}
