package by.kirilldikun.projectmanagementsystem.service;

import by.kirilldikun.projectmanagementsystem.entity.User;

public interface UserService {

    User findByUsername(String username);
}
