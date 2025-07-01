package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.UserDao;
import com.jmill29.tvtrackerapi.dto.UserDto;
import com.jmill29.tvtrackerapi.model.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserDto> findById(int id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    @Override
    public List<UserDto> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Override
    public boolean save(User user) throws SQLException {
        return userDao.save(user);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return userDao.deleteById(id);
    }

    
}
