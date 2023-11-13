package net.guides.springboot.registrationlogindemo.service;

import net.guides.springboot.registrationlogindemo.entity.User;
import net.guides.springboot.registrationlogindemo.dto.UserDto;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
