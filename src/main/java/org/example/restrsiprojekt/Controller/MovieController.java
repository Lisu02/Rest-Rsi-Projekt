package org.example.restrsiprojekt.Controller;


import org.example.restrsiprojekt.Controller.Exception.MovieNotFoundException;
import org.example.restrsiprojekt.DAO.ActorDao;
import org.example.restrsiprojekt.DAO.ActorDaoImpl;
import org.example.restrsiprojekt.DAO.MovieDao;
import org.example.restrsiprojekt.DAO.MovieDaoImpl;
import org.example.restrsiprojekt.model.Movie;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping("/movie")
public class MovieController {

    private final MovieDao movieDao;
    private final ActorDao actorDao;

    public MovieController(){
        this.movieDao = MovieDaoImpl.getMovieDaoInstance();
        this.actorDao = ActorDaoImpl.getActorDaoInstance();
    }

    @PostMapping()
    public EntityModel<Movie> addMovie(@RequestBody Movie movie) {
        // Logika dodawania filmu
        Movie savedMovie = movieDao.save(movie);
        return EntityModel.of(savedMovie,
                linkTo(methodOn(MovieController.class).addMovie(savedMovie)).withSelfRel(),
                linkTo(methodOn(MovieController.class).getMovie(savedMovie.getId())).withRel("find"),
                linkTo(methodOn(MovieController.class).getMovieList()).withRel("all")
        );
    }
    @GetMapping("/{id}")
    public EntityModel<Movie> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieDao.findById(id);
        Movie getMovie = movie.orElseThrow(() -> new MovieNotFoundException(id));
        return EntityModel.of(getMovie,
                linkTo(methodOn(MovieController.class).getMovie(getMovie.getId())).withSelfRel(),
                linkTo(methodOn(MovieController.class).getMovieList()).withRel("all")
                );
    }
    @GetMapping()
    public CollectionModel<EntityModel<Movie>> getMovieList() {
        List<Movie> movieList = movieDao.findAll();

        List<EntityModel<Movie>> movies = movieList.stream()
                .map(movie -> EntityModel.of(movie,
                        linkTo(methodOn(MovieController.class).getMovie(movie.getId())).withSelfRel()
                        )
                ).toList();


        return CollectionModel.of(movies,
                linkTo(methodOn(MovieController.class).getMovieList()).withSelfRel());
    }

    //Schowane jest addActorToMovie bo musze lepiej wymyslic przekazywanie danych
    // i schowany jest getImage bo trzeba jakos lepiej zdjecia zwrócić

//    @PostMapping("/{actorId}/{movieId}")
//    public Movie addActorToMovie(@PathVariable Long actorId,@PathVariable Long movieId) {
//        if(movieDao.findById(movieId).isPresent()){
//            Movie movie = movieDao.findById(movieId).get();
//            if(actorDao.findById(actorId).isPresent()){
//                movie.getActorList().add(actorId);
//                movieDao.update(movie);
//                return movie;
//            }
//        }
//        return null;
//    }

//    public DataHandler getImage(@WebParam(name = "filepath") String filepath) {
//        if(filepath == null){
//            Logger log = Logger.getLogger(this.getClass().getName());
//            log.warning("getImage - filepath jest NULL");
//            return Movie.loadImageOrDefault("");
//        }
//        return Movie.loadImageOrDefault(filepath);
//    }
//
//    public DataHandler getMovieImage(@WebParam(name = "movieId") Long movieId) {
//        Optional<Movie> movieOptional = movieDao.findById(movieId);
//        if(movieOptional.isPresent()){
//            String imagePath = movieOptional.get().getImagePath();
//            return Movie.loadImageOrDefault(imagePath);
//        }else {
//            return Movie.loadImageOrDefault("");
//        }
//        //throw new RuntimeException("No movie at provided id is present");
//    }


}
