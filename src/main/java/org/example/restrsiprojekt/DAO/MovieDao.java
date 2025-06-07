package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    public Movie save(Movie movie);
    public Movie update(Movie movie);
    public void delete(Movie movie);
    public void delete(Long id);
    public Optional<Movie> findById(Long id);
    public List<Movie> findAll();

}
