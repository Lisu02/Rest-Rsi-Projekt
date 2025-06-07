package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.Movie;
import org.example.restrsiprojekt.model.MovieType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    private MovieDaoImpl(){}

    public static MovieDaoImpl getMovieDaoInstance(){
        if(movieDao == null){
            movieDao = new MovieDaoImpl();

            Movie movie1 = new Movie(movieDao.counter++, "Inception", "Christopher Nolan", "2010-07-16", "A mind-bending thriller.", MovieType.SCIENCE_FICTION, "/images/diune.png");
            movie1.setActorList(List.of(1L,15L));
            Movie movie2 = new Movie(movieDao.counter++, "The Dark Knight", "Christopher Nolan", "2008-07-18", "Batman faces the Joker.", MovieType.ACTION, "/images/bullet_train.png");
            movie2.setActorList(List.of(2L,3L,4L));
            Movie movie3 = new Movie(movieDao.counter++, "The Godfather", "Francis Ford Coppola", "1972-03-24", "Crime drama classic.", MovieType.THRILLER, "/images/dumb_and_dumber.png");
            movie3.setActorList(List.of(5L,6L,7L));
            Movie movie4= new Movie(movieDao.counter++, "Pulp Fiction", "Quentin Tarantino", "1994-10-14", "Classic with interwoven stories.", MovieType.CRIME, "/images/shrek.png");
            movie4.setActorList(List.of(8L,9L));
            Movie movie5 = new Movie(movieDao.counter++, "The Matrix", "The Wachowskis", "1999-03-31", "A hacker discovers the shocking truth.", MovieType.SCIENCE_FICTION, "/images/topgun.png");
            movie5.setActorList(List.of(10L,11L));
            Movie movie6 = (new Movie(movieDao.counter++, "Titanic", "James Cameron", "1997-12-19", "Romance on a doomed ship.", MovieType.R0MANCE, "/images/ojciec_chrzesnty.jpg"));
            movie6.setActorList(List.of(12L,13L,14L));

            movieDao.save(movie1);
            movieDao.save(movie2);
            movieDao.save(movie3);
            movieDao.save(movie4);
            movieDao.save(movie5);
            movieDao.save(movie6);
        }
        return movieDao;
    }

    private static MovieDaoImpl movieDao;
    private HashMap<Long, Movie> database = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Movie save(Movie movie) {
        if(movie.getActorList() == null){
            if(movie.getActorList().isEmpty()){
                throw new RuntimeException(
                        this.getClass().getName() +": Actor list for a movie is null or empty"
                );
            }
        }
        counter++;
        movie.setId(counter);
        database.put(counter,movie);
        return movie;
    }

    @Override
    public Movie update(Movie newMovie) {
        Long movieId = newMovie.getId();
        if(movieId == null){
            throw new RuntimeException("Updated movie id is null");
        }
        Movie databaseMovie = database.get(movieId);
        if(databaseMovie == null){
            throw new RuntimeException("Movie for update does not exist");
        }
        database.replace(movieId, newMovie);
        return newMovie;
    }

    @Override
    public void delete(Movie movie) {
        database.remove(movie.getId());
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return new LinkedList<>(database.values());
    }


}
