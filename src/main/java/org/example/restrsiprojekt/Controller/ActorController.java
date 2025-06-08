package org.example.restrsiprojekt.Controller;

import org.example.restrsiprojekt.Controller.Exception.ActorNotFoundException;
import org.example.restrsiprojekt.DAO.ActorDao;
import org.example.restrsiprojekt.DAO.ActorDaoImpl;
import org.example.restrsiprojekt.model.Actor;
import org.example.restrsiprojekt.model.Movie;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping("/actor")
public class ActorController {

    private final ActorDao actorDao;

    public ActorController(){
        actorDao = ActorDaoImpl.getActorDaoInstance();
    }

    @PostMapping()
    public EntityModel<Actor> addActor(@RequestBody Actor actor) {
        Actor savedActor = actorDao.save(actor);
        return EntityModel.of(savedActor,
                linkTo(methodOn(ActorController.class).addActor(savedActor)).withSelfRel(),
                linkTo(methodOn(ActorController.class).getActor(savedActor.getId())).withRel("find")
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Actor> getActor(@PathVariable Long id) {
        Actor foundActor = actorDao.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
        return EntityModel.of(foundActor,
                linkTo(methodOn(ActorController.class).getActor(foundActor.getId())).withSelfRel(),
                linkTo(methodOn(ActorController.class).getActorList()).withRel("all")
                );
    }
    @GetMapping()
    public CollectionModel<EntityModel<Actor>> getActorList() {
        List<Actor> actorList = actorDao.findAll();

        List<EntityModel<Actor>> actors = actorList.stream()
                .map(actor -> EntityModel.of(actor,
                                linkTo(methodOn(ActorController.class).getActor(actor.getId())).withSelfRel()
                        )
                ).toList();


        return CollectionModel.of(actors,
                linkTo(methodOn(ActorController.class).getActorList()).withSelfRel());
    }
}
