package guhar4k.crud.controller;

import guhar4k.crud.model.User;
import guhar4k.crud.repository.UserRepository;
import guhar4k.crud.repository.json.JsonRepositoryFactory;

import java.util.List;

public class UserController {
    private UserRepository userRepository;

    public UserController() {
        userRepository = JsonRepositoryFactory.getUserRepository();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
