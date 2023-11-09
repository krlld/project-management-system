package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.entity.User;

public interface UserService {

    User save(User user);

    User findByUsername(String username);
}
