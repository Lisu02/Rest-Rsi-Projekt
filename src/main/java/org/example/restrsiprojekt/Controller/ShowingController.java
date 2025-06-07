package org.example.restrsiprojekt.Controller;


import org.example.restrsiprojekt.DAO.ShowingDao;
import org.example.restrsiprojekt.DAO.ShowingDaoImpl;
import org.example.restrsiprojekt.model.Showing;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/showing")
public class ShowingController {

    private final ShowingDao showingDao = ShowingDaoImpl.getShowingDaoInstance();


    @PostMapping
    public Showing addShowing(@RequestBody Showing showing) {
        return showingDao.save(showing);
    }
    @DeleteMapping("/{id}")
    public void deleteShowing(@PathVariable Long id) {
        showingDao.delete(id);
    }
    @GetMapping("/{id}")
    public Showing getShowing(@PathVariable Long id) {
        return showingDao.findById(id).get();
    }
    @GetMapping()
    public List<Showing> getShowingList() {
        return showingDao.findAll();
    }
}
