package org.example.restrsiprojekt.Controller;


import jakarta.websocket.server.PathParam;
import org.example.restrsiprojekt.Controller.Exception.MovieNotFoundException;
import org.example.restrsiprojekt.DAO.ActorDao;
import org.example.restrsiprojekt.DAO.ActorDaoImpl;
import org.example.restrsiprojekt.DAO.MovieDao;
import org.example.restrsiprojekt.DAO.MovieDaoImpl;
import org.example.restrsiprojekt.model.Movie;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    //todo: fix rzuca nullem
    @PostMapping("/{actorId}/{movieId}")
    public Movie addActorToMovie(@PathVariable Long actorId,@PathVariable Long movieId) {
        if(movieDao.findById(movieId).isPresent()){
            System.out.printf("movie is present");
            Movie movie = movieDao.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            if(actorDao.findById(actorId).isPresent()){
                System.out.printf("actor is present");
                movie.getActorList().add(actorId); //todo : wyjatek
                movieDao.update(movie);
                return movie;
            }
        }
        return null;
    }

    //TODO: naprawic nie dziala
    @GetMapping(value = "/movies/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getMovieImage(@PathParam(value = "filepath") String filepath) throws IOException {
        String imagePath = filepath;
        imagePath = imagePath.substring(1,imagePath.length());
        Path path = Paths.get(imagePath);
        byte[] image = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }

    @GetMapping(value = "/movies/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getMovieImage(@PathVariable Long id) throws IOException {
        Optional<Movie> movieOptional = movieDao.findById(id);
        String imagePath = movieOptional.map(Movie::getImage).orElse("shrek.png");
        imagePath = imagePath.substring(1,imagePath.length());
        Path path = Paths.get(imagePath);
        byte[] image = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }




}
