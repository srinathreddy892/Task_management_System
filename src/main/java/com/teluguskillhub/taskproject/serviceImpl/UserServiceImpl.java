package com.teluguskillhub.taskproject.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teluguskillhub.taskproject.entity.Users;
import com.teluguskillhub.taskproject.payload.UserDto;
import com.teluguskillhub.taskproject.repository.UserRepository;
import com.teluguskillhub.taskproject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
    	    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            Users users = modelMapper.map(userDto, Users.class);//userDto class to Entity class
            Users savedUser = userRepository.save(users);
            return modelMapper.map(savedUser, UserDto.class);// Entity class to UserDto class
        
    }
    
    

}
