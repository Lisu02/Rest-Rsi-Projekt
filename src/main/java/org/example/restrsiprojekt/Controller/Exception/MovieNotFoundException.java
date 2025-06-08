package org.example.restrsiprojekt.Controller.Exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long id){
        super("Movie with ID " + id + " not found.");
    }
}
