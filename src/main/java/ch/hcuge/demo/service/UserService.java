package ch.hcuge.demo.service;

import ch.hcuge.demo.entity.User;
import ch.hcuge.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }
}
