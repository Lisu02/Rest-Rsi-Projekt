package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    public User save(User user);
    public User update(User user);
    public void delete(User user);
    public void delete(Long id);
    public Optional<User> findById(Long id);
    public List<User> findAll();

}
