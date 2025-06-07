package org.example.restrsiprojekt.Controller;


import org.example.restrsiprojekt.DAO.ActorDao;
import org.example.restrsiprojekt.DAO.ActorDaoImpl;
import org.example.restrsiprojekt.DAO.MovieDao;
import org.example.restrsiprojekt.DAO.MovieDaoImpl;
import org.example.restrsiprojekt.model.Movie;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movie")
public class MovieController {

    private final MovieDao movieDao = MovieDaoImpl.getMovieDaoInstance();
    private final ActorDao actorDao = ActorDaoImpl.getActorDaoInstance();

    @PostMapping()
    public Movie addMovie(@RequestBody Movie movie) {
        // Logika dodawania filmu
        return movieDao.save(movie);
    }
    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieDao.findById(id);
        return movie.get();
    }
    @GetMapping()
    public List<Movie> getMovieList() {
        return movieDao.findAll();
    }
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
