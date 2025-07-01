package com.jmill29.tvtrackerapi.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.dto.UserDto;
import com.jmill29.tvtrackerapi.model.User;

public interface UserDao {

    public Optional<UserDto> findById(int id) throws SQLException;
    public Optional<UserDto> findByUsername(String username) throws SQLException;
    public List<UserDto> findAll() throws SQLException;
    public boolean save(User user) throws SQLException;
    public boolean deleteById(int id) throws SQLException;

}
