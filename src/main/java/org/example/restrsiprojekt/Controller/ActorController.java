package org.example.restrsiprojekt.Controller;

import org.example.restrsiprojekt.DAO.ActorDao;
import org.example.restrsiprojekt.DAO.ActorDaoImpl;
import org.example.restrsiprojekt.model.Actor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/actor")
public class ActorController {

    private final ActorDao actorDao = ActorDaoImpl.getActorDaoInstance();

    @PostMapping()
    public Actor addActor(@RequestBody Actor actor) {
        return actorDao.save(actor);
    }

    @GetMapping("/{id}")
    public Actor getActor(@PathVariable Long id) {
        return actorDao.findById(id).orElseThrow();
    }
    @GetMapping()
    public List<Actor> getActorList() {
        return actorDao.findAll();
    }
}
