package org.example.restrsiprojekt.Controller.Exception;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(Long id){
        super("Actor on id: " + id + " not found");
    }
}
