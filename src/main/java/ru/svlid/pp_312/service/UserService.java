package ru.svlid.pp_312.service;

import ru.svlid.pp_312.models.User;

public interface UserService {
    public  Iterable<User> findAllById(Long id);

    public void save (User user);

    public void delete(Long id);

    public void updateUser(User user);

    public Iterable<User> findAll();


}
