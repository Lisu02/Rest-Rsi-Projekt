package org.example.restrsiprojekt.Controller;


import org.example.restrsiprojekt.Controller.Exception.ShowingNotFoundException;
import org.example.restrsiprojekt.DAO.ShowingDao;
import org.example.restrsiprojekt.DAO.ShowingDaoImpl;
import org.example.restrsiprojekt.model.Showing;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
@RequestMapping("/showing")
public class ShowingController {

    private final ShowingDao showingDao = ShowingDaoImpl.getShowingDaoInstance();


    @PostMapping
    public EntityModel<Showing> addShowing(@RequestBody Showing showing) {

        Showing showing1 = showingDao.save(showing);

        return EntityModel.of(showing1,
                linkTo(methodOn(ShowingController.class).addShowing(showing1)).withSelfRel(),
                linkTo(methodOn(ShowingController.class).getShowing(showing1.getShowingId())).withRel("find")
        );
    }
    @DeleteMapping("/{id}")
    public void deleteShowing(@PathVariable Long id) {
        showingDao.delete(id);
    }
    @GetMapping("/{id}")
    public EntityModel<Showing> getShowing(@PathVariable Long id) {
        Showing showing = showingDao.findById(id).orElseThrow(() -> new ShowingNotFoundException(id));

        return EntityModel.of(showing,
                linkTo(methodOn(ShowingController.class).getShowing(showing.getShowingId())).withSelfRel(),
                linkTo(methodOn(ShowingController.class).getShowingList()).withRel("all")
                );
    }
    @GetMapping()
    public CollectionModel<EntityModel<Showing>> getShowingList() {
        List<Showing> showingList = showingDao.findAll();

        List<EntityModel<Showing>> showings = showingList.stream()
                .map(showing -> EntityModel.of(showing,
                        linkTo(methodOn(ShowingController.class).getShowing(showing.getShowingId())).withSelfRel()
                        )
                ).toList();

        return CollectionModel.of(showings,
                linkTo(methodOn(ShowingController.class).getShowingList()).withSelfRel());
    }
}
