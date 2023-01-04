package com.example.Task10.services;

import com.example.Task10.models.User;
import com.example.Task10.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean add(User user) {
        Optional<User> userFromDB = userRepository.findUserByEmail(user.getEmail());
        if(userFromDB.isPresent()) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public List<User> show() {
        return userRepository.findAll();
    }

    public User show(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void update(User user) {
        Optional<User> userFromDB = userRepository.findUserByEmail(user.getEmail());
        if(userFromDB.isPresent()) return;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
