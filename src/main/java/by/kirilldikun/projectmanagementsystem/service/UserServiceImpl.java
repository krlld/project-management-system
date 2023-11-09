package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.entity.User;
import by.kirilldikun.projectmanagementsystem.exception.UserAlreadyExistsException;
import by.kirilldikun.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
