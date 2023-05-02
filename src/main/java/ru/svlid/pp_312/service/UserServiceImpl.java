package ru.svlid.pp_312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svlid.pp_312.models.User;
import ru.svlid.pp_312.repos.UserRepo;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    @Autowired
    @Transactional
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }
    @Transactional
    public  Iterable<User> findAllById(Long id) {
        return  userRepo.findAllById(Collections.singleton(id));
    }
    @Transactional
    public void save (User user) {
        userRepo.save(user);
    }
    @Transactional
    public void delete(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }
    @Transactional
    public void updateUser(User user) {
        userRepo.save(user);
    }
}
