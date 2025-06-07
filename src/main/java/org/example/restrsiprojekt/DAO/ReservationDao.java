package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao {

    public Reservation save(Reservation reservation);
    public Reservation update(Reservation reservation);
    public void delete(Reservation reservation);
    public void delete(Long id);
    public Optional<Reservation> findById(Long id);
    public List<Reservation> findAll();

    List<Reservation> findByMovieId(Long movieId);
}
