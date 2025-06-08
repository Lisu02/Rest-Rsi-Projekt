package org.example.restrsiprojekt.Controller.Exception;

public class ShowingNotFoundException extends RuntimeException{
    public ShowingNotFoundException(Long id){
        super("Showing with id: " + id + " not found");
    }
}
