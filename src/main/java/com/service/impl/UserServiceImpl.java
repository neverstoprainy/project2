package com.service.impl;

import com.entity.User;
import com.mapper.UserDAO;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDAO userDAO;

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User login(User user) {
        return userDAO.findByUsernameAndPassword(user);
    }
}
