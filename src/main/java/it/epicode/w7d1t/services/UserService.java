package it.epicode.w7d1t.services;

import it.epicode.w7d1t.exceptions.NotFoundException;
import it.epicode.w7d1t.models.objects.User;
import it.epicode.w7d1t.models.requests.UserRequest;
import it.epicode.w7d1t.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAll(Pageable pageable) {

        return userRepository.findAll(pageable);

    }

    public User getById(int id) {

        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

    }

    public User getByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));

    }

    public User save(UserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        return userRepository.save(user);

    }
}
