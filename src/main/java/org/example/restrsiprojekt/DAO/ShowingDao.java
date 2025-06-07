package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.Showing;

import java.util.List;
import java.util.Optional;

public interface ShowingDao {

    Optional<Showing> findById(Long id);

    List<Showing> findAll();

    Showing save(Showing showing);

    void delete(Long id);
}
