package com.webmarket.service;

import com.webmarket.beans.User;
import com.webmarket.dao.UserDao;
import com.webmarket.util.PasswordUtil;

import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();

    // логин
    public User login(String username, String password) {

        User user = userDao.findByUsername(username);

        // проверка пароля через BCrypt
        if (user != null && PasswordUtil.checkPassword(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    // регистрация
    public boolean register(String username, String password, String email) {

        User existing = userDao.findByUsername(username);

        if (existing != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole("USER");

        // хэширование пароля
        user.setPassword(PasswordUtil.hashPassword(password));

        return userDao.save(user);
    }

    // получить всех пользователей (для админа)

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // удалить пользователя (админ)
    public boolean deleteUser(int id) {
        return userDao.deleteById(id);
    }
}