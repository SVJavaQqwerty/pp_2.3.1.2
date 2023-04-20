package ru.svlid.pp_312.service;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svlid.pp_312.models.User;
import ru.svlid.pp_312.repos.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public  Iterable<User> findAllById(Long id) {
        return  userRepo.findAllById(Collections.singleton(id));
    }

    public User save (User user) {

        return userRepo.save(user);
    }

    public boolean existsById(Long id) {
        return userRepo.existsById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }


    public Iterable<User> addId(Long id) {
        return null;
    }

}
