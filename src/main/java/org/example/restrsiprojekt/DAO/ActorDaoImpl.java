package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.Actor;
import org.example.restrsiprojekt.model.Country;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ActorDaoImpl implements ActorDao{

    private ActorDaoImpl(){}

    public static ActorDaoImpl getActorDaoInstance() {
        if(actorDaoInstance == null){
            actorDaoInstance = new ActorDaoImpl();

            actorDaoInstance.save(new Actor("Leonardo", "DiCaprio", "1974-11-11", Country.USA));
            actorDaoInstance.save(new Actor("Joseph", "Gordon-Levitt", "1981-02-17", Country.USA));
            actorDaoInstance.save(new Actor("Marlon", "Brando", "1924-04-03", Country.USA));
            actorDaoInstance.save(new Actor("Al", "Pacino", "1940-04-25", Country.USA));
            actorDaoInstance.save(new Actor("Keanu", "Reeves", "1964-09-02", Country.CANADA));
            actorDaoInstance.save(new Actor("Carrie-Anne", "Moss", "1967-08-21", Country.CANADA));
            actorDaoInstance.save(new Actor("Kate", "Winslet", "1975-10-05", Country.UK));
            actorDaoInstance.save(new Actor("Christian", "Bale", "1974-01-30", Country.UK));
            actorDaoInstance.save(new Actor("Heath", "Ledger", "1979-04-04", Country.AUSTRALIA));
            actorDaoInstance.save(new Actor("John", "Travolta", "1954-02-18", Country.USA));
            actorDaoInstance.save(new Actor("Samuel L.", "Jackson", "1948-12-21", Country.USA));
            actorDaoInstance.save(new Actor("Uma", "Thurman", "1970-04-29", Country.USA));
            actorDaoInstance.save(new Actor("Morgan", "Freeman", "1937-06-01", Country.USA));
            actorDaoInstance.save(new Actor("Robert", "De Niro", "1943-08-17", Country.USA));
            actorDaoInstance.save(new Actor("Natalie", "Portman", "1981-06-09", Country.ISRAEL));
        }
        return actorDaoInstance;
    }

    private static ActorDaoImpl actorDaoInstance;
    private HashMap<Long, Actor> database = new HashMap<>();
    private Long counter = 0L;

    @Override
    public Actor save(Actor actor) {
        counter++;
        actor.setId(counter);
        database.put(counter,actor);
        return actor;
    }

    @Override
    public Actor update(Actor newActor) {
        Long actorId = newActor.getId();
        if(actorId == null){throw new RuntimeException("Updated actor's id is null");}
        Actor databaseActor = database.get(actorId);
        if(databaseActor == null){throw new RuntimeException("Actor for update doesn't exist");}
        return database.replace(actorId,newActor);
    }

    @Override
    public void delete(Actor actor) {
        if(actor != null && actor.getId() != null){
            database.remove(actor.getId());
        }
    }

    @Override
    public void delete(Long id) {
        if(id != null){
            database.remove(id);
        }
    }

    @Override
    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Actor> findAll() {
        return new LinkedList<>(database.values());
    }
}
