package org.example.restrsiprojekt.DAO;


import org.example.restrsiprojekt.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private HashMap<Long, User> database = new HashMap<>();
    private Long counter = 0L;

    @Override
    public User save(User user) {
        counter++;
        user.setUserId(counter);
        database.put(counter,user);
        return user;

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {
        database.remove(user.getUserId(),user);
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<User> findAll() {
        return database.values().stream().toList();
    }
}
